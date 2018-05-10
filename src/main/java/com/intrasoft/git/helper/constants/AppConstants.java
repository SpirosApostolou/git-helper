package com.intrasoft.git.helper.constants;




public enum AppConstants {
 
	ICI_CM_PATH("C:\\gitHubRepoDk\\ici-cm\\.git"),
	ICI_PSRM_PATH("C:\\gitHubRepoDk\\ici-psrm\\.git"),
	INPUT_FROM_MAIL_FILE("inputFromMail"),
	TEST_FILES_PATH("Code/PSRM/EclipseWorkspaces/SKMICI/_integrationsTest/"),
	INTRASOFT_MAIL_PART("intrasoft-intl.com")
	;

	private String value;

	AppConstants(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

