package eki.ekilex.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import eki.common.constant.GlobalConstant;
import eki.common.constant.LoaderConstant;
import eki.common.exception.DataLoadingException;
import eki.ekilex.data.transform.DatasetId;
import eki.ekilex.data.transform.Guid;
import eki.ekilex.data.transform.Mnr;

@Component
public class LoaderConfService implements InitializingBean, LoaderConstant, GlobalConstant {

	private static final String TERMEKI_KEY_PART = ".termeki.";

	@Autowired
	private ResourceLoader resourceLoader;

	private Properties loaderConf;

	@Override
	public void afterPropertiesSet() throws Exception {
		Resource loaderConfResource = resourceLoader.getResource("ultima-loader.properties");
		InputStream confStream = loaderConfResource.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(confStream, Charset.forName(UTF_8));
		loaderConf = new Properties();
		loaderConf.load(inputStreamReader);
		inputStreamReader.close();
		confStream.close();
	}

	public boolean doReports() {
		String doReportsStr = loaderConf.getProperty("doreports");
		boolean doReports = Boolean.valueOf(doReportsStr);
		return doReports;
	}

	public boolean isFullReload() {
		String isFullReloadStr = loaderConf.getProperty("isfullreload");
		boolean isFullReload = Boolean.valueOf(isFullReloadStr);
		return isFullReload;
	}

	public String getMandatoryConfProperty(String key) throws Exception {
		String propertyValue = loaderConf.getProperty(key);
		if (StringUtils.isBlank(propertyValue)) {
			throw new DataLoadingException("Missing mandatory property \"" + key + "\"");
		}
		return propertyValue;
	}

	public String getConfProperty(String key) {
		return loaderConf.getProperty(key);
	}

	public String[] getMabDataFilePaths() {
		String[] mabDataFilePathKeys = new String[] {
				"mab.data.file.1",
				"mab.data.file.2",
				"mab.data.file.3",
				"mab.data.file.4"
		};
		String[] dataFilePaths = new String[mabDataFilePathKeys.length];
		for (int dataFilePathKeyIndex = 0; dataFilePathKeyIndex < mabDataFilePathKeys.length; dataFilePathKeyIndex++) {
			dataFilePaths[dataFilePathKeyIndex] = getConfProperty(mabDataFilePathKeys[dataFilePathKeyIndex]);
		}
		return dataFilePaths;
	}

	public Map<String, List<Guid>> getSsGuidMapFor(String filteringDataset) throws Exception {

		String ssGuidMapFilePath1 = loaderConf.getProperty("ss1.guid.map.file.1");
		String ssGuidMapFilePath2 = loaderConf.getProperty("ss1.guid.map.file.2");
		if (StringUtils.isBlank(ssGuidMapFilePath1)) {
			return null;
		}
		if (StringUtils.isBlank(ssGuidMapFilePath2)) {
			return null;
		}
		String[] ssGuidMapFilePaths = new String[] {ssGuidMapFilePath1, ssGuidMapFilePath2};

		Map<String, List<Guid>> ssGuidMap = new HashMap<>();
		InputStream resourceInputStream;
		List<String> resourceFileLines;
		List<Guid> mappedGuids;
		Guid guidObj;
		for (String ssGuidMapFilePath : ssGuidMapFilePaths) {
			resourceInputStream = new FileInputStream(ssGuidMapFilePath);
			resourceFileLines = IOUtils.readLines(resourceInputStream, UTF_8);
			resourceInputStream.close();
			for (String resourceFileLine : resourceFileLines) {
				if (StringUtils.isBlank(resourceFileLine)) {
					continue;
				}
				String[] ssGuidMapRowCells = StringUtils.split(resourceFileLine, CSV_SEPARATOR);
				if (ssGuidMapRowCells.length != 4) {
					throw new DataLoadingException("Invalid guid map line \"" + resourceFileLine + "\"");
				}
				String sourceDataset = correctDatasetCode(ssGuidMapRowCells[0]);
				String sourceGuid = ssGuidMapRowCells[1].toLowerCase();
				String targetGuid = ssGuidMapRowCells[2].toLowerCase();
				String word = ssGuidMapRowCells[3];
				word = RegExUtils.removePattern(word, "[&]\\w+[;]");//remove eki markup 
				word = RegExUtils.removePattern(word, "<[^>]*>");//remove html
				word = StringUtils.removeStart(word, "-");//suffixoid
				word = StringUtils.removeEnd(word, "-");//prefixoid
				if (StringUtils.equals(sourceDataset, filteringDataset)) {
					mappedGuids = ssGuidMap.get(sourceGuid);
					if (mappedGuids == null) {
						mappedGuids = new ArrayList<>();
						ssGuidMap.put(sourceGuid, mappedGuids);
					}
					guidObj = new Guid();
					guidObj.setValue(targetGuid);
					guidObj.setWord(word);
					if (!mappedGuids.contains(guidObj)) {
						mappedGuids.add(guidObj);
					}
				}
			}
		}
		return ssGuidMap;
	}

	public Map<String, List<Mnr>> getSsMnrMap() throws Exception {

		String ssMnrMapFilePath = loaderConf.getProperty("ss1.mnr.map.file");
		if (StringUtils.isBlank(ssMnrMapFilePath)) {
			return null;
		}

		InputStream resourceInputStream = new FileInputStream(ssMnrMapFilePath);
		List<String> resourceFileLines = IOUtils.readLines(resourceInputStream, UTF_8);
		resourceInputStream.close();
		Map<String, List<Mnr>> ssMnrMap = new HashMap<>();
		List<Mnr> mappedMnrs;
		Mnr mnr;
		for (String resourceFileLine : resourceFileLines) {
			if (StringUtils.isBlank(resourceFileLine)) {
				continue;
			}
			String[] ssMnrMapRowCells = StringUtils.split(resourceFileLine, CSV_SEPARATOR);
			if (ssMnrMapRowCells.length != 3) {
				throw new DataLoadingException("Invalid mnr map line \"" + resourceFileLine + "\"");
			}
			String sourceMnr = ssMnrMapRowCells[0];
			String targetMnr = ssMnrMapRowCells[1];
			String word = ssMnrMapRowCells[2];
			mappedMnrs = ssMnrMap.get(sourceMnr);
			if (mappedMnrs == null) {
				mappedMnrs = new ArrayList<>();
				ssMnrMap.put(sourceMnr, mappedMnrs);
			}
			mnr = new Mnr();
			mnr.setValue(targetMnr);
			mnr.setWord(word);
			if (!mappedMnrs.contains(mnr)) {
				mappedMnrs.add(mnr);
			}
		}
		return ssMnrMap;
	}

	private String correctDatasetCode(String datasetCode) {
		if (StringUtils.equals(datasetCode, "qqv")) {
			return "qq2";
		}
		if (StringUtils.equals(datasetCode, "evs")) {
			return "ev2";
		}
		return datasetCode;
	}

	public List<DatasetId> getTermekiDatasetIds() {
		List<DatasetId> termekiIds = new ArrayList<>();
		Enumeration<Object> loaderConfKeys = loaderConf.keys();
		while (loaderConfKeys.hasMoreElements()) {
			String loaderConfKey = loaderConfKeys.nextElement().toString();
			if (StringUtils.contains(loaderConfKey, TERMEKI_KEY_PART)) {
				String termekiDataset = StringUtils.substringBefore(loaderConfKey, TERMEKI_KEY_PART);
				String termekiIdStr = loaderConf.getProperty(loaderConfKey);
				if (StringUtils.isNotEmpty(termekiIdStr)) {
					Integer termekiId = Integer.valueOf(termekiIdStr);
					DatasetId datasetId = new DatasetId(termekiDataset, termekiId);
					termekiIds.add(datasetId);
				}
			}
		}
		return termekiIds;
	}

	public List<String> getLexemeMergeDatasets() throws Exception {
		String lexemeMergeDatasetsStr = getMandatoryConfProperty("lex.merge.dataset");
		String[] lexemeMergeDatasetsArr = StringUtils.split(lexemeMergeDatasetsStr);
		return Arrays.asList(lexemeMergeDatasetsArr);
	}
}
