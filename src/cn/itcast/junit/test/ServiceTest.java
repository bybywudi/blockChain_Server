package cn.itcast.junit.test;

import cn.itcast.domain.User;
import cn.itcast.exception.UserExistException;
import cn.itcast.service.impl.BusinessServiceImpl;
import org.junit.Test;

import java.util.Date;

public class ServiceTest {
	
	@Test
	public void testRegister() {
		User user = new User();
		user.setBirthday(new Date());
		user.setEmail("baibai@163.com");
		user.setId("233333");
		user.setNikename("大傻春");
		user.setPassword("123");
		user.setUsername("bbb");
		
		BusinessServiceImpl service = new BusinessServiceImpl();
		try {
			service.register(user);
			System.out.println("注册成功！！");
		} catch (UserExistException e) {
			System.out.println("用户已存在");
		}
		
	}
	
	@Test
	public void testLogin() {
		BusinessServiceImpl service = new BusinessServiceImpl();
		User user = service.login("aaaa", "123");
		System.out.println(user);
	}

	@Test
	public void testAddNewBolck() {
		BusinessServiceImpl service = new BusinessServiceImpl();

		System.out.println(service.addNewBolck(10,"1"));
	}
}
