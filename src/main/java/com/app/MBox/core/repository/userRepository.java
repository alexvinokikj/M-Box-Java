package com.app.MBox.core.repository;

import com.app.MBox.core.model.users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface userRepository extends CrudRepository<users,Integer> {

    users findByEmail(String email);
}
