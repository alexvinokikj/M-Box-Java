package com.app.MBox.core.repository;

import com.app.MBox.core.model.emailTemplate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface emailTemplateRepository extends CrudRepository <emailTemplate,Integer> {

    emailTemplate findByName (String name);

}
