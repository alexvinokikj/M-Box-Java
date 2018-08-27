package com.app.MBox.services;

import com.app.MBox.core.model.emailTemplate;
import com.app.MBox.core.repository.emailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("emailTemplateService")
public class emailTemplateService {
    @Autowired
    private emailTemplateRepository emailTemplateRepository ;


    //public emailTemplateService (emailTemplateRepository emailTemplateRepository) {
        //this.emailTemplateRepository=emailTemplateRepository;
   // }

    public emailTemplate findByName(String name) {

        return emailTemplateRepository.findByName(name);
    }


    public void saveEmailTemplate(emailTemplate emailTemplate) {

        emailTemplateRepository.save(emailTemplate);
    }

}
