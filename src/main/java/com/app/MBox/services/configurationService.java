package com.app.MBox.services;

import com.app.MBox.core.model.configuration;

public interface configurationService {

    configuration findByKey(String key);
}
