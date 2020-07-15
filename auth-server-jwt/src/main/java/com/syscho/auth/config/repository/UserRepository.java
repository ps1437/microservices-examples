package com.syscho.auth.config.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syscho.auth.config.entity.UserDO;

@Repository
public interface UserRepository extends CrudRepository<UserDO, Integer> {

	Optional<UserDO> findByUsername(String username);

}
