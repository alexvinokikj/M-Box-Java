package com.app.MBox.core.repository;

import com.app.MBox.core.model.role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roleRepository extends CrudRepository <role,Integer> {

    role findByName (String name);

    role findById (int id);
}
