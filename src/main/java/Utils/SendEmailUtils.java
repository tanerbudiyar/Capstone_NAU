package Utils;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

public class SendEmailUtils {

	static String filename = System.getProperty("user.dir") + File.separator + "target" + File.separator
			 + "bookSearchResult.xlsx";

	// send an email with attachment to a recipient
	public static void sendAttachmentInEmail(String recipient) {
		String from = "pricecheckertaner@gmail.com";
		final String username = "pricecheckertaner";
		final String password= "pricechecK";
		Properties props = new Properties();
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.poprt","587");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
			
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject("Amazon Book Search Results");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Please check the attached excel file for Amazon Book Search Results");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler (source));
			messageBodyPart.setFileName(filename);
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			//Send message
			Transport.send(message);
		} catch (MessagingException e)
		{
			throw new RuntimeException(e);
		}
		}

	
	public static void sendEmailToRecipients() {
//	SendEmailUtils.sendAttachmentInEmail("aoncu@na.edu");
	SendEmailUtils.sendAttachmentInEmail("tanerbudiyar@gmail.com");
	System.out.println("Email has been sent");
	}
	
}
