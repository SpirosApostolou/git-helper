package com.intrasoft.git.helper.constants;

public enum EmailConstants {

	OUTLOOK_SERVER_HOST_NAME("smtp.office365.com"),
	OUTLOOK_SERVER_PORT_NO("587"),
	OUTLOOK_USER_MAIL("sapostolou@intrasoft-intl.com"),
	OUTLOOK_USER_PASSWORD("@WSX3edc$RFV"),
	MESSAGE_SUBJECT("SDK Automation tests failures"),
	ADMIN_MAIL("spyridon.APOSTOLOU@intrasoft-intl.com")
	;

	private String value;

	EmailConstants(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
