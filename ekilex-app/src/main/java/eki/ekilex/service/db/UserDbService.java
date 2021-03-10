package eki.ekilex.service.db;

import static eki.ekilex.data.db.Tables.EKI_USER;
import static eki.ekilex.data.db.Tables.EKI_USER_APPLICATION;
import static eki.ekilex.data.db.Tables.TERMS_OF_USE;

import java.util.List;
import java.util.Optional;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eki.common.service.db.AbstractDbService;
import eki.ekilex.data.EkiUser;
import eki.ekilex.data.EkiUserApplication;
import eki.ekilex.data.db.tables.records.EkiUserRecord;

@Component
public class UserDbService extends AbstractDbService {

	@Autowired
	private DSLContext create;

	public Long createUser(String email, String name, String password, String activationKey, String termsVer) {
		EkiUserRecord ekiUser = create.newRecord(EKI_USER);
		ekiUser.setEmail(email);
		ekiUser.setName(name);
		ekiUser.setPassword(password);
		ekiUser.setActivationKey(activationKey);
		ekiUser.setTermsVer(termsVer);
		ekiUser.store();
		return ekiUser.getId();
	}

	public EkiUser getUserByEmail(String email) {

		Condition where = EKI_USER.EMAIL.eq(email);
		return getUser(where);
	}

	public EkiUser getUserByApiKey(String apiKey) {

		Condition where = EKI_USER.API_KEY.eq(apiKey);
		return getUser(where);
	}

	private EkiUser getUser(Condition where) {

		Field<Boolean> termsAgreed = DSL
				.select(DSL.field(DSL.count(TERMS_OF_USE.ID).eq(1)))
				.from(TERMS_OF_USE)
				.where(TERMS_OF_USE.VERSION.eq(EKI_USER.TERMS_VER).and(TERMS_OF_USE.IS_ACTIVE.isTrue()))
				.asField();

		return create
				.select(
						EKI_USER.ID,
						EKI_USER.NAME,
						EKI_USER.EMAIL,
						EKI_USER.PASSWORD,
						EKI_USER.ACTIVATION_KEY,
						EKI_USER.RECOVERY_KEY,
						EKI_USER.API_KEY,
						EKI_USER.IS_API_CRUD.as("api_crud"),
						EKI_USER.IS_ADMIN.as("admin"),
						EKI_USER.IS_MASTER.as("master"),
						EKI_USER.IS_ENABLED.as("enabled"),
						termsAgreed.as("active_terms_agreed"))
				.from(EKI_USER)
				.where(where)
				.fetchOptionalInto(EkiUser.class)
				.orElse(null);
	}

	public Long getUserIdByEmail(String email) {

		return create
				.select(EKI_USER.ID)
				.from(EKI_USER)
				.where(EKI_USER.EMAIL.eq(email))
				.fetchOptionalInto(Long.class)
				.orElse(null);
	}

	public String getUserEmailByRecoveryKey(String recoveryKey) {

		return create
				.select(EKI_USER.EMAIL)
				.from(EKI_USER)
				.where(EKI_USER.RECOVERY_KEY.eq(recoveryKey))
				.fetchOptionalInto(String.class)
				.orElse(null);
	}

	public String getActiveTermsValue() {

		return create
				.select(TERMS_OF_USE.VALUE)
				.from(TERMS_OF_USE)
				.where(TERMS_OF_USE.IS_ACTIVE.isTrue())
				.fetchSingleInto(String.class);
	}

	public String getActiveTermsVersion() {

		return create
				.select(TERMS_OF_USE.VERSION)
				.from(TERMS_OF_USE)
				.where(TERMS_OF_USE.IS_ACTIVE.isTrue())
				.fetchSingleInto(String.class);
	}

	public void agreeActiveTerms(Long userId) {

		create
				.update(EKI_USER)
				.set(EKI_USER.TERMS_VER, TERMS_OF_USE.VERSION)
				.from(TERMS_OF_USE)
				.where(
						EKI_USER.ID.eq(userId)
								.and(TERMS_OF_USE.IS_ACTIVE.isTrue()))
				.execute();
	}

	public void setUserRecoveryKey(Long userId, String recoveryKey) {

		create
				.update(EKI_USER)
				.set(EKI_USER.RECOVERY_KEY, recoveryKey)
				.where(EKI_USER.ID.eq(userId)).execute();
	}

	public void setUserPassword(String email, String encodedPassword) {

		EkiUserRecord ekiUser = create.selectFrom(EKI_USER).where(EKI_USER.EMAIL.eq(email)).fetchOne();
		ekiUser.setRecoveryKey(null);
		ekiUser.setPassword(encodedPassword);
		ekiUser.store();
	}

	public EkiUser activateUser(String activationKey) {
		Optional<EkiUserRecord> ekiUser = create.selectFrom(EKI_USER).where(EKI_USER.ACTIVATION_KEY.eq(activationKey)).fetchOptional();
		if (ekiUser.isPresent()) {
			ekiUser.get().setActivationKey(null);
			ekiUser.get().store();
			return ekiUser.get().into(EkiUser.class);
		}
		return null;
	}

	public void enableUser(Long userId, boolean enable) {
		create.update(EKI_USER).set(EKI_USER.IS_ENABLED, enable).where(EKI_USER.ID.eq(userId)).execute();
	}

	public void setApiCrud(Long userId, boolean isApiCrud) {
		create.update(EKI_USER).set(EKI_USER.IS_API_CRUD, isApiCrud).where(EKI_USER.ID.eq(userId)).execute();
	}

	public void setAdmin(Long userId, boolean isAdmin) {
		create.update(EKI_USER).set(EKI_USER.IS_ADMIN, isAdmin).where(EKI_USER.ID.eq(userId)).execute();
	}

	public void setMaster(Long userId, boolean isMaster) {
		create.update(EKI_USER).set(EKI_USER.IS_MASTER, isMaster).where(EKI_USER.ID.eq(userId)).execute();
	}

	public void setReviewed(Long userId, boolean isReviewed) {
		create.update(EKI_USER).set(EKI_USER.IS_REVIEWED, isReviewed).where(EKI_USER.ID.eq(userId)).execute();
	}

	public void updateReviewComment(Long userId, String reviewComment) {
		create.update(EKI_USER).set(EKI_USER.REVIEW_COMMENT, reviewComment).where(EKI_USER.ID.eq(userId)).execute();
	}

	public List<String> getAdminEmails() {

		return create
				.select(EKI_USER.EMAIL)
				.from(EKI_USER)
				.where(EKI_USER.IS_ADMIN.isTrue())
				.fetchInto(String.class);
	}

	public void createUserApplication(Long userId, String[] datasets, String comment) {

		create
				.insertInto(EKI_USER_APPLICATION, EKI_USER_APPLICATION.USER_ID, EKI_USER_APPLICATION.DATASETS, EKI_USER_APPLICATION.COMMENT)
				.values(userId, datasets, comment)
				.execute();
	}

	public List<EkiUserApplication> getUserApplications(Long userId) {

		Field<Boolean> basicApplicationField = DSL.field(
				EKI_USER_APPLICATION.DATASETS.isNull()
						.and(DSL.or(EKI_USER_APPLICATION.COMMENT.isNull(), EKI_USER_APPLICATION.COMMENT.eq(""))));

		return create
				.select(
						EKI_USER_APPLICATION.USER_ID,
						EKI_USER_APPLICATION.DATASETS.as("dataset_codes"),
						EKI_USER_APPLICATION.COMMENT,
						EKI_USER_APPLICATION.CREATED,
						basicApplicationField.as("basic_application"))
				.from(EKI_USER_APPLICATION)
				.where(EKI_USER_APPLICATION.USER_ID.eq(userId))
				.fetchInto(EkiUserApplication.class);
	}

	public void updateApiKey(Long userId, String apiKey, boolean isApiCrud) {

		create.update(EKI_USER).set(EKI_USER.API_KEY, apiKey).set(EKI_USER.IS_API_CRUD, isApiCrud).where(EKI_USER.ID.eq(userId)).execute();
	}
}
