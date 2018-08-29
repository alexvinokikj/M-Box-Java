package com.app.MBox.services;

import com.app.MBox.core.model.users;
import com.app.MBox.core.model.verificationToken;

public interface verificationTokenService {

    public verificationToken findByUserId(int id);

    public users findByToken(String token);

    public void saveVerificationToken(verificationToken verificationToken);

}
