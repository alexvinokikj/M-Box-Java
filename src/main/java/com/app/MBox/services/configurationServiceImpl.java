package com.app.MBox.services;

import com.app.MBox.core.model.configuration;
import com.app.MBox.core.repository.configurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("configurationServiceImpl")
public class configurationServiceImpl implements configurationService {

    @Autowired
    configurationRepository configurationRepository;


    @Override
    public configuration findByKey(String key) {
        return configurationRepository.findByKey(key);
    }
}
