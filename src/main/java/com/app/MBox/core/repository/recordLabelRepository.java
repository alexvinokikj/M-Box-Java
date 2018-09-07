package com.app.MBox.core.repository;

import com.app.MBox.core.model.recordLabel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface recordLabelRepository extends CrudRepository<recordLabel,Integer> {

    recordLabel findByUserId(int userId);

}
