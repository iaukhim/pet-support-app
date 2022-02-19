package com.unknown.server.mail;


import com.sun.mail.smtp.SMTPSendFailedException;
import org.apache.commons.lang3.RandomStringUtils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MailService {

    private static final String MAIL_SERVICE_PROPERTIES_FILE = "./support-app/server/mail-service-settings.properties";

    private static final MailService mailService = new MailService();

    private Properties properties;

    public static MailService getMailService(){
        return mailService;
    }

    private MailService() {
        properties = new Properties();
        try {
            properties.load(new FileReader(MAIL_SERVICE_PROPERTIES_FILE));
        } catch (IOException e) {
            throw new RuntimeException("Error occurred, trying to load mail service properties", e);
        }

    }

    public String sendConfirmationEmail(String receiverEmail) {
        String randomedCode = null;
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(properties.getProperty("username"), properties.getProperty("password"));
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(properties.getProperty("from")));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiverEmail));

            message.setSubject("Confirmation Email");
            randomedCode = RandomStringUtils.randomAlphanumeric(6);
            message.setText(randomedCode);
            Transport.send(message);
        } catch (SMTPSendFailedException e) {
            System.out.println("fail in mail");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return randomedCode;
    }
}

