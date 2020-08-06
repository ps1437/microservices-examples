package com.syscho.user.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.syscho.user.entity.UserDO;

@Repository
public interface UserRepository extends CrudRepository<UserDO, String> {

	List<UserDO> findAll();

	Optional<UserDO> findByUserName(String userName);

}
