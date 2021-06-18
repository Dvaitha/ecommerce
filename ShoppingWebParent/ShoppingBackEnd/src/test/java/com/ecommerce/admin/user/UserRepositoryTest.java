package com.ecommerce.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.common.entity.Role;
import com.ecommerce.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userDhaval = new User("vaithadhaval@gmail.com", "password", "Dhaval", "Vaitha");
		userDhaval.addRole(roleAdmin);
		User savedUser = repository.save(userDhaval);
		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userMeet = new User("meet@gmail.com", "password", "Meet", "Kumar");
		Role role1 = new Role(4);
		Role role2 = new Role(5);
		userMeet.addRole(role1);
		userMeet.addRole(role2);

		User savedUser = repository.save(userMeet);

		assertThat(savedUser.getId()).isGreaterThan(0);
	}

	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repository.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}

	@Test
	public void testGetUserById() {
		User user = repository.findById(1).get();
		System.out.println(user);
		assertThat(user).isNotNull();
	}

	@Test
	public void testUpdateUserUserDetails() {
		User user = repository.findById(1).get();
		user.setEnabled(true);
		user.setEmail("vaithadhaval@yahoo.com");
		
		repository.save(user);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User user = repository.findById(2).get();
		Role role1 = new Role(4);
		Role role2 = new Role(2);
		user.getRoles().remove(role1);
		user.addRole(role2);
		repository.save(user);
	}
	
	@Test
	public void testDeleteUser() {
		repository.deleteById(2);
	}
}
