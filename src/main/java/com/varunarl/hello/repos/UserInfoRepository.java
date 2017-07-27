package com.varunarl.hello.repos;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.varunarl.hello.User;

@EnableScan
public interface UserInfoRepository extends CrudRepository<User, Integer> {

	List<User> findById(Integer id);
}