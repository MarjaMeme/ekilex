package eki.wordweb.constant;

import eki.common.constant.GlobalConstant;

public interface SystemConstant {

	String DESTIN_LANG_ALL = "dlall";

	String DESTIN_LANG_EST = "est";

	String DESTIN_LANG_ENG = "eng";

	String DESTIN_LANG_RUS = "rus";

	String DESTIN_LANG_FRA = "fra";

	String DESTIN_LANG_UKR = "ukr";

	String DESTIN_LANG_OTHER = "other";

	String DATASET_ALL = "dsall";

	String[] SUPPORTED_SIMPLE_DATASETS = new String[] {DATASET_ALL, GlobalConstant.DATASET_EKI};

	String[] SUPPORTED_DESTIN_LANGS = new String[] {DESTIN_LANG_EST, DESTIN_LANG_ENG, DESTIN_LANG_RUS, DESTIN_LANG_FRA, DESTIN_LANG_UKR};

	String[] SUPPORTED_DETAIL_DESTIN_LANG_FILTERS = new String[] {DESTIN_LANG_ALL, DESTIN_LANG_EST, DESTIN_LANG_ENG, DESTIN_LANG_RUS, DESTIN_LANG_FRA, DESTIN_LANG_UKR, DESTIN_LANG_OTHER};

	String[] SUPPORTED_SIMPLE_DESTIN_LANG_FILTERS = new String[] {DESTIN_LANG_ALL, DESTIN_LANG_EST, DESTIN_LANG_ENG, DESTIN_LANG_RUS, DESTIN_LANG_FRA, DESTIN_LANG_UKR};

	String DATASET_TYPE_LEX = "lex";

	String DATASET_TYPE_TERM = "term";

	String SEARCH_MODE_SIMPLE = "simple";

	String SEARCH_MODE_DETAIL = "detail";

	String GAME_DIFFICULTY_SIMPLE = "easy";

	String GAME_DIFFICULTY_HARD = "hard";

	long CACHE_EVICT_DELAY_5MIN = 5 * 60 * 1000;

	long CACHE_EVICT_DELAY_60MIN = 60 * 60 * 1000;

	String CACHE_KEY_NULL_WORD = "nullword";

	String CACHE_KEY_CLASSIF = "classif";

	String CACHE_KEY_CORPUS = "corpus";

	Float COLLOC_MEMBER_CONTEXT_WEIGHT = 0.5F;

	String DEFAULT_CLASSIF_VALUE_LANG = "est";

	String DEFAULT_CLASSIF_VALUE_TYPE = "wordweb";

	String CLASSIF_VALUE_TYPE_ISO2 = "iso2";

	String WORD_SEARCH_GROUP_WORD = "word";

	String WORD_SEARCH_GROUP_AS_WORD = "as_word";

	String WORD_SEARCH_GROUP_FORM = "form";

	char TEMP_CONVERSION_PLACEHOLDER = '`';

	String ILLEGAL_FORM_VALUE = "-";
}
