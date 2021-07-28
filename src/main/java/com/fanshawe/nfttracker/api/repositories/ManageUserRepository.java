package com.fanshawe.nfttracker.api.repositories;

import com.fanshawe.nfttracker.api.entities.ApiUser;
import com.fanshawe.nfttracker.api.entities.Professor;
import com.fanshawe.nfttracker.api.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ManageUserRepository extends CrudRepository<User, Long> {
    List<User> findByUsername(String username);


}
