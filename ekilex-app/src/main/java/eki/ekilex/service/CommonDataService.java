package eki.ekilex.service;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eki.common.constant.ClassifierName;
import eki.common.constant.GlobalConstant;
import eki.common.constant.TagType;
import eki.ekilex.constant.SystemConstant;
import eki.ekilex.data.Classifier;
import eki.ekilex.data.Dataset;
import eki.ekilex.data.Origin;
import eki.ekilex.service.db.CommonDataDbService;
import eki.ekilex.service.util.DatasetUtil;

// only common use data aggregation!
@Component
public class CommonDataService implements SystemConstant, GlobalConstant {

	@Autowired
	private CommonDataDbService commonDataDbService;

	@Autowired
	private DatasetUtil datasetUtil;

	@Transactional
	public List<Dataset> getAllDatasets() {
		return commonDataDbService.getAllDatasets();
	}

	@Transactional
	public List<Dataset> getVisibleDatasets() {
		List<Dataset> datasets = commonDataDbService.getVisibleDatasets();
		datasets = datasetUtil.resortPriorityDatasets(datasets);
		return datasets;
	}

	@Transactional
	public List<String> getVisibleDatasetCodes() {
		List<String> datasetCodes = getVisibleDatasets().stream().map(Dataset::getCode).collect(Collectors.toList());
		return datasetCodes;
	}

	@Transactional
	public List<Classifier> getLanguages() {
		return commonDataDbService.getLanguages(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public Map<String, String> getLanguageIso2Map() {
		return commonDataDbService.getLanguages(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_ISO2).stream()
				.collect(Collectors.toMap(Classifier::getCode, classifier -> StringUtils.isNotBlank(classifier.getValue()) ? classifier.getValue() : classifier.getCode()));
	}

	@Transactional
	public Map<String, List<Classifier>> getDomainsInUseByOrigin() {
		List<Classifier> domains = commonDataDbService.getDomainsInUse(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		return domains.stream().collect(groupingBy(Classifier::getOrigin));
	}

	@Transactional
	public Map<String, List<Classifier>> getDatasetDomainsByOrigin(String datasetCode) {
		List<Classifier> domains = commonDataDbService.getDatasetClassifiers(
				ClassifierName.DOMAIN, datasetCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
		Map<String, List<Classifier>> datasetDomainsByOrigin = domains.stream().collect(groupingBy(Classifier::getOrigin));
		return datasetDomainsByOrigin;
	}

	@Transactional
	public List<Classifier> getDomains(String origin) {
		List<Classifier> domains = commonDataDbService.getDomains(origin, CLASSIF_LABEL_TYPE_DESCRIP);
		return domains;
	}

	@Transactional
	public List<Classifier> getMorphs() {
		return commonDataDbService.getMorphs(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getGenders() {
		return commonDataDbService.getGenders(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getWordTypes() {
		return commonDataDbService.getWordTypes(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getAspects() {
		return commonDataDbService.getAspects(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getWordRelationTypes() {
		return commonDataDbService.getWordRelationTypes(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getLexemeRelationTypes() {
		return commonDataDbService.getLexemeRelationTypes(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getMeaningRelationTypes() {
		return commonDataDbService.getMeaningRelationTypes(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getDefinitionTypes() {
		return commonDataDbService.getDefinitionTypes(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getPoses() {
		return commonDataDbService.getPoses(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getRegisters() {
		return commonDataDbService.getRegisters(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getRegions() {
		return commonDataDbService.getRegions();
	}

	@Transactional
	public List<Classifier> getDerivs() {
		return commonDataDbService.getDerivs(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getValueStates() {
		return commonDataDbService.getValueStates(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getProficiencyLevels() {
		return commonDataDbService.getProficiencyLevels(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getSemanticTypes() {
		return commonDataDbService.getSemanticTypes(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getDisplayMorphs() {
		return commonDataDbService.getDisplayMorphs(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getGovernmentTypes() {
		return commonDataDbService.getGovernmentTypes(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getEtymologyTypes() {
		return commonDataDbService.getEtymologyTypes();
	}

	@Transactional
	public List<Classifier> getPosGroups() {
		return commonDataDbService.getPosGroups(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getUsageTypes() {
		return commonDataDbService.getUsageTypes(CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Classifier> getClassifiers(ClassifierName classifierName) {
		if (classifierName == null) {
			return null;
		}
		if (ClassifierName.LANGUAGE.equals(classifierName)) {
			return getLanguages();
		}
		if (ClassifierName.MORPH.equals(classifierName)) {
			return getMorphs();
		}
		if (ClassifierName.GENDER.equals(classifierName)) {
			return getGenders();
		}
		if (ClassifierName.WORD_TYPE.equals(classifierName)) {
			return getWordTypes();
		}
		if (ClassifierName.ASPECT.equals(classifierName)) {
			return getAspects();
		}
		if (ClassifierName.WORD_REL_TYPE.equals(classifierName)) {
			return getWordRelationTypes();
		}
		if (ClassifierName.LEX_REL_TYPE.equals(classifierName)) {
			return getLexemeRelationTypes();
		}
		if (ClassifierName.MEANING_REL_TYPE.equals(classifierName)) {
			return getMeaningRelationTypes();
		}
		if (ClassifierName.DEFINITION_TYPE.equals(classifierName)) {
			return getDefinitionTypes();
		}
		if (ClassifierName.POS.equals(classifierName)) {
			return getPoses();
		}
		if (ClassifierName.REGISTER.equals(classifierName)) {
			return getRegisters();
		}
		if (ClassifierName.REGION.equals(classifierName)) {
			return getRegions();
		}
		if (ClassifierName.DERIV.equals(classifierName)) {
			return getDerivs();
		}
		if (ClassifierName.VALUE_STATE.equals(classifierName)) {
			return getValueStates();
		}
		if (ClassifierName.PROFICIENCY_LEVEL.equals(classifierName)) {
			return getProficiencyLevels();
		}
		if (ClassifierName.SEMANTIC_TYPE.equals(classifierName)) {
			return getSemanticTypes();
		}
		if (ClassifierName.DISPLAY_MORPH.equals(classifierName)) {
			return getDisplayMorphs();
		}
		if (ClassifierName.GOVERNMENT_TYPE.equals(classifierName)) {
			return getGovernmentTypes();
		}
		if (ClassifierName.ETYMOLOGY_TYPE.equals(classifierName)) {
			return getEtymologyTypes();
		}
		if (ClassifierName.POS_GROUP.equals(classifierName)) {
			return getPosGroups();
		}
		if (ClassifierName.USAGE_TYPE.equals(classifierName)) {
			return getUsageTypes();
		}
		return null;
	}

	@Transactional
	public List<Classifier> getDatasetLanguages(String datasetCode) {
		return commonDataDbService.getDatasetClassifiers(ClassifierName.LANGUAGE, datasetCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public List<Origin> getDomainOrigins() {
		List<Origin> allDomainOrigins = commonDataDbService.getDomainOrigins();
		//hack to beautify specific code into label
		final String ekiCodePrefix = "eki ";
		final String ekiLabelPrefix = "EKI ";
		for (Origin origin : allDomainOrigins) {
			String code = origin.getCode();
			String label = origin.getLabel();
			if (StringUtils.isBlank(label)) {
				if (StringUtils.startsWith(code, ekiCodePrefix)) {
					label = StringUtils.replace(code, ekiCodePrefix, ekiLabelPrefix);
				} else {
					label = code;
				}
				origin.setLabel(label);
			}
		}
		return allDomainOrigins;
	}

	@Transactional
	public List<String> getAllTags() {
		return commonDataDbService.getTags();
	}

	@Transactional
	public List<String> getLexemeTags() {
		return commonDataDbService.getTags(TagType.LEX.name());
	}

	@Transactional
	public List<String> getMeaningTags() {
		return commonDataDbService.getTags(TagType.MEANING.name());
	}
}
