package com.fanshawe.nfttracker.api.repositories;

import com.fanshawe.nfttracker.api.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ManageUserRepository extends CrudRepository<User, Long> {
    public Optional<User> findUserByUsername(String username);


}
