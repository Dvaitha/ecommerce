package com.ecommerce.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

	@Autowired
	private RoleRepository repository;

	@Test
	public void testCreateFirstRole() {
		Role roleAdmin = new Role("Admin", "manage Everything");

		Role roleSalesperson = new Role("Salesperson",
				"manage product price, customers, shipping, orders and sales report");

		Role roleEditor = new Role("Editor", "manage categories, brands, products, articles and menus");

		Role roleShipper = new Role("Shipper", "View products, view orders and update order status");

		Role roleAssistant = new Role("Assistant", "manage questions and reviews");

		repository.saveAll(List.of(roleAdmin, roleSalesperson, roleEditor, roleShipper, roleAssistant));

	}

	@Test
	public void testCreateRestRoles() {

	}
}
