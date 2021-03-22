package eki.ekilex.constant;

import org.apache.commons.lang3.ArrayUtils;

public enum SearchKey {

	ID(new SearchOperand[] {SearchOperand.EQUALS}, SearchValueType.NUMERIC),
	VALUE(OperandSets.VALUE_OPERANDS, SearchValueType.TEXTUAL),
	VALUE_AND_EXISTS(OperandSets.VALUE_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL),
	LANGUAGE(new SearchOperand[] {SearchOperand.EQUALS, SearchOperand.NOT_EQUALS, SearchOperand.NOT_CONTAINS}, SearchValueType.TEXTUAL),
	WORD_TYPE(OperandSets.CLASSIFIER_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL),
	ASPECT(new SearchOperand[] {SearchOperand.EQUALS, SearchOperand.NOT_EQUALS, SearchOperand.EXISTS, SearchOperand.NOT_EXISTS}, SearchValueType.TEXTUAL),
	TAG_NAME(new SearchOperand[] {SearchOperand.EQUALS, SearchOperand.NOT_CONTAINS, SearchOperand.HAS_BEEN}, SearchValueType.TEXTUAL),
	DOMAIN(OperandSets.CLASSIFIER_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL),
	SEMANTIC_TYPE(OperandSets.CLASSIFIER_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL),
	SOURCE_REF(new SearchOperand[] {SearchOperand.EQUALS, SearchOperand.STARTS_WITH, SearchOperand.ENDS_WITH, SearchOperand.CONTAINS, SearchOperand.NOT_EXISTS}, SearchValueType.TEXTUAL),
	SOURCE_NAME(new SearchOperand[] {SearchOperand.EQUALS, SearchOperand.STARTS_WITH, SearchOperand.ENDS_WITH, SearchOperand.CONTAINS}, SearchValueType.TEXTUAL),
	SOURCE_ID(new SearchOperand[] {SearchOperand.EQUALS}, SearchValueType.NUMERIC),
	CREATED_OR_UPDATED_ON(OperandSets.DATUM_OPERANDS, SearchValueType.TEXTUAL),
	CREATED_OR_UPDATED_BY(OperandSets.USER_NAME_OPERANDS, SearchValueType.TEXTUAL),
	CREATED_ON(OperandSets.DATUM_OPERANDS, SearchValueType.TEXTUAL),
	CREATED_BY(OperandSets.USER_NAME_OPERANDS, SearchValueType.TEXTUAL),
	UPDATED_ON(OperandSets.DATUM_OPERANDS, SearchValueType.TEXTUAL),
	UPDATED_BY(OperandSets.USER_NAME_OPERANDS, SearchValueType.TEXTUAL),
	LAST_UPDATE_ON(OperandSets.DATUM_OPERANDS, SearchValueType.TEXTUAL),
	DATASET_USAGE(new SearchOperand[] {SearchOperand.EQUALS}, SearchValueType.TEXTUAL),
	SECONDARY_MEANING_WORD(OperandSets.VALUE_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL),
	PUBLICITY(new SearchOperand[] {SearchOperand.EQUALS}, SearchValueType.BOOLEAN),
	LEXEME_GRAMMAR(OperandSets.VALUE_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL),
	LEXEME_GOVERNMENT(OperandSets.VALUE_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL),
	LEXEME_POS(OperandSets.CLASSIFIER_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL),
	LEXEME_REGISTER(OperandSets.CLASSIFIER_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL),
	LEXEME_VALUE_STATE(new SearchOperand[] {SearchOperand.EQUALS, SearchOperand.NOT_EQUALS, SearchOperand.EXISTS, SearchOperand.NOT_EXISTS}, SearchValueType.TEXTUAL),
	OD_RECOMMENDATION(OperandSets.VALUE_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL),
	WORD_RELATION(OperandSets.CLASSIFIER_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL), // TODO kas üks ja mitu ka jätta või teha nagu LEXEME_VALUE_STATE?
	MEANING_RELATION(OperandSets.CLASSIFIER_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL),
	COMPLEXITY(OperandSets.CLASSIFIER_OPERANDS, SearchValueType.TEXTUAL),
	FREQUENCY(new SearchOperand[] {SearchOperand.GREATER_THAN, SearchOperand.LESS_THAN}, SearchValueType.NUMERIC),
	RANK(new SearchOperand[] {SearchOperand.GREATER_THAN, SearchOperand.LESS_THAN}, SearchValueType.NUMERIC),
	ATTRIBUTE_NAME(new SearchOperand[] {SearchOperand.EQUALS}, SearchValueType.TEXTUAL),
	ATTRIBUTE_VALUE(OperandSets.VALUE_AND_EXISTS_OPERANDS, SearchValueType.TEXTUAL)
	;

	private SearchOperand[] operands;

	private SearchValueType valueType;

	private SearchKey(SearchOperand[] operands, SearchValueType valueType) {
		this.operands = operands;
		this.valueType = valueType;
	}

	public SearchOperand[] getOperands() {
		return operands;
	}

	public SearchValueType getValueType() {
		return valueType;
	}

	private interface OperandSets {

		SearchOperand[] VALUE_OPERANDS = new SearchOperand[] {
				SearchOperand.EQUALS, SearchOperand.STARTS_WITH, SearchOperand.ENDS_WITH, SearchOperand.CONTAINS, SearchOperand.CONTAINS_WORD};
		SearchOperand[] VALUE_AND_EXISTS_OPERANDS = ArrayUtils.addAll(VALUE_OPERANDS, SearchOperand.EXISTS, SearchOperand.NOT_EXISTS);
		SearchOperand[] CLASSIFIER_OPERANDS = new SearchOperand[] {SearchOperand.EQUALS, SearchOperand.NOT_EQUALS};
		SearchOperand[] CLASSIFIER_AND_EXISTS_OPERANDS = ArrayUtils.addAll(
				CLASSIFIER_OPERANDS, SearchOperand.NOT_EXISTS, SearchOperand.EXISTS, SearchOperand.SINGLE, SearchOperand.MULTIPLE);
		SearchOperand[] DATUM_OPERANDS = new SearchOperand[] {SearchOperand.EARLIER_THAN, SearchOperand.LATER_THAN};
		SearchOperand[] USER_NAME_OPERANDS = new SearchOperand[] {SearchOperand.EQUALS, SearchOperand.STARTS_WITH, SearchOperand.ENDS_WITH};
	}

}
