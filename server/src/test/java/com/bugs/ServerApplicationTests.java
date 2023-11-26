package com.bugs;

import com.bugs.dao.UserDao;
import com.bugs.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServerApplicationTests {
	@Autowired
	UserDao userDao;
	@Test
	void contextLoads() {
		System.out.println("测试");
	}

	@Test
	void userTest() {
		User test01 = userDao.getUserByUserid("test01");
		System.out.println(test01.toString());
	}

}
