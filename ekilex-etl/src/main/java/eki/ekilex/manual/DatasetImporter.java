package eki.ekilex.manual;

import java.io.File;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eki.common.service.TransportService;
import eki.ekilex.runner.DatasetImportValidator;
import eki.ekilex.runner.DatasetImporterRunner;

@Deprecated
public class DatasetImporter extends AbstractLoader {

	private static Logger logger = LoggerFactory.getLogger(DatasetImporter.class);

	private static final String OP_TABLES = "tables";

	private static final String OP_VALIDATE = "validate";

	private static final String OP_IMPORT = "import";

	private static final String OP_CREATE = "create";

	private static final String OP_APPEND = "append";

	//mvn exec:java -P<profile> -Dexec.args="tables"
	//mvn exec:java -P<profile> -Dexec.args="validate <import file path>"
	//mvn exec:java -P<profile> -Dexec.args="import <create|append> <import file path>"
	public static void main(String[] args) {
		if (validateArgs(args)) {
			new DatasetImporter().execute(args);
		} else {
			logger.error("Please provide arguments:");
			logger.error("tables");
			logger.error("or");
			logger.error("validate <import file path>");
			logger.error("or");
			logger.error("import <create|append> <import file path>");
		}
	}

	@Override
	void execute(String[] args) {
		try {
			initDefault();
			DatasetImportValidator validator = getComponent(DatasetImportValidator.class);
			DatasetImporterRunner runner = getComponent(DatasetImporterRunner.class);
			TransportService transportService = getComponent(TransportService.class);
			transportService.initialise();

			String operation = args[0];
			if (StringUtils.equals(OP_TABLES, operation)) {
				validator.printTablesDescription();
			} else if (StringUtils.equals(OP_VALIDATE, operation)) {
				String importFilePath = args[1];
				validator.validate(importFilePath);
			} else if (StringUtils.equals(OP_IMPORT, operation)) {
				String importType = args[1];
				String importFilePath = args[2];
				boolean isAppend = StringUtils.equals(OP_APPEND, importType);
				boolean isCreate = !isAppend;
				runner.execute(isCreate, isAppend, importFilePath);
			}
		} catch (Exception e) {
			logger.error("Unexpected behaviour of the system", e);
		} finally {
			shutdown();
		}
	}

	private static boolean validateArgs(String[] args) {
		if (ArrayUtils.isEmpty(args)) {
			return false;
		}
		String operation = args[0];
		if (StringUtils.equals(OP_TABLES, operation)) {
			return true;
		}
		if (StringUtils.equals(OP_VALIDATE, operation)) {
			if (args.length == 2) {
				String importFilePath = args[1];
				boolean fileExists = isValidFilePath(importFilePath);
				if (fileExists) {
					return true;
				} else {
					logger.error("Import file path is incorrect");
					return false;
				}
			}
			return false;
		}
		if (StringUtils.equals(OP_IMPORT, operation)) {
			if (args.length == 3) {
				String importType = args[1];
				if (!ArrayUtils.contains(new String[] {OP_CREATE, OP_APPEND}, importType)) {
					logger.error("Import type is incorrect");
					return false;
				}
				String importFilePath = args[2];
				boolean fileExists = isValidFilePath(importFilePath);
				if (fileExists) {
					return true;
				} else {
					logger.error("Import file path is incorrect");
					return false;
				}
			}
			return false;
		}
		return false;
	}

	private static boolean isValidFilePath(String importFilePath) {
		File importFile = new File(importFilePath);
		boolean fileExists = importFile.exists();
		return fileExists;
	}
}
