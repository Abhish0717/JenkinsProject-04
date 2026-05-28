package in.co.rays.proj4.controller;

/**
 * ORSView defines application-wide constants for JSP view paths and their
 * corresponding controller URL mappings. These constants are used throughout
 * the project to avoid hard-coded strings in controllers and JSPs.
 *
 * <p>
 * - {@code APP_CONTEXT} is the application context root. <br>
 * - {@code PAGE_FOLDER} is the base folder where JSP pages are located. <br>
 * - Other constants map logical view names (JSP paths) and controller URL
 * patterns used by servlets.
 * </p>
 *
 * @author Abhishish Bhawsar
 * 
 * @version 1.0
 *
 */
public interface ORSView {

	/** Application context root. */
	public String APP_CONTEXT = "/ORSProject-04";

	/** Base JSP folder. */
	public String PAGE_FOLDER = "/jsp";

	public String MODULE_VIEW = PAGE_FOLDER + "/ModuleView.jsp";
	public String MODULE_CTL = APP_CONTEXT + "/ctl/ModuleCtl";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String WELCOME_VIEW = PAGE_FOLDER + "/WelcomeView.jsp";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";

	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";

	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";

	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";

	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";

	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";

	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";

	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";

	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";

	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";

	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";

	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";

	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";

	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";

	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";

	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";

	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";
	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";

	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";

	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";
	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";

	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";
	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";

	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";
	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";

	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";

	/** Timetable View JSP */
	public String TIMETABLE_VIEW = PAGE_FOLDER + "/TimeTableView.jsp";
	/** Timetable Controller URL */
	public String TIMETABLE_CTL = APP_CONTEXT + "/ctl/TimeTableCtl";

	/** TimeTable List JSP */
	public String TIMETABLE_LIST_VIEW = PAGE_FOLDER + "/TimeTableListView.jsp";
	/** TimeTable List Controller URL */
	public String TIMETABLE_LIST_CTL = APP_CONTEXT + "/ctl/TimeTableListCtl";

	/** Faculty View JSP */
	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";
	/** Faculty Controller URL */
	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";

	/** Faculty List JSP */
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";
	/** Faculty List Controller URL */
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";

	/** ================= Error Page ================= */

	/** Error JSP */
	public String ERROR_VIEW = PAGE_FOLDER + "/ErrorView.jsp";

	/** Error Controller URL */
	public String ERROR_CTL = APP_CONTEXT + "/ErrorCtl";

	/** ================= Daily Models ================= */

	/** Health View JSP */
	public String HEALTH_VIEW = PAGE_FOLDER + "/HealthView.jsp";
	/** Health Controller URL */
	public String HEALTH_CTL = APP_CONTEXT + "/ctl/HealthCtl";

	/** Health List JSP */
	public String HEALTH_LIST_VIEW = PAGE_FOLDER + "/HealthListView.jsp";
	/** Health List Controller URL */
	public String HEALTH_LIST_CTL = APP_CONTEXT + "/ctl/HealthListCtl";

	/** Purge View JSP */
	public String PURGE_VIEW = PAGE_FOLDER + "/PurgeView.jsp";
	/** Purge Controller URL */
	public String PURGE_CTL = APP_CONTEXT + "/ctl/PurgeCtl";

	/** Purge List JSP */
	public String PURGE_LIST_VIEW = PAGE_FOLDER + "/PurgeListView.jsp";
	/** Purge List Controller URL */
	public String PURGE_LIST_CTL = APP_CONTEXT + "/ctl/PurgeListCtl";

	/** System View JSP */
	public String SYSTEM_VIEW = PAGE_FOLDER + "/SystemView.jsp";
	/** System Controller URL */
	public String SYSTEM_CTL = APP_CONTEXT + "/ctl/SystemCtl";

	/** System List JSP */
	public String SYSTEM_LIST_VIEW = PAGE_FOLDER + "/SystemListView.jsp";
	/** System List Controller URL */
	public String SYSTEM_LIST_CTL = APP_CONTEXT + "/ctl/SystemListCtl";

	/** Subscription View JSP */
	public String SUBSCRIPTION_VIEW = PAGE_FOLDER + "/SubscriptionView.jsp";
	/** Subscription Controller URL */
	public String SUBSCRIPTION_CTL = APP_CONTEXT + "/ctl/SubscriptionCtl";

	/** Subscription List JSP */
	public String SUBSCRIPTION_LIST_VIEW = PAGE_FOLDER + "/SubscriptionListView.jsp";
	/** Subscription List Controller URL */
	public String SUBSCRIPTION_LIST_CTL = APP_CONTEXT + "/ctl/SubscriptionListCtl";

	/** Feature View JSP */
	public String FEATURE_VIEW = PAGE_FOLDER + "/FeatureView.jsp";
	/** Feature Controller URL */
	public String FEATURE_CTL = APP_CONTEXT + "/ctl/FeatureCtl";

	/** Feature List JSP */
	public String FEATURE_LIST_VIEW = PAGE_FOLDER + "/FeatureListView.jsp";
	/** Feature List Controller URL */
	public String FEATURE_LIST_CTL = APP_CONTEXT + "/ctl/FeatureListCtl";

	/** Audit View JSP */
	public String AUDIT_VIEW = PAGE_FOLDER + "/AuditView.jsp";
	/** Audit Controller URL */
	public String AUDIT_CTL = APP_CONTEXT + "/ctl/AuditCtl";

	/** Audit List JSP */
	public String AUDIT_LIST_VIEW = PAGE_FOLDER + "/AuditListView.jsp";
	/** Audit List Controller URL */
	public String AUDIT_LIST_CTL = APP_CONTEXT + "/ctl/AuditListCtl";

	/** Block View JSP */
	public String BLOCK_VIEW = PAGE_FOLDER + "/BlockView.jsp";
	/** Block Controller URL */
	public String BLOCK_CTL = APP_CONTEXT + "/ctl/BlockCtl";

	/** Block List JSP */
	public String BLOCK_LIST_VIEW = PAGE_FOLDER + "/BlockListView.jsp";
	/** Block List Controller URL */
	public String BLOCK_LIST_CTL = APP_CONTEXT + "/ctl/BlockListCtl";

	/** Rule View JSP */
	public String RULE_VIEW = PAGE_FOLDER + "/RuleView.jsp";
	/** Rule Controller URL */
	public String RULE_CTL = APP_CONTEXT + "/ctl/RuleCtl";

	/** Rule List JSP */
	public String RULE_LIST_VIEW = PAGE_FOLDER + "/RuleListView.jsp";
	/** Rule List Controller URL */
	public String RULE_LIST_CTL = APP_CONTEXT + "/ctl/RuleListCtl";

	/** Access View JSP */
	public String ACCESS_VIEW = PAGE_FOLDER + "/AccessView.jsp";
	/** Access Controller URL */
	public String ACCESS_CTL = APP_CONTEXT + "/ctl/AccessCtl";

	/** Access List JSP */
	public String ACCESS_LIST_VIEW = PAGE_FOLDER + "/AccessListView.jsp";
	/** Access List Controller URL */
	public String ACCESS_LIST_CTL = APP_CONTEXT + "/ctl/AccessListCtl";

	/** Transformation View JSP */
	public String TRANSFORMATION_VIEW = PAGE_FOLDER + "/TransformationView.jsp";
	/** Transformation Controller URL */
	public String TRANSFORMATION_CTL = APP_CONTEXT + "/ctl/TransformationCtl";

	/** Transformation List JSP */
	public String TRANSFORMATION_LIST_VIEW = PAGE_FOLDER + "/TransformationListView.jsp";
	/** Transformation List Controller URL */
	public String TRANSFORMATION_LIST_CTL = APP_CONTEXT + "/ctl/TransformationListCtl";

	/** Event View JSP */
	public String EVENT_VIEW = PAGE_FOLDER + "/EventView.jsp";
	/** Event Controller URL */
	public String EVENT_CTL = APP_CONTEXT + "/ctl/EventCtl";

	/** Event List JSP */
	public String EVENT_LIST_VIEW = PAGE_FOLDER + "/EventListView.jsp";
	/** Event List Controller URL */
	public String EVENT_LIST_CTL = APP_CONTEXT + "/ctl/EventListCtl";

	/** Password View JSP */
	public String PASSWORD_VIEW = PAGE_FOLDER + "/PasswordView.jsp";
	/** Password Controller URL */
	public String PASSWORD_CTL = APP_CONTEXT + "/ctl/PasswordCtl";

	/** Password List JSP */
	public String PASSWORD_LIST_VIEW = PAGE_FOLDER + "/PasswordListView.jsp";
	/** Password List Controller URL */
	public String PASSWORD_LIST_CTL = APP_CONTEXT + "/ctl/PasswordListCtl";

	/** Queue View JSP */
	public String QUEUE_VIEW = PAGE_FOLDER + "/QueueView.jsp";
	/** Queue Controller URL */
	public String QUEUE_CTL = APP_CONTEXT + "/ctl/QueueCtl";

	/** Queue List JSP */
	public String QUEUE_LIST_VIEW = PAGE_FOLDER + "/QueueListView.jsp";
	/** Queue List Controller URL */
	public String QUEUE_LIST_CTL = APP_CONTEXT + "/ctl/QueueListCtl";

	/** Allow View JSP */
	public String ALLOW_VIEW = PAGE_FOLDER + "/AllowView.jsp";
	/** Allow Controller URL */
	public String ALLOW_CTL = APP_CONTEXT + "/ctl/AllowCtl";

	/** Allow List JSP */
	public String ALLOW_LIST_VIEW = PAGE_FOLDER + "/AllowListView.jsp";
	/** Allow List Controller URL */
	public String ALLOW_LIST_CTL = APP_CONTEXT + "/ctl/AllowListCtl";

	/** Face View JSP */
	public String FACE_VIEW = PAGE_FOLDER + "/FaceView.jsp";
	/** Face Controller URL */
	public String FACE_CTL = APP_CONTEXT + "/ctl/FaceCtl";

	/** Face List JSP */
	public String FACE_LIST_VIEW = PAGE_FOLDER + "/FaceListView.jsp";
	/** Face List Controller URL */
	public String FACE_LIST_CTL = APP_CONTEXT + "/ctl/FaceListCtl";

	/** Claim View JSP */
	public String CLAIM_VIEW = PAGE_FOLDER + "/ClaimView.jsp";
	/** Claim Controller URL */
	public String CLAIM_CTL = APP_CONTEXT + "/ctl/ClaimCtl";

	/** Claim List JSP */
	public String CLAIM_LIST_VIEW = PAGE_FOLDER + "/ClaimListView.jsp";
	/** Claim List Controller URL */
	public String CLAIM_LIST_CTL = APP_CONTEXT + "/ctl/ClaimListCtl";

	/** Account View JSP */
	public String ACCOUNT_VIEW = PAGE_FOLDER + "/AccountView.jsp";
	/** Account Controller URL */
	public String ACCOUNT_CTL = APP_CONTEXT + "/ctl/AccountCtl";

	/** Account List JSP */
	public String ACCOUNT_LIST_VIEW = PAGE_FOLDER + "/AccountListView.jsp";
	/** Account List Controller URL */
	public String ACCOUNT_LIST_CTL = APP_CONTEXT + "/ctl/AccountListCtl";

	/** Registration View JSP */
	public String REGISTRATION_VIEW = PAGE_FOLDER + "/RegistrationView.jsp";
	/** Registration Controller URL */
	public String REGISTRATION_CTL = APP_CONTEXT + "/ctl/RegistrationCtl";

	/** Registration List JSP */
	public String REGISTRATION_LIST_VIEW = PAGE_FOLDER + "/RegistrationListView.jsp";
	/** Registration List Controller URL */
	public String REGISTRATION_LIST_CTL = APP_CONTEXT + "/ctl/RegistrationListCtl";

	/** Voice View JSP */
	public String VOICE_VIEW = PAGE_FOLDER + "/VoiceView.jsp";
	/** Voice Controller URL */
	public String VOICE_CTL = APP_CONTEXT + "/ctl/VoiceCtl";

	/** Voice List JSP */
	public String VOICE_LIST_VIEW = PAGE_FOLDER + "/VoiceListView.jsp";
	/** Voice List Controller URL */
	public String VOICE_LIST_CTL = APP_CONTEXT + "/ctl/VoiceListCtl";

	/** Chat View JSP */
	public String CHAT_VIEW = PAGE_FOLDER + "/ChatView.jsp";
	/** Chat Controller URL */
	public String CHAT_CTL = APP_CONTEXT + "/ctl/ChatCtl";

	/** Chat List JSP */
	public String CHAT_LIST_VIEW = PAGE_FOLDER + "/ChatListView.jsp";
	/** Chat List Controller URL */
	public String CHAT_LIST_CTL = APP_CONTEXT + "/ctl/ChatListCtl";

	/** Story View JSP */
	public String STORY_VIEW = PAGE_FOLDER + "/StoryView.jsp";
	/** Story Controller URL */
	public String STORY_CTL = APP_CONTEXT + "/ctl/StoryCtl";

	/** Story List JSP */
	public String STORY_LIST_VIEW = PAGE_FOLDER + "/StoryListView.jsp";
	/** Story List Controller URL */
	public String STORY_LIST_CTL = APP_CONTEXT + "/ctl/StoryListCtl";

	/** Login History View JSP */
	public String LOGIN_HISTORY_VIEW = PAGE_FOLDER + "/LoginHistoryView.jsp";
	/** Login History Controller URL */
	public String LOGIN_HISTORY_CTL = APP_CONTEXT + "/ctl/LoginHistoryCtl";

	/** Login History List JSP */
	public String LOGIN_HISTORY_LIST_VIEW = PAGE_FOLDER + "/LoginHistoryListView.jsp";
	/** Login History List Controller URL */
	public String LOGIN_HISTORY_LIST_CTL = APP_CONTEXT + "/ctl/LoginHistoryListCtl";

	/** Cricket View JSP */
	public String CRICKET_VIEW = PAGE_FOLDER + "/CricketView.jsp";
	/** Cricket Controller URL */
	public String CRICKET_CTL = APP_CONTEXT + "/ctl/CricketCtl";

	/** Cricket List JSP */
	public String CRICKET_LIST_VIEW = PAGE_FOLDER + "/CricketListView.jsp";
	/** Cricket List Controller URL */
	public String CRICKET_LIST_CTL = APP_CONTEXT + "/ctl/CricketListCtl";

	/** Achievement View JSP */
	public String ACHIEVEMENT_VIEW = PAGE_FOLDER + "/AchievementView.jsp";
	/** Achievement Controller URL */
	public String ACHIEVEMENT_CTL = APP_CONTEXT + "/ctl/AchievementCtl";

	/** Achievement List JSP */
	public String ACHIEVEMENT_LIST_VIEW = PAGE_FOLDER + "/AchievementListView.jsp";
	/** Achievement List Controller URL */
	public String ACHIEVEMENT_LIST_CTL = APP_CONTEXT + "/ctl/AchievementListCtl";

	/** Assistant View JSP */
	public String ASSISTANT_VIEW = PAGE_FOLDER + "/AssistantView.jsp";
	/** Assistant Controller URL */
	public String ASSISTANT_CTL = APP_CONTEXT + "/ctl/AssistantCtl";

	/** Assistant List JSP */
	public String ASSISTANT_LIST_VIEW = PAGE_FOLDER + "/AssistantListView.jsp";
	/** Assistant List Controller URL */
	public String ASSISTANT_LIST_CTL = APP_CONTEXT + "/ctl/AssistantListCtl";

	/** Meeting View JSP */
	public String MEETING_VIEW = PAGE_FOLDER + "/MeetingView.jsp";
	/** Meeting Controller URL */
	public String MEETING_CTL = APP_CONTEXT + "/ctl/MeetingCtl";

	/** Meeting List JSP */
	public String MEETING_LIST_VIEW = PAGE_FOLDER + "/MeetingListView.jsp";
	/** Meeting List Controller URL */
	public String MEETING_LIST_CTL = APP_CONTEXT + "/ctl/MeetingListCtl";

	/** Library View JSP */
	public String LIBRARY_VIEW = PAGE_FOLDER + "/LibraryView.jsp";
	/** Library Controller URL */
	public String LIBRARY_CTL = APP_CONTEXT + "/ctl/LibraryCtl";

	/** Library List JSP */
	public String LIBRARY_LIST_VIEW = PAGE_FOLDER + "/LibraryListView.jsp";
	/** Library List Controller URL */
	public String LIBRARY_LIST_CTL = APP_CONTEXT + "/ctl/LibraryListCtl";

	/** Parking View JSP */
	public String PARKING_VIEW = PAGE_FOLDER + "/ParkingView.jsp";
	/** Parking Controller URL */
	public String PARKING_CTL = APP_CONTEXT + "/ctl/ParkingCtl";
	
	/** Parking List JSP */
	public String PARKING_LIST_VIEW = PAGE_FOLDER + "/ParkingListView.jsp";
	/** Parking List Controller URL */
	public String PARKING_LIST_CTL = APP_CONTEXT + "/ctl/ParkingListCtl";

	/** Employee View JSP */
	public String EMPLOYEE_VIEW = PAGE_FOLDER + "/EmployeeView.jsp";
	/** Employee Controller URL */
	public String EMPLOYEE_CTL = APP_CONTEXT + "/ctl/EmployeeCtl";
	
	/** Employee List JSP */
	public String EMPLOYEE_LIST_VIEW = PAGE_FOLDER + "/EmployeeListView.jsp";
	/** Employee List Controller URL */
	public String EMPLOYEE_LIST_CTL = APP_CONTEXT + "/ctl/EmployeeListCtl";
}