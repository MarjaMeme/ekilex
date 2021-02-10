package eki.ekilex.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eki.common.constant.AuthorityItem;
import eki.common.constant.AuthorityOperation;
import eki.common.constant.OrderingField;
import eki.ekilex.constant.SystemConstant;
import eki.ekilex.data.Classifier;
import eki.ekilex.data.Dataset;
import eki.ekilex.data.DatasetPermission;
import eki.ekilex.data.EkiUser;
import eki.ekilex.data.EkiUserApplication;
import eki.ekilex.data.EkiUserPermData;
import eki.ekilex.service.db.PermissionDbService;
import eki.ekilex.service.db.UserDbService;

@Component
public class PermissionService implements SystemConstant {

	@Autowired
	private UserDbService userDbService;

	@Autowired
	private PermissionDbService permissionDbService;

	@Autowired
	private EmailService emailService;

	@Transactional
	public List<EkiUserPermData> getEkiUserPermissions(
			String userNameFilter, String userPermDatasetCodeFilter, Boolean userEnablePendingFilter, OrderingField orderBy) {

		List<EkiUserPermData> users = permissionDbService.getUsers(userNameFilter, userPermDatasetCodeFilter, userEnablePendingFilter, orderBy);
		for (EkiUserPermData user : users) {
			Long userId = user.getId();
			List<EkiUserApplication> userApplications = userDbService.getUserApplications(userId);
			List<DatasetPermission> datasetPermissions = permissionDbService.getDatasetPermissions(userId);
			user.setApplications(userApplications);
			user.setDatasetPermissions(datasetPermissions);
		}
		return users;
	}

	@Transactional
	public List<Dataset> getUserVisibleDatasets(Long userId) {
		if (userId == null) {
			return Collections.emptyList();
		}
		return permissionDbService.getUserVisibleDatasets(userId);
	}

	@Transactional
	public List<String> getUserVisibleDatasetCodes(Long userId) {
		if (userId == null) {
			return Collections.emptyList();
		}
		List<Dataset> userVisibleDatasets = permissionDbService.getUserVisibleDatasets(userId);
		List<String> userVisibleDatasetCodes = userVisibleDatasets.stream().map(Dataset::getCode).collect(Collectors.toList());
		return userVisibleDatasetCodes;
	}

	//FIXME ambiguous - read or crud?
	@Transactional
	public List<String> getUserPermDatasetCodes(Long userId) {
		if (userId == null) {
			return Collections.emptyList();
		}
		List<Dataset> userPermDatasets = permissionDbService.getUserPermDatasets(userId);
		List<String> userPermDatasetCodes = userPermDatasets.stream().map(Dataset::getCode).collect(Collectors.toList());
		return userPermDatasetCodes;
	}

	@Transactional
	public List<Dataset> getUserOwnedDatasets(Long userId) {
		if (userId == null) {
			return Collections.emptyList();
		}
		return permissionDbService.getUserOwnedDatasets(userId);
	}

	@Transactional
	public List<Classifier> getUserDatasetLanguages(Long userId, String datasetCode) {
		return permissionDbService.getUserDatasetLanguages(userId, datasetCode, CLASSIF_LABEL_LANG_EST, CLASSIF_LABEL_TYPE_DESCRIP);
	}

	@Transactional
	public void createDatasetPermission(Long userId, String datasetCode, AuthorityItem authItem, AuthorityOperation authOp, String authLang) {

		if (StringUtils.isBlank(datasetCode)) {
			return;
		}
		if (authItem == null) {
			return;
		}
		if (authOp == null) {
			return;
		}
		if (StringUtils.isBlank(authLang)) {
			authLang = null;
		}
		permissionDbService.createDatasetPermission(userId, datasetCode, authItem, authOp, authLang);
	}

	@Transactional
	public void deleteDatasetPermission(Long datasetPermissionId) {

		permissionDbService.deleteDatasetPermission(datasetPermissionId);
	}

	@Transactional
	public void sendPermissionsEmail(String userEmail, EkiUser sender) {

		EkiUser receiver = userDbService.getUserByEmail(userEmail);
		Long receiverId = receiver.getId();
		List<DatasetPermission> datasetPermissions = permissionDbService.getDatasetPermissions(receiverId);
		receiver.setDatasetPermissions(datasetPermissions);

		emailService.sendPermissionsEmail(receiver, sender);
	}

	@Transactional
	public List<DatasetPermission> getUserDatasetPermissions(Long userId) {
		return permissionDbService.getDatasetPermissions(userId);
	}
}
