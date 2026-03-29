package ru.vladislav.javanaumen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.vladislav.javanaumen.entity.User;
import ru.vladislav.javanaumen.service.UserService;

@SpringBootTest
class JavaNaumenApplicationTests {
	private final UserService userService;

	@Autowired
	JavaNaumenApplicationTests(UserService userService) {
		this.userService = userService;
	}

	@Test
	void contextLoads() {
	}

	@Test
	void userServiceTest() {
		var user = new User();
		user.setUsername("boris");
		user.setPassword("abab123");
		userService.addUser(user);

		var foundUser = userService.getUser("boris");

		Assertions.assertNotNull(foundUser);
		Assertions.assertEquals(user.getId(), foundUser.getId());
		Assertions.assertEquals(user.getUsername(), foundUser.getUsername());
	}
}
