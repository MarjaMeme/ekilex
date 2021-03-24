package eki.ekilex.data;

import java.util.List;

import eki.common.data.AbstractDataObject;

public class EkiUserProfile extends AbstractDataObject {

	private static final long serialVersionUID = 1L;

	private Long userId;

	private Long recentDatasetPermissionId;

	private List<String> preferredDatasets;

	private List<String> preferredTagNames;

	private String activeTagName;

	private List<String> preferredSynCandidateLangs;

	private List<String> preferredSynLexMeaningWordLangs;

	private List<String> preferredMeaningRelationWordLangs;

	private boolean showLexMeaningRelationSourceLangWords;

	private boolean showMeaningRelationFirstWordOnly;

	private boolean showMeaningRelationMeaningId;

	private boolean showMeaningRelationWordDatasets;

	private boolean isApproveMeaningEnabled;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRecentDatasetPermissionId() {
		return recentDatasetPermissionId;
	}

	public void setRecentDatasetPermissionId(Long recentDatasetPermissionId) {
		this.recentDatasetPermissionId = recentDatasetPermissionId;
	}

	public List<String> getPreferredDatasets() {
		return preferredDatasets;
	}

	public void setPreferredDatasets(List<String> preferredDatasets) {
		this.preferredDatasets = preferredDatasets;
	}

	public List<String> getPreferredTagNames() {
		return preferredTagNames;
	}

	public void setPreferredTagNames(List<String> preferredTagNames) {
		this.preferredTagNames = preferredTagNames;
	}

	public String getActiveTagName() {
		return activeTagName;
	}

	public void setActiveTagName(String activeTagName) {
		this.activeTagName = activeTagName;
	}

	public List<String> getPreferredSynCandidateLangs() {
		return preferredSynCandidateLangs;
	}

	public void setPreferredSynCandidateLangs(List<String> preferredSynCandidateLangs) {
		this.preferredSynCandidateLangs = preferredSynCandidateLangs;
	}

	public List<String> getPreferredSynLexMeaningWordLangs() {
		return preferredSynLexMeaningWordLangs;
	}

	public void setPreferredSynLexMeaningWordLangs(List<String> preferredSynLexMeaningWordLangs) {
		this.preferredSynLexMeaningWordLangs = preferredSynLexMeaningWordLangs;
	}

	public List<String> getPreferredMeaningRelationWordLangs() {
		return preferredMeaningRelationWordLangs;
	}

	public void setPreferredMeaningRelationWordLangs(List<String> preferredMeaningRelationWordLangs) {
		this.preferredMeaningRelationWordLangs = preferredMeaningRelationWordLangs;
	}

	public boolean isShowLexMeaningRelationSourceLangWords() {
		return showLexMeaningRelationSourceLangWords;
	}

	public void setShowLexMeaningRelationSourceLangWords(boolean showLexMeaningRelationSourceLangWords) {
		this.showLexMeaningRelationSourceLangWords = showLexMeaningRelationSourceLangWords;
	}

	public boolean isShowMeaningRelationFirstWordOnly() {
		return showMeaningRelationFirstWordOnly;
	}

	public void setShowMeaningRelationFirstWordOnly(boolean showMeaningRelationFirstWordOnly) {
		this.showMeaningRelationFirstWordOnly = showMeaningRelationFirstWordOnly;
	}

	public boolean isShowMeaningRelationMeaningId() {
		return showMeaningRelationMeaningId;
	}

	public void setShowMeaningRelationMeaningId(boolean showMeaningRelationMeaningId) {
		this.showMeaningRelationMeaningId = showMeaningRelationMeaningId;
	}

	public boolean isShowMeaningRelationWordDatasets() {
		return showMeaningRelationWordDatasets;
	}

	public void setShowMeaningRelationWordDatasets(boolean showMeaningRelationWordDatasets) {
		this.showMeaningRelationWordDatasets = showMeaningRelationWordDatasets;
	}

	public boolean isApproveMeaningEnabled() {
		return isApproveMeaningEnabled;
	}

	public void setApproveMeaningEnabled(boolean isApproveMeaningEnabled) {
		this.isApproveMeaningEnabled = isApproveMeaningEnabled;
	}

}
