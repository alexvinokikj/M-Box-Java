package com.app.MBox.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
@Getter
@Setter
@NoArgsConstructor
public class emailService {

    @Autowired
    private static JavaMailSender mailSender;

    @Async
    public static void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }                                    //I put them static maybe there will be problems ?
}
