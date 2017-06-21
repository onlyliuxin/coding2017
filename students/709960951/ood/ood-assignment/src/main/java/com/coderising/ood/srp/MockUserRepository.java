package com.coderising.ood.srp;

import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp.domainlogic.User;
import com.coderising.ood.srp.domainlogic.UserRepository;

public class MockUserRepository extends UserRepository {

	@Override
	public List<User> getPromotionUsers() {
		List<User> userList = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			User user = new User();
			user.setName("User" + i);
			user.setEmail("aa@bb.com");
			userList.add(user);
		}

		return userList;
	}

}
