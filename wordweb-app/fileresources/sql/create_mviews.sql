drop materialized view if exists mview_ww_word;
drop materialized view if exists mview_ww_form;
drop materialized view if exists mview_ww_meaning;
drop materialized view if exists mview_ww_lexeme;
drop materialized view if exists mview_ww_collocation;
drop materialized view if exists mview_ww_classifier;
drop materialized view if exists mview_ww_dataset;
drop materialized view if exists mview_ww_word_relation;
drop materialized view if exists mview_ww_lexeme_relation;
drop materialized view if exists mview_ww_meaning_relation;
drop type if exists type_word;
drop type if exists type_definition;
drop type if exists type_domain;
drop type if exists type_usage;
drop type if exists type_colloc_member;
drop type if exists type_word_relation;
drop type if exists type_lexeme_relation;
drop type if exists type_meaning_relation;

-- run this once:
-- CREATE EXTENSION dblink;
-- SELECT dblink_connect('host=localhost user=ekilex password=3kil3x dbname=ekilex');

create type type_word as (value text, lang char(3));
create type type_definition as (value text, lang char(3));
create type type_domain as (origin varchar(100), code varchar(100));
create type type_usage as (usage text, usage_author text, usage_translator text);
create type type_colloc_member as (lexeme_id bigint, word_id bigint, word text);
create type type_word_relation as (word_id bigint,word text,word_lang char(3),word_rel_type_code varchar(100));
create type type_lexeme_relation as (lexeme_id bigint,word_id bigint,word text,word_lang char(3),lex_rel_type_code varchar(100));
create type type_meaning_relation as (meaning_id bigint,lexeme_id bigint,word_id bigint,word text,word_lang char(3),meaning_rel_type_code varchar(100));

create materialized view mview_ww_word as
select * from 
dblink(
	'host=localhost user=ekilex password=3kil3x dbname=ekilex',
	'select * from view_ww_word') as word(
	word_id bigint,
	word text,
	homonym_nr integer,
	lang char(3),
	morph_code varchar(100),
	display_morph_code varchar(100),
	dataset_codes varchar(100) array,
	meaning_count integer,
	meaning_words type_word array,
	definitions type_definition array
);

create materialized view mview_ww_form as
select * from 
dblink(
	'host=localhost user=ekilex password=3kil3x dbname=ekilex',
	'select * from view_ww_form') as form(
	word_id bigint,
	word text,
	lang char(3),
	paradigm_id bigint,
	form_id bigint,
	form text,
	morph_code varchar(100),
	components varchar(100) array,
	display_form varchar(255),
	vocal_form varchar(255),
	sound_file varchar(255),
	is_word boolean,
	dataset_codes varchar(100) array
);

create materialized view mview_ww_meaning as
select * from 
dblink(
	'host=localhost user=ekilex password=3kil3x dbname=ekilex',
	'select * from view_ww_meaning') as meaning(
	word_id bigint,
	meaning_id bigint,
	lexeme_id bigint,
	dataset_code varchar(10),
	level1 integer,
	level2 integer,
	level3 integer,
	register_codes varchar(100) array,
	pos_codes varchar(100) array,
	deriv_codes varchar(100) array,
	domain_codes type_domain array,
	image_files text array,
	systematic_polysemy_patterns text array,
	semantic_types text array,
	learner_comments text array,
	definitions type_definition array
);

create materialized view mview_ww_lexeme as
select * from 
dblink(
	'host=localhost user=ekilex password=3kil3x dbname=ekilex',
	'select * from view_ww_lexeme') as lexeme(
	lexeme_id bigint,
	word_id bigint,
	meaning_id bigint,
	advice_notes text array,
	public_notes text array,
	grammars text array,
	government_id bigint,
	government text,
	usage_meaning_id bigint,
	usage_meaning_type_code varchar(100),
	usages type_usage array,
	usage_translations text array,
	usage_definitions text array
);

create materialized view mview_ww_collocation as
select * from 
dblink(
	'host=localhost user=ekilex password=3kil3x dbname=ekilex',
	'select * from view_ww_collocation') as collocation(
	lexeme_id bigint,
	word_id bigint,
	dataset_code varchar(10),
	level1 integer,
	level2 integer,
	level3 integer,
	pos_group_id bigint,
	pos_group_code varchar(100),
	pos_group_order_by bigint,
	rel_group_id bigint,
	rel_group_name text,
	rel_group_order_by bigint,
	colloc_id bigint,
	colloc_value text,
	colloc_definition text,
	colloc_usages text array,
	colloc_order_by bigint,
	colloc_members type_colloc_member array
);

create materialized view mview_ww_word_relation as
select * from
dblink(
	'host=localhost user=ekilex password=3kil3x dbname=ekilex',
	'select * from view_ww_word_relation') as word_relation(
	word_id bigint,
	related_words type_word_relation array
);

create materialized view mview_ww_lexeme_relation as
select * from
dblink(
	'host=localhost user=ekilex password=3kil3x dbname=ekilex',
	'select * from view_ww_lexeme_relation') as lexeme_relation(
	lexeme_id bigint,
	related_lexemes type_lexeme_relation array
);

create materialized view mview_ww_meaning_relation as
select * from
dblink(
	'host=localhost user=ekilex password=3kil3x dbname=ekilex',
	'select * from view_ww_meaning_relation') as meaning_relation(
	lexeme_id bigint,
	meaning_id bigint,
	related_meanings type_meaning_relation array
);

create materialized view mview_ww_dataset as
select * from
dblink(
	'host=localhost user=ekilex password=3kil3x dbname=ekilex',
	'select * from view_ww_dataset') as dataset(
	code varchar(10),
	name text,
	description text,
	lang char(3)
);

create materialized view mview_ww_classifier as
select * from 
dblink(
	'host=localhost user=ekilex password=3kil3x dbname=ekilex',
	'select * from view_ww_classifier') as classifier(
	name text,
	origin text,
	code varchar(100),
	value text,
	lang char(3)
);

create index mview_ww_word_word_id_idx on mview_ww_word (word_id);
create index mview_ww_word_value_idx on mview_ww_word (word);
create index mview_ww_word_value_lower_idx on mview_ww_word (lower(word));
create index mview_ww_word_value_prefix_idx on mview_ww_word (word text_pattern_ops);
create index mview_ww_word_value_lower_prefix_idx on mview_ww_word (lower(word) text_pattern_ops);
create index mview_ww_word_lang_idx on mview_ww_word (lang);
create index mview_ww_form_word_id_idx on mview_ww_form (word_id);
create index mview_ww_form_word_idx on mview_ww_form (word);
create index mview_ww_form_word_lower_idx on mview_ww_form (lower(word));
create index mview_ww_form_value_idx on mview_ww_form (form);
create index mview_ww_form_value_lower_idx on mview_ww_form (lower(form));
create index mview_ww_meaning_word_id_idx on mview_ww_meaning (word_id);
create index mview_ww_meaning_meaning_id_idx on mview_ww_meaning (meaning_id);
create index mview_ww_meaning_lexeme_id_idx on mview_ww_meaning (lexeme_id);
create index mview_ww_lexeme_lexeme_id_idx on mview_ww_lexeme (lexeme_id);
create index mview_ww_lexeme_word_id_idx on mview_ww_lexeme (word_id);
create index mview_ww_lexeme_meaning_id_idx on mview_ww_lexeme (meaning_id);
create index mview_ww_collocation_lexeme_id_idx on mview_ww_collocation (lexeme_id);
create index mview_ww_collocation_word_id_idx on mview_ww_collocation (word_id);
create index mview_ww_word_relation_word_id_idx on mview_ww_word_relation (word_id);
create index mview_ww_lexeme_relation_lexeme_id_idx on mview_ww_lexeme_relation (lexeme_id);
create index mview_ww_meaning_relation_meaning_id_idx on mview_ww_meaning_relation (meaning_id);
create index mview_ww_meaning_relation_lexeme_id_idx on mview_ww_meaning_relation (lexeme_id);
create index mview_ww_classifier_name_code_lang_idx on mview_ww_classifier (name, code, lang);
create index mview_ww_classifier_name_origin_code_lang_idx on mview_ww_classifier (name, origin, code, lang);
