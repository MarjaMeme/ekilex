package eki.ekilex.service.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import eki.common.constant.GlobalConstant;

@Component
public class MessageUtil implements InitializingBean, GlobalConstant {

	private static final String MOTIVATIONAL_TXT_FILE_PATH = "messages/motivational.txt";

	private List<String> positiveQuotes;

	@Override
	public void afterPropertiesSet() throws Exception {
		ClassPathResource fileResource = new ClassPathResource(MOTIVATIONAL_TXT_FILE_PATH);
		File txtFile = fileResource.getFile();
		FileInputStream txtFileStream = new FileInputStream(txtFile);
		positiveQuotes = IOUtils.readLines(txtFileStream, UTF_8);
		txtFileStream.close();
	}

	public synchronized String getPositiveQuote() {
		Collections.shuffle(positiveQuotes);
		return positiveQuotes.get(0);
	}
}
