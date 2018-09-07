package com.app.MBox.core.repository;

import org.springframework.data.repository.CrudRepository;
import com.app.MBox.core.model.configuration;
import org.springframework.stereotype.Repository;

@Repository
public interface configurationRepository extends CrudRepository<configuration,Integer> {

    configuration findByKey(String key);

}
