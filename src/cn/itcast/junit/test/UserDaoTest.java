package cn.itcast.junit.test;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;
import org.junit.Test;

import java.util.Date;

public class UserDaoTest {
	
	@Test
	public void tesAdd() {
		User user = new User();
		user.setBirthday(new Date());
		user.setEmail("baibai@163.com");
		user.setId("233333");
		user.setNikename("��ɵ��");
		user.setPassword("123");
		user.setUsername("aaa");
		
		UserDao dao = new UserDaoImpl();
		dao.add(user);
	}
	
	@Test
	public void testFind() {
		UserDao dao = new UserDaoImpl();
		System.out.println(dao.find("aaaa"));
	}
	
	@Test
	public void testFindByUsername() {
		UserDao dao = new UserDaoImpl();
		System.out.println(dao.find("aaa"));
	}

	@Test
	public void testFindResIndex() {
		UserDao dao = new UserDaoImpl();
		System.out.println(dao.findResIndex("1"));
	}
}
