package eki.common.constant;

public interface GlobalConstant {

	String UTF_8 = "UTF-8";

	String LANGUAGE_CODE_EST = "est";

	String LANGUAGE_CODE_RUS = "rus";

	String LANGUAGE_CODE_ENG = "eng";

	String LANGUAGE_CODE_LAT = "lat";

	String DATASET_EKI = "eki";

	String DATASET_LIMITED = "vrk";

	String DATASET_XXX = "xxx";

	String DATASET_TEST = "kce";

	boolean PUBLICITY_PUBLIC = true;

	boolean PUBLICITY_PRIVATE = false;

	boolean MANUAL_EVENT_ON_UPDATE_DISABLED = false;

	boolean MANUAL_EVENT_ON_UPDATE_ENABLED = true;

	String VALUE_STATE_MOST_PREFERRED = "eelistatud";

	String VALUE_STATE_LEAST_PREFERRED = "väldi";

	String VALUE_STATE_INCORRECT = "vigane";

	String DEFINITION_TYPE_UNDEFINED = "määramata";

	String COMPLEXITY_DETAIL = Complexity.DETAIL.name();

	String UNKNOWN_FORM_CODE = "??";

	String WORD_TYPE_CODE_PREFIXOID = "pf";

	String WORD_TYPE_CODE_SUFFIXOID = "sf";

	String WORD_TYPE_CODE_ABBREVIATION = "l";

	String[] WORD_TYPE_CODES_ABBREVIATION = new String[] {"l", "th"};

	String[] WORD_TYPE_CODES_FOREIGN = new String[] {"z", "lz"};

	String WORD_REL_TYPE_CODE_ASCPECTS = "ASPECTS";

	String WORD_REL_TYPE_CODE_DERIVATIVE_BASE = "deriv_base";

	String WORD_REL_TYPE_CODE_RAW = "raw";

	String WORD_REL_TYPE_CODE_COMP = "ühend";

	String[] PRIMARY_WORD_REL_TYPE_CODES = new String[] {WORD_REL_TYPE_CODE_COMP, WORD_REL_TYPE_CODE_RAW};

	String LEX_REL_TYPE_CODE_DIRECT_MATCH = "otse";

	String MEANING_REL_TYPE_CODE_SIMILAR = "sarnane";

	String[] MEANING_ATTRIBUTES = new String[] {
			FreeformType.CONCEPT_ID.name(), FreeformType.GENUS.name(), FreeformType.FAMILY.name(), FreeformType.DESCRIBER.name(),
			FreeformType.DESCRIBING_YEAR.name(), FreeformType.SOURCE_FILE.name()};

	char DISPLAY_FORM_STRESS_SYMBOL = '"';

	String[] DISPLAY_FORM_IGNORE_SYMBOLS = new String[] {"[", "]", "*"};

	String FUNCT_NAME_APPROVE_MEANING = "approveMeaning";

	String IGNORE_QUERY_LOG = "'ignore query log'";

	String FORCE_QUERY_LOG = "'force query log'";

	String QUERY_MULTIPLE_CHARACTERS_SYM = "*";

	String QUERY_SINGLE_CHARACTER_SYM = "?";

	String STAT_API_KEY_HEADER_NAME = "stat-api-key";

	String EMPTY_API_KEY = "empty-api-key";

	String ENCODE_SYM_SLASH = "$2F";

	String ENCODE_SYM_PERCENT = "$25";

	String ENCODE_SYM_BACKSLASH = "$5C";
}
