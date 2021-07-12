package com.fanshawe.nfttracker.helper;

public class ApplicationConstants {

	public static final String API_ERROR = "Some error occured, try again!";

	public static final String LETTER_OF_INTENT_PREFIX = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n"
			+ "<title>Faculty Letter of Intent</title>\r\n" + "<style>\r\n"
			+ "body{font-family:arial; line-height:18px; font-size:14px;}\r\n" + "ul li{margin-bottom:5px;}\r\n"
			+ "ul{margin:0px; padding:0px 20px;}\r\n" + "</style>\r\n" + "\r\n" + "</head>\r\n" + "<body>\r\n"
			+ "<div style=\"width:900px; padding:10px; border:1px solid #000; margin:0 auto;\">";
	public static final String LETTER_OF_INTENT_POSTFIX = "</div>\r\n" + "</body>\r\n" + "</html>";

	public static final String TEACHING_NOTIFICATION_PREFIX = "<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n"
			+ "<title>London South Campus teaching invitation</title>\r\n" + "<style>\r\n"
			+ "body{font-family:arial; line-height:18px; font-size:14px;}\r\n" + "ul li{margin-bottom:5px;}\r\n"
			+ "ul{margin:0px; padding:0px 20px;}\r\n"
			+ "td{padding:10px; border-left:1px solid #000; vertical-align:top; }\r\n"
			+ "td:first-child{border-left:0px;}\r\n" + "</style>\r\n" + "\r\n" + "</head>\r\n" + "<body>\r\n"
			+ "<div style=\"width:900px; padding:10px; border:1px solid #000; margin:0 auto;\">";
	public static final String TEACHING_NOTIFICATION_POSTFIX = "</div>\r\n" + "</body>\r\n" + "</html>";

	public static final String PART_TIME_PARTIAL_LOAD_CONTRACT_PREFIX = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n"
			+ "<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + "<head>\r\n"
			+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n"
			+ "<title>Part-Time - Post-Secondary Credit</title>\r\n" + "<style>\r\n"
			+ "body{font-family:Arial, Helvetica, sans-serif; font-size:14px; line-height:20px;}\r\n"
			+ "h2{font-family:Arial, Helvetica, sans-serif; font-size:20px; font-weight:normal;}\r\n"
			+ "input[type=\"text\"]{border-bottom:1px solid #000 !important; border:0;}\r\n"
			+ "p{margin:0 0px 10px;}\r\n" + "label{    display: inline-block;\r\n" + "    width: 82%;\r\n"
			+ "    white-space: nowrap;}\r\n" + "</style>\r\n" + "</head>\r\n" + "\r\n" + "<body>\r\n"
			+ "<div style=\"width:815px; border:1px solid #000; padding:15px; margin:0 auto\">";
	public static final String PART_TIME_PARTIAL_LOAD_CONTRACT_POSTFIX = "</div>\r\n" + "</body>\r\n" + "</html>";

	public static final String FROM_EMAIL = "fromEmailUsername";
	public static final String FROM_EMAIL_PASSWORD = "fromEmailPassword";

	public static final String SMTP_HOST = "mail.smtp.host";
	public static final String SMTP_PORT = "mail.smtp.port";
	public static final String PART_TIME_HOURS = "partTimeHours";
	public static final String PARTIAL_LOAD_HOURS = "partialLoadHours";

	public static final String APPLICATON_SETTINGS_FILE_PATH = "lscplanning.properties";

}
