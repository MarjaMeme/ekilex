-- query words in different langs with matching meaning
select w_1.word word1,
       w_2.word word2
from (select w.id word_id,
             w.value word,
             w.lang,
             m.id meaning_id
      from word w,
           meaning m,
           lexeme l
      where l.word_id = w.id
      and   l.meaning_id = m.id) w_1,
     (select w.value word,
             w.lang,
             m.id meaning_id
      from word w,
           meaning m,
           lexeme l
      where l.word_id = w.id
      and   l.meaning_id = m.id) w_2
where w_1.meaning_id = w_2.meaning_id
and   w_1.lang != w_2.lang
and   w_1.lang = :lang1;
