package eki.ekilex.web.util;

import static java.util.Arrays.asList;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eki.ekilex.constant.SearchEntity;
import eki.ekilex.constant.SearchKey;
import eki.ekilex.constant.SearchOperand;
import eki.ekilex.constant.SystemConstant;
import eki.ekilex.constant.WebConstant;
import eki.ekilex.data.SearchCriterion;
import eki.ekilex.data.SearchCriterionGroup;
import eki.ekilex.data.SearchFilter;
import eki.ekilex.data.SearchUriData;
import eki.ekilex.service.CommonDataService;

@Component
public class SearchHelper {

	private static final char PATH_SEPARATOR = '/';
	private static final char DICTONARIES_SEPARATOR = ',';
	private static final String EMPTY_VALUE = "-";
	private static final String SEARCH_MODE = "smode";
	private static final String DICTIONARIES = "dicts";
	private static final String SIMPLE_SEARCH_FILTER = "sfilt";
	private static final String DETAIL_SEARCH_FILTER = "dfilt";
	private static final String CRITERIA_GROUP = "critgr";
	private static final String CRITERION = "crit";
	private static final String FETCH_ALL = "fetchall";

	@Autowired
	protected CommonDataService commonDataService;

	public String composeSearchUri(List<String> datasets, String simpleSearchFilter) {
		return composeSearchUri(WebConstant.SEARCH_MODE_SIMPLE, datasets, simpleSearchFilter, null, false);
	}

	public String composeSearchUri(String searchMode, List<String> datasets, String simpleSearchFilter, SearchFilter detailSearchFilter, boolean fetchAll) {

		StringBuffer uriBuf = new StringBuffer();

		// search mode
		uriBuf.append(PATH_SEPARATOR);
		uriBuf.append(SEARCH_MODE);
		uriBuf.append(PATH_SEPARATOR);
		uriBuf.append(searchMode);

		// datasets
		if (CollectionUtils.isNotEmpty(datasets)) {
			List<String> allDatasets = commonDataService.getDatasetCodes();
			Collection<String> datasetComparison = CollectionUtils.disjunction(datasets, allDatasets);
			if (CollectionUtils.isNotEmpty(datasetComparison)) {
				String dictonaries = StringUtils.join(datasets, DICTONARIES_SEPARATOR);
				uriBuf.append(PATH_SEPARATOR);
				uriBuf.append(DICTIONARIES);
				uriBuf.append(PATH_SEPARATOR);
				uriBuf.append(dictonaries);
			}
		}

		// search crit
		if (StringUtils.equals(WebConstant.SEARCH_MODE_SIMPLE, searchMode) && StringUtils.isNotBlank(simpleSearchFilter)) {
			uriBuf.append(PATH_SEPARATOR);
			uriBuf.append(SIMPLE_SEARCH_FILTER);
			uriBuf.append(PATH_SEPARATOR);
			uriBuf.append(simpleSearchFilter);
		} else if (StringUtils.equals(WebConstant.SEARCH_MODE_DETAIL, searchMode) && (detailSearchFilter != null)) {
			List<SearchCriterionGroup> criteriaGroups = detailSearchFilter.getCriteriaGroups();
			if (CollectionUtils.isNotEmpty(criteriaGroups)) {
				uriBuf.append(PATH_SEPARATOR);
				uriBuf.append(DETAIL_SEARCH_FILTER);
				for (SearchCriterionGroup criteriaGroup : criteriaGroups) {
					uriBuf.append(PATH_SEPARATOR);
					uriBuf.append(CRITERIA_GROUP);
					uriBuf.append(PATH_SEPARATOR);
					uriBuf.append(criteriaGroup.getEntity().name());
					for (SearchCriterion searchCriterion : criteriaGroup.getSearchCriteria()) {
						uriBuf.append(PATH_SEPARATOR);
						uriBuf.append(CRITERION);
						uriBuf.append(PATH_SEPARATOR);
						uriBuf.append(searchCriterion.getSearchKey().name());
						uriBuf.append(PATH_SEPARATOR);
						uriBuf.append(searchCriterion.getSearchOperand().name());
						uriBuf.append(PATH_SEPARATOR);
						if (searchCriterion.getSearchValue() == null) {
							uriBuf.append(EMPTY_VALUE);
						} else {
							String critValue = searchCriterion.getSearchValue().toString();
							if (StringUtils.isEmpty(critValue)) {
								uriBuf.append(EMPTY_VALUE);
							} else {
								critValue = encode(critValue);
								uriBuf.append(critValue);
							}
						}
					}
				}
			}
		}

		// fetch all
		if (fetchAll) {
			uriBuf.append(PATH_SEPARATOR);
			uriBuf.append(FETCH_ALL);
		}

		return uriBuf.toString();
	}

	public SearchUriData parseSearchUri(String searchUri) {

		boolean isValid;
		String searchMode = null;
		List<String> selectedDatasets = null;
		String simpleSearchFilter = null;
		SearchFilter detailSearchFilter = null;
		boolean fetchAll = false;

		String[] uriParts = StringUtils.split(searchUri, PATH_SEPARATOR);

		for (int uriPartIndex = 0; uriPartIndex < uriParts.length; uriPartIndex++) {
			String uriPart = uriParts[uriPartIndex];
			if (StringUtils.equals(FETCH_ALL, uriPart)) {
				fetchAll = true;
			}
			if (uriPartIndex == uriParts.length - 1) {
				break;
			}
			if (StringUtils.equals(SEARCH_MODE, uriPart)) {
				searchMode = uriParts[uriPartIndex + 1];
			} else if (StringUtils.equals(DICTIONARIES, uriPart)) {
				String selectedDatasetsStr = uriParts[uriPartIndex + 1];
				selectedDatasetsStr = StringUtils.remove(selectedDatasetsStr, ' ');
				selectedDatasets = Arrays.asList(StringUtils.split(selectedDatasetsStr, DICTONARIES_SEPARATOR));
			} else if (StringUtils.equals(SIMPLE_SEARCH_FILTER, uriPart)) {
				simpleSearchFilter = uriParts[uriPartIndex + 1];
			} else if (StringUtils.equals(DETAIL_SEARCH_FILTER, uriPart)) {
				detailSearchFilter = new SearchFilter();
				detailSearchFilter.setCriteriaGroups(new ArrayList<>());
			} else if (StringUtils.equals(CRITERIA_GROUP, uriPart)) {
				String searchEntityStr = uriParts[uriPartIndex + 1];
				SearchEntity entity = null;
				try {
					entity = SearchEntity.valueOf(searchEntityStr.toUpperCase());
				} catch (Exception e) {
					break;
				}
				SearchCriterionGroup criterionGroup = new SearchCriterionGroup();
				criterionGroup.setEntity(entity);
				criterionGroup.setSearchCriteria(new ArrayList<>());
				List<SearchCriterionGroup> criteriaGroups = detailSearchFilter.getCriteriaGroups();
				criteriaGroups.add(criterionGroup);
			} else if (StringUtils.equals(CRITERION, uriPart)) {
				if (uriPartIndex > uriParts.length - 4) {
					break;
				}
				// crit key
				String searchKeyStr = uriParts[uriPartIndex + 1];
				SearchKey searchKey = null;
				try {
					searchKey = SearchKey.valueOf(searchKeyStr.toUpperCase());
				} catch (Exception e) {
					break;
				}
				// crit operand
				String searchOperandStr = uriParts[uriPartIndex + 2];
				SearchOperand searchOperand = null;
				try {
					searchOperand = SearchOperand.valueOf(searchOperandStr.toUpperCase());
				} catch (Exception e) {
					break;
				}
				// crit value
				String searchValue = uriParts[uriPartIndex + 3];
				searchValue = decode(searchValue);
				if (StringUtils.equals(EMPTY_VALUE, searchValue)) {
					searchValue = null;
				}
				SearchCriterion criterion = new SearchCriterion();
				criterion.setSearchKey(searchKey);
				criterion.setSearchOperand(searchOperand);
				criterion.setSearchValue(searchValue);
				List<SearchCriterionGroup> criteriaGroups = detailSearchFilter.getCriteriaGroups();
				SearchCriterionGroup criterionGroup = criteriaGroups.get(criteriaGroups.size() - 1);
				criterionGroup.getSearchCriteria().add(criterion);
			}
		}
		isValid = StringUtils.isNotBlank(simpleSearchFilter) || (detailSearchFilter != null && CollectionUtils.isNotEmpty(detailSearchFilter.getCriteriaGroups()));
		if (CollectionUtils.isEmpty(selectedDatasets)) {
			selectedDatasets = commonDataService.getDatasetCodes();
		}
		if (detailSearchFilter == null) {
			detailSearchFilter = initSearchFilter();
		}
		return new SearchUriData(isValid, searchMode, selectedDatasets, simpleSearchFilter, detailSearchFilter, fetchAll);
	}

	public SearchFilter initSearchFilter() {

		SearchFilter detailSearch = new SearchFilter();
		SearchCriterion defaultCriterion = new SearchCriterion();
		defaultCriterion.setSearchKey(SearchKey.VALUE);
		defaultCriterion.setSearchOperand(SearchKey.VALUE.getOperands()[0]);
		SearchCriterionGroup searchGroup = new SearchCriterionGroup();
		searchGroup.setEntity(SearchEntity.HEADWORD);
		searchGroup.setSearchCriteria(asList(defaultCriterion));
		detailSearch.setCriteriaGroups(asList(searchGroup));
		return detailSearch;
	}

	private String encode(String value) {
		try {
			value = URLEncoder.encode(value, SystemConstant.UTF_8);
		} catch (UnsupportedEncodingException e) {
		}
		return value;
	}

	private String decode(String value) {
		try {
			value = URLDecoder.decode(value, SystemConstant.UTF_8);
		} catch (UnsupportedEncodingException e) {
		}
		return value;
	}
}
