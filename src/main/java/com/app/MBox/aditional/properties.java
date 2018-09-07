package com.app.MBox.aditional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
@ConfigurationProperties("app")
public class properties {

    private  String example;
    private  String passwordMessage;
    private  String smtpServerHost;
    private  String smtpServerPort;
    private  String smtpUserName;
    private  String smtpUserPassword;
    private  String fromUserEmail;
    private  String APPURL;
    private  String NAME;
    private  String EMAILADRESS;
    private  String PORT;
    private  String toEmailAdress;
}
