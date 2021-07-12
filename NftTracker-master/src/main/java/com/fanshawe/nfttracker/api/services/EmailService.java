package com.fanshawe.nfttracker.api.services;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.util.StringUtils;

import com.fanshawe.nfttracker.helper.ApplicationConstants;
import com.fanshawe.nfttracker.helper.ReadApplicationSettings;

public class EmailService {

	@SuppressWarnings("deprecation")
	public boolean sendEmail(String to, String body, String subject, String cc, String bcc, String filename) {
		try {
			String from = ReadApplicationSettings.getApplicationProperty(ApplicationConstants.FROM_EMAIL);
			final String username = ReadApplicationSettings.getApplicationProperty(ApplicationConstants.FROM_EMAIL);
			final String password = ReadApplicationSettings
					.getApplicationProperty(ApplicationConstants.FROM_EMAIL_PASSWORD);

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", ReadApplicationSettings.getApplicationProperty(ApplicationConstants.SMTP_HOST));
			props.put("mail.smtp.port", ReadApplicationSettings.getApplicationProperty(ApplicationConstants.SMTP_PORT));

			// Get the Session object.
			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(body, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (!StringUtils.isEmpty(filename)) {
				MimeBodyPart attachmentPart = new MimeBodyPart();
				attachmentPart.attachFile(new File(filename));
				multipart.addBodyPart(attachmentPart);
			}

			/*
			 * MimeMultipart multipart = new MimeMultipart("related");
			 * 
			 * // first part (the html) BodyPart messageBodyPart = new MimeBodyPart();
			 * String htmlText = body; messageBodyPart.setContent(htmlText, "text/html"); //
			 * add it multipart.addBodyPart(messageBodyPart);
			 * 
			 * // second part (the image) messageBodyPart = new MimeBodyPart(); DataSource
			 * fds = new FileDataSource(
			 * "C:\\Raman\\Fanshawe\\Spring Workspace\\NftTracker\\src\\main\\resources\\static\\partTimeContractLogo.png"
			 * );
			 * 
			 * messageBodyPart.setDataHandler(new DataHandler(fds));
			 * messageBodyPart.setHeader("Content-ID", "<image>");
			 * 
			 * multipart.addBodyPart(messageBodyPart);
			 */

			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Send the actual HTML message, as big as you like
			message.setContent(multipart);
			// message.setContent(body, "text/html");

			// Send message
			Transport.send(message);

			return Boolean.TRUE;

		} catch (MessagingException | IOException e) {
			return Boolean.FALSE;
		}

	}
}
