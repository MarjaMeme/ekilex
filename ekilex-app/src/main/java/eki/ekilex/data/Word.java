package eki.ekilex.data;

import java.util.List;

public class Word extends AbstractCrudEntity implements DecoratedWordType {

	private static final long serialVersionUID = 1L;

	private Long wordId;

	private String wordValue;

	private String wordValuePrese;

	private Integer homonymNr;

	private String lang;

	private String genderCode;

	private String aspectCode;

	private String vocalForm;

	private String morphCode;

	private String displayMorphCode;

	private String wordFrequency;

	private String[] wordTypeCodes;

	private List<Classifier> wordTypes;

	private boolean prefixoid;

	private boolean suffixoid;

	private boolean foreign;

	private Boolean lexemesArePublic;

	private List<String> lexemesValueStateLabels;

	private List<String> lexemesTagNames;

	private List<String> datasetCodes;

	private List<WordNote> notes;

	private List<Relation> relations;

	private List<WordGroup> groups;

	private List<WordEtym> etymology;

	private List<FreeForm> odWordRecommendations;

	public Long getWordId() {
		return wordId;
	}

	public void setWordId(Long wordId) {
		this.wordId = wordId;
	}

	@Override
	public String getWordValue() {
		return wordValue;
	}

	@Override
	public void setWordValue(String value) {
		this.wordValue = value;
	}

	@Override
	public String getWordValuePrese() {
		return wordValuePrese;
	}

	@Override
	public void setWordValuePrese(String valuePrese) {
		this.wordValuePrese = valuePrese;
	}

	public Integer getHomonymNr() {
		return homonymNr;
	}

	public void setHomonymNr(Integer homonymNr) {
		this.homonymNr = homonymNr;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getGenderCode() {
		return genderCode;
	}

	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}

	public String getAspectCode() {
		return aspectCode;
	}

	public void setAspectCode(String aspectCode) {
		this.aspectCode = aspectCode;
	}

	public String getVocalForm() {
		return vocalForm;
	}

	public void setVocalForm(String vocalForm) {
		this.vocalForm = vocalForm;
	}

	public String getMorphCode() {
		return morphCode;
	}

	public void setMorphCode(String morphCode) {
		this.morphCode = morphCode;
	}

	public String getDisplayMorphCode() {
		return displayMorphCode;
	}

	public void setDisplayMorphCode(String displayMorphCode) {
		this.displayMorphCode = displayMorphCode;
	}

	public String getWordFrequency() {
		return wordFrequency;
	}

	public void setWordFrequency(String wordFrequency) {
		this.wordFrequency = wordFrequency;
	}

	@Override
	public String[] getWordTypeCodes() {
		return wordTypeCodes;
	}

	@Override
	public void setWordTypeCodes(String[] wordTypeCodes) {
		this.wordTypeCodes = wordTypeCodes;
	}

	public List<Classifier> getWordTypes() {
		return wordTypes;
	}

	public void setWordTypes(List<Classifier> wordTypes) {
		this.wordTypes = wordTypes;
	}

	@Override
	public boolean isPrefixoid() {
		return prefixoid;
	}

	@Override
	public void setPrefixoid(boolean prefixoid) {
		this.prefixoid = prefixoid;
	}

	@Override
	public boolean isSuffixoid() {
		return suffixoid;
	}

	@Override
	public void setSuffixoid(boolean suffixoid) {
		this.suffixoid = suffixoid;
	}

	@Override
	public boolean isForeign() {
		return foreign;
	}

	@Override
	public void setForeign(boolean foreign) {
		this.foreign = foreign;
	}

	public Boolean getLexemesArePublic() {
		return lexemesArePublic;
	}

	public void setLexemesArePublic(Boolean lexemesArePublic) {
		this.lexemesArePublic = lexemesArePublic;
	}

	public List<String> getLexemesValueStateLabels() {
		return lexemesValueStateLabels;
	}

	public void setLexemesValueStateLabels(List<String> lexemesValueStateLabels) {
		this.lexemesValueStateLabels = lexemesValueStateLabels;
	}

	public List<String> getLexemesTagNames() {
		return lexemesTagNames;
	}

	public void setLexemesTagNames(List<String> lexemesTagNames) {
		this.lexemesTagNames = lexemesTagNames;
	}

	public List<String> getDatasetCodes() {
		return datasetCodes;
	}

	public void setDatasetCodes(List<String> datasetCodes) {
		this.datasetCodes = datasetCodes;
	}

	public List<WordNote> getNotes() {
		return notes;
	}

	public void setNotes(List<WordNote> notes) {
		this.notes = notes;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	public List<WordGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<WordGroup> groups) {
		this.groups = groups;
	}

	public List<WordEtym> getEtymology() {
		return etymology;
	}

	public void setEtymology(List<WordEtym> etymology) {
		this.etymology = etymology;
	}

	public List<FreeForm> getOdWordRecommendations() {
		return odWordRecommendations;
	}

	public void setOdWordRecommendations(List<FreeForm> odWordRecommendations) {
		this.odWordRecommendations = odWordRecommendations;
	}

}
