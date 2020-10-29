
create index activity_entity_name_owner_name_event_on_idx on activity_log(entity_name, owner_name, (date_part('epoch', event_on) * 1000));

update activity_log set entity_name = 'LEXEME_NOTE' where owner_name = 'LEXEME' and entity_name = 'NOTE';

-- kustutamiste logi veergude vahetamine
update activity_log as alt
   set prev_data = als.curr_data,
       prev_diffs = als.curr_diffs,
       curr_data = als.prev_data,
       curr_diffs = als.prev_diffs
from activity_log als
where alt.id = als.id
and   alt.funct_name like 'delete%'
and   alt.owner_name = alt.entity_name;

-- keelendi väärtuse kolimine
alter table word drop column morph_code cascade;
alter table word add column value text, add column value_prese text, add column value_as_word text;

update word wt
   set value = ws.value,
       value_prese = ws.value_prese
from (select w.id,
             (array_agg(distinct f.value))[1] as value,
             (array_agg(distinct f.value_prese))[1] as value_prese
      from word w,
           paradigm p,
           form f
      where p.word_id = w.id
      and   f.paradigm_id = p.id
      and   f.mode in ('WORD', 'UNKNOWN')
      group by w.id) ws
where wt.id = ws.id;

update word wt
   set value_as_word = ws.value
from (select w.id,
             (array_agg(distinct f.value))[1] as value
      from word w,
           paradigm p,
           form f
      where p.word_id = w.id
      and   f.paradigm_id = p.id
      and   f.mode = 'AS_WORD'
      group by w.id) ws
where wt.id = ws.id;

delete from word w where w.value is null;

alter table word alter column value set not null;
alter table word alter column value_prese set not null;

create index word_value_idx on word(value);
create index word_value_lower_idx on word(lower(value));
create index word_value_lower_prefix_idx on word(lower(value) text_pattern_ops);

create index word_value_as_word_idx on word(value_as_word);
create index word_value_as_word_lower_idx on word(lower(value_as_word));
create index word_value_as_word_lower_prefix_idx on word(lower(value_as_word) text_pattern_ops);

update aspect set order_by = 1 where code = 'несов.';
update aspect set order_by = 2 where code = 'сов. и несов.';
update aspect set order_by = 3 where code = 'сов.';

------------------------------------------
-- tundmatute keelendite likvideerimine --
------------------------------------------

-- tõstab kollok liikmelisuse ümber kui esineb 1 homonüüm 1 tähendus (6335)

update lex_colloc lc
   set lexeme_id = ukw.kw_lex_id
from (select ukw.*,
             ukw.uw_lex_ids[1] uw_lex_id,
             ukw.kw_lex_ids[1] kw_lex_id
      from (select ukw.*
            from (select ukw.*,
                         (select array_agg(l.id)
                          from lexeme l
                          where l.word_id = ukw.uw_id
                          and   l.type = 'PRIMARY'
                          and   l.dataset_code = 'sss') uw_lex_ids,
                         (select array_agg(l.id)
                          from lexeme l
                          where l.word_id = ukw.kw_id
                          and   l.type = 'PRIMARY'
                          and   l.dataset_code = 'sss') kw_lex_ids
                  from (select ukw.*,
                               ukw.kw_ids[1] kw_id
                        from (select ukw.*
                              from (select uw.id uw_id,
                                           uw.value,
                                           max(kw.homonym_nr) max_homon_nr,
                                           array_agg(kw.id) kw_ids
                                    from (select w.id,
                                                 w.value,
                                                 w.lang
                                          from word w
                                          where exists (select f.id
                                                        from paradigm p,
                                                             form f
                                                        where p.word_id = w.id
                                                        and   f.paradigm_id = p.id
                                                        and   f.mode = 'UNKNOWN')) uw
                                      inner join (select w.id,
                                                         w.value,
                                                         w.homonym_nr,
                                                         w.lang
                                                  from word w
                                                  where exists (select f.id
                                                                from paradigm p,
                                                                     form f
                                                                where p.word_id = w.id
                                                                and   f.paradigm_id = p.id
                                                                and   f.mode = 'WORD')
                                                  and   exists (select l.id
                                                                from lexeme l
                                                                where l.word_id = w.id
                                                                and   l.type = 'PRIMARY'
                                                                and   l.dataset_code = 'sss')) kw
                                              on kw.value = uw.value
                                             and kw.lang = uw.lang
                                    group by uw.id,
                                             uw.value) ukw
                              where array_length(ukw.kw_ids, 1) = 1) ukw) ukw) ukw
            where array_length(ukw.kw_lex_ids, 1) = 1) ukw) ukw
where lc.lexeme_id = ukw.uw_lex_id;

-- tundmatute kollide sildid
insert into tag(name) values ('tundmatu koll, 1 homon'), ('tundmatu koll, n homon'), ('tundmatu koll, 0 homon');

-- lisab sildi kui kollok liige on tndmatu ja esineb 1 valiidne homonüüm ja n tähendust (4511)

insert into lexeme_tag
(
  lexeme_id,
  tag_name
)
select ukw.uw_lex_id,
       'tundmatu koll, 1 homon'
from (select ukw.*
      from (select ukw.*,
                   (select array_agg(l.id)
                    from lexeme l
                    where l.word_id = ukw.kw_id
                    and   l.type = 'PRIMARY'
                    and   l.dataset_code = 'sss') kw_lex_ids
            from (select ukw.*,
                         ukw.kw_ids[1] kw_id
                  from (select ukw.*
                        from (select uw.id uw_id,
                                     uw.value,
                                     uw.lexeme_id uw_lex_id,
                                     max(kw.homonym_nr) max_homon_nr,
                                     array_agg(kw.id) kw_ids
                              from (select w.id,
                                           w.value,
                                           w.lang,
                                           l.id lexeme_id
                                    from word w,
                                         lexeme l
                                    where l.word_id = w.id
                                    and   l.type = 'PRIMARY'
                                    and   l.dataset_code = 'sss'
                                    and   exists (select f.id
                                                  from paradigm p,
                                                       form f
                                                  where p.word_id = w.id
                                                  and   f.paradigm_id = p.id
                                                  and   f.mode = 'UNKNOWN')) uw
                                inner join (select w.id,
                                                   w.value,
                                                   w.homonym_nr,
                                                   w.lang
                                            from word w
                                            where exists (select f.id
                                                          from paradigm p,
                                                               form f
                                                          where p.word_id = w.id
                                                          and   f.paradigm_id = p.id
                                                          and   f.mode = 'WORD')
                                            and   exists (select l.id
                                                          from lexeme l
                                                          where l.word_id = w.id
                                                          and   l.type = 'PRIMARY'
                                                          and   l.dataset_code = 'sss')) kw
                                        on kw.value = uw.value
                                       and kw.lang = uw.lang
                              group by uw.id,
                                       uw.value,
                                       uw.lexeme_id) ukw
                        where array_length(ukw.kw_ids, 1) = 1) ukw) ukw) ukw
      where array_length(ukw.kw_lex_ids, 1) > 1) ukw
where not exists (select lt.id
                  from lexeme_tag lt
                  where lt.lexeme_id = ukw.uw_lex_id
                  and   lt.tag_name = 'tundmatu koll, 1 homon');

-- lisab sildi kui kollok liige on tndmatu ja esineb n valiidset homonüümi (258)

insert into lexeme_tag
(
  lexeme_id,
  tag_name
)
select ukw.uw_lex_id,
       'tundmatu koll, n homon'
from (select ukw.*
      from (select uw.id uw_id,
                   uw.value,
                   uw.lexeme_id uw_lex_id,
                   max(kw.homonym_nr) max_homon_nr,
                   array_agg(kw.id) kw_ids
            from (select w.id,
                         w.value,
                         w.lang,
                         l.id lexeme_id
                  from word w,
                       lexeme l
                  where l.word_id = w.id
                  and   l.type = 'PRIMARY'
                  and   l.dataset_code = 'sss'
                  and   exists (select f.id
                                from paradigm p,
                                     form f
                                where p.word_id = w.id
                                and   f.paradigm_id = p.id
                                and   f.mode = 'UNKNOWN')) uw
              inner join (select w.id,
                                 w.value,
                                 w.homonym_nr,
                                 w.lang
                          from word w
                          where exists (select f.id
                                        from paradigm p,
                                             form f
                                        where p.word_id = w.id
                                        and   f.paradigm_id = p.id
                                        and   f.mode = 'WORD')
                          and   exists (select l.id
                                        from lexeme l
                                        where l.word_id = w.id
                                        and   l.type = 'PRIMARY'
                                        and   l.dataset_code = 'sss')) kw
                      on kw.value = uw.value
                     and kw.lang = uw.lang
            group by uw.id,
                     uw.value,
                     uw.lexeme_id) ukw
      where array_length(ukw.kw_ids, 1) > 1) ukw
where not exists (select lt.id
                  from lexeme_tag lt
                  where lt.lexeme_id = ukw.uw_lex_id
                  and   lt.tag_name = 'tundmatu koll, n homon');
                  
-- lisab sildi kui kollok liige on tndmatu ja puuduvad valiidsed homonüümid (3768)

insert into lexeme_tag
(
  lexeme_id,
  tag_name
)
select ukw.uw_lex_id,
       'tundmatu koll, 0 homon'
from (select uw.id uw_id,
             uw.value,
             uw.lang,
             uwl.id uw_lex_id
      from word uw,
           lexeme uwl
      where uwl.word_id = uw.id
      and   uwl.type = 'PRIMARY'
      and   uwl.dataset_code = 'sss'
      and   exists (select f.id
                    from paradigm p,
                         form f
                    where p.word_id = uw.id
                    and   f.paradigm_id = p.id
                    and   f.mode = 'UNKNOWN')
      and   not exists (select kw.id
                        from word kw
                        where kw.value = uw.value
                        and   kw.lang = uw.lang
                        and   exists (select f.id
                                      from paradigm p,
                                           form f
                                      where p.word_id = kw.id
                                      and   f.paradigm_id = p.id
                                      and   f.mode = 'WORD')
                        and   exists (select l.id
                                      from lexeme l
                                      where l.word_id = kw.id
                                      and   l.type = 'PRIMARY'
                                      and   l.dataset_code = 'sss'))
      group by uw.id,
               uwl.id) ukw
where not exists (select lt.id
                  from lexeme_tag lt
                  where lt.lexeme_id = ukw.uw_lex_id
                  and   lt.tag_name = 'tundmatu koll, 0 homon');
                  
-- muuda kõik tundmatute kollide sildiga lekseemid mitteavalikuks
                  
update lexeme l
   set is_public = false
where exists (select lt.id
              from lexeme_tag lt
              where lt.lexeme_id = l.id
              and   lt.tag_name in ('tundmatu koll, 1 homon', 'tundmatu koll, n homon', 'tundmatu koll, 0 homon'));

-- freeform tabelile looja/muutja andmete lisamine ja olemasolevate andmete migreerimine logidest

alter table freeform
add column created_by text null,
add column created_on timestamp null,
add column modified_by text null,
add column modified_on timestamp null;