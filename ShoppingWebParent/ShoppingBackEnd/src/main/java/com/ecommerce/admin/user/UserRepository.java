package com.ecommerce.admin.user;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	

}
