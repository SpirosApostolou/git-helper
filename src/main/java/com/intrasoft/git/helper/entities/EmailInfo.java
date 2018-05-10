package com.intrasoft.git.helper.entities;

public class EmailInfo {

	private String mailTo;
	private String mailFrom;
	private String subject;
	private String messageContent;
	private String mailcc;
	
	public EmailInfo() {
		super();
	}

	public EmailInfo(String mailTo, String mailFrom, String subject,
			String messageContent, String mailcc) {
		super();
		this.mailTo = mailTo;
		this.mailFrom = mailFrom;
		this.subject = subject;
		this.messageContent = messageContent;
		this.mailcc = mailcc;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMailcc() {
		return mailcc;
	}

	public void setMailcc(String mailcc) {
		this.mailcc = mailcc;
	}

	@Override
	public String toString() {
		return "EmailInfo [mailTo=" + mailTo + ", mailFrom=" + mailFrom
				+ ", subject=" + subject + ", messageContent=" + messageContent
				+ ", mailcc=" + mailcc + "]";
	}

}
