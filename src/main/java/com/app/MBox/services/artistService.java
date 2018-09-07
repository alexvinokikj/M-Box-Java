package com.app.MBox.services;

import com.app.MBox.core.model.artist;
import com.app.MBox.core.repository.artistRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface artistService {

    List<artist> findAllArtists(int recordLabelId);

}
