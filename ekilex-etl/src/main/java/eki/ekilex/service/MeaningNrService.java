package eki.ekilex.service;

import static eki.common.constant.TableName.MEANING_NR;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eki.common.constant.GlobalConstant;
import eki.common.constant.LoaderConstant;
import eki.common.exception.DataLoadingException;
import eki.common.service.db.BasicDbService;
import eki.ekilex.data.transform.Mnr;

@Component
public class MeaningNrService implements LoaderConstant, GlobalConstant {

	private final static String MAIN_DATASET = "ss1";

	private static Logger logger = LoggerFactory.getLogger(MeaningNrService.class);

	@Autowired
	protected BasicDbService basicDbService;

	private Map<String, List<Mnr>> ssMnrMap = Collections.emptyMap();

	public void init(Properties loaderConf) throws Exception {
		logger.debug("Meaning MNR service starting up...");
		ssMnrMap = loadSsMnrMap(loaderConf);
		logger.debug("Meaning MNR service ready!");
	}

	public Long getMappedMeaning(String meaningNr) {
		Long meaningId = null;
		if (isBlank(meaningNr)) {
			return meaningId;
		}
		if (ssMnrMap.containsKey(meaningNr)) {
			List<Mnr> mnrs = ssMnrMap.get(meaningNr);
			String wordValue = mnrs.get(0).getWord();
			if (mnrs.size() > 1) {
				logger.debug("More than one meaning mapped from SS1 : {}", wordValue);
			}
			try {
				String mnr = mnrs.get(0).getValue();
				List<Map<String, Object>> meaningNrs = getMeaningNrs(mnr, MAIN_DATASET);
				if (!meaningNrs.isEmpty()) {
					meaningId =  (Long) meaningNrs.get(0).get("meaning_id");
					if (meaningNrs.size() > 1) {
						logger.debug("More than one meaning found in Ekilex : {} : mnr {}", wordValue, mnr);
					}
				}
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		return meaningId;
	}

	public void storeMeaningNr(Long meaningId, String mnr, String dataset) throws Exception {

		Map<String, Object> tableRowParamMap = new HashMap<>();
		tableRowParamMap.put("meaning_id", meaningId);
		tableRowParamMap.put("mnr", mnr.toLowerCase());
		tableRowParamMap.put("dataset_code", dataset);
		basicDbService.createIfNotExists(MEANING_NR, tableRowParamMap);
	}

	private List<Map<String, Object>> getMeaningNrs(String meaningNr, String dataset) throws Exception {

		Map<String, Object> tableRowParamMap = new HashMap<>();
		tableRowParamMap.put("mnr", meaningNr.toLowerCase());
		tableRowParamMap.put("dataset_code", dataset);
		return basicDbService.selectAll(MEANING_NR, tableRowParamMap);
	}

	private Map<String, List<Mnr>> loadSsMnrMap(Properties loaderConf) throws Exception {

		String ssMnrMapFilePath = loaderConf.getProperty("ss1.mnr.map.file");
		if (isBlank(ssMnrMapFilePath)) {
			return null;
		}

		InputStream resourceInputStream = new FileInputStream(ssMnrMapFilePath);
		List<String> resourceFileLines = IOUtils.readLines(resourceInputStream, UTF_8);
		resourceInputStream.close();
		Map<String, List<Mnr>> ssMnrMap = new HashMap<>();
		List<Mnr> mappedMnrs;
		Mnr mnr;
		for (String resourceFileLine : resourceFileLines) {
			if (isBlank(resourceFileLine)) {
				continue;
			}
			String[] meaningMapParts = StringUtils.split(resourceFileLine, CSV_SEPARATOR);
			if (meaningMapParts.length != 3) {
				throw new DataLoadingException("Invalid mnr map line \"" + resourceFileLine + "\"");
			}
			String sourceMnr = meaningMapParts[0];
			String targetMnr = meaningMapParts[1];
			String word = meaningMapParts[2];
			mappedMnrs = ssMnrMap.computeIfAbsent(sourceMnr, k -> new ArrayList<>());
			mnr = new Mnr();
			mnr.setValue(targetMnr);
			mnr.setWord(word);
			if (!mappedMnrs.contains(mnr)) {
				mappedMnrs.add(mnr);
			}
		}
		return ssMnrMap;
	}

}
