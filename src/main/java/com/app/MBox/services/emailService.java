package com.app.MBox.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service("emailService")
@Getter
@Setter
@NoArgsConstructor
public class emailService {

    void sendMail(String smtpServerHost, String smtpServerPort,  String smtpUserName, String smtpUserPassword, String fromUserEmail, String fromUserFullName, String toEmail, String subject, String body) {
        try {
            Properties props = System.getProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.port", smtpServerPort);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromUserEmail, fromUserFullName));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);
            msg.setContent(body, "text/html");

            Transport transport = session.getTransport();
            transport.connect(smtpServerHost, smtpUserName, smtpUserPassword);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (Exception ex) {
            System.out.println("There was a problem sending the mail");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);

        }
    }
}
