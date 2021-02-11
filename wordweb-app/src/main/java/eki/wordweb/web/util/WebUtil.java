package eki.wordweb.web.util;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriUtils;

import eki.common.constant.GlobalConstant;
import eki.wordweb.constant.WebConstant;

@Component
public class WebUtil implements WebConstant, GlobalConstant {

	private static final String MEANING_ID_URL_PLACEHOLDER = "{meaningId}";

	@Value("${ekilex.limterm.details.url}")
	private String ekilexLimTermDetailsUrl;

	public String composeDetailSearchUri(String destinLangsStr, String datasetCodesStr, String word, Integer homonymNr) {
		String encodedWord = encode(word);
		String encodedDatasetCodesStr = encodeSeparatedValuesStr(datasetCodesStr);
		String searchUri = StringUtils.join(SEARCH_URI, UNIF_URI, '/', destinLangsStr, '/', encodedDatasetCodesStr, '/', encodedWord);
		if (homonymNr != null) {
			searchUri += "/" + homonymNr;
		}
		return searchUri;
	}

	public String composeSimpleSearchUri(String destinLangsStr, String word, Integer homonymNr) {
		String encodedWord = encode(word);
		String searchUri = StringUtils.join(SEARCH_URI, LITE_URI, '/', destinLangsStr, '/', encodedWord);
		if (homonymNr != null) {
			searchUri += "/" + homonymNr;
		}
		return searchUri;
	}

	public String composeDatasetFirstLetterSearchUri(String datasetCode, Character firstLetter) {
		String uri = StringUtils.join(DATASET_HOME_URI, '/', datasetCode, '/', firstLetter);
		return uri;
	}

	public String composeEkilexLimTermDetailsUrl(Long meaningId) {
		String limTermDetailsUrl = StringUtils.replace(ekilexLimTermDetailsUrl, MEANING_ID_URL_PLACEHOLDER, String.valueOf(meaningId));
		return limTermDetailsUrl;
	}

	public String getEkilexLimTermSearchUrl() {
		String limTermSearchUrl = StringUtils.substringBefore(ekilexLimTermDetailsUrl, "?");
		return limTermSearchUrl;
	}

	private String encodeSeparatedValuesStr(String separatedValuesStr) {
		String[] valuesArr = StringUtils.split(separatedValuesStr, UI_FILTER_VALUES_SEPARATOR);
		String encodedSeparatedValuesStr = Arrays.stream(valuesArr)
				.map(value -> UriUtils.encode(value, UTF_8))
				.collect(Collectors.joining(String.valueOf(UI_FILTER_VALUES_SEPARATOR)));
		return encodedSeparatedValuesStr;
	}

	private String encode(String value) {
		value = value.replace("/", ENCODE_SYM_SLASH).replace("%", ENCODE_SYM_PERCENT).replace("\\", ENCODE_SYM_BACKSLASH);
		return UriUtils.encode(value, UTF_8);
	}
}
