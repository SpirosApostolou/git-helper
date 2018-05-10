package com.intrasoft.git.helper;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.intrasoft.git.helper.constants.EmailConstants;
import com.intrasoft.git.helper.entities.EmailInfo;

public class EmailSender {
	
	private EmailInfo emailInfo;
	
	public EmailSender() {
		super();
	}

	public EmailSender(EmailInfo emailInfo) {
		super();
		this.emailInfo = emailInfo;
	}
	
	public void sendEmail() {
        Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailConstants.OUTLOOK_USER_MAIL.value(), EmailConstants.OUTLOOK_USER_PASSWORD.value());
            }

        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailInfo.getMailFrom()));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailInfo.getMailTo()));
            message.setRecipient(Message.RecipientType.CC, new InternetAddress(emailInfo.getMailcc()));
            message.setSubject(emailInfo.getSubject());
            message.setText(emailInfo.getMessageContent());
            message.setSentDate(new Date());
            Transport.send(message);
            System.out.println("email send to: " + emailInfo.getMailTo());
        } catch (MessagingException ex) {
        	ex.printStackTrace();
        }
    }

    public Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.host", EmailConstants.OUTLOOK_SERVER_HOST_NAME.value());
        config.put("mail.smtp.port", EmailConstants.OUTLOOK_SERVER_PORT_NO.value());
        return config;
    }

	public EmailInfo getEmailInfo() {
		return emailInfo;
	}

	public void setEmailInfo(EmailInfo emailInfo) {
		this.emailInfo = emailInfo;
	}

}
