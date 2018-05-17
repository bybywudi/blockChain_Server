package cn.itcast.junit.test;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.User;
import org.junit.Test;

import java.util.Date;
import java.util.List;

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
	public void testGetUserIps() {
		UserDao dao = new UserDaoImpl();
		List list = dao.getUserIps();
		System.out.println(dao.getUsersTotalNumber());
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i));
		}
		List<String> list2 = dao.getUserIps();
		String[] ips = new String[list.size()];
		list2.toArray(ips);
		for(int i=0;i<list.size();i++){
			System.out.println(ips[i]);
		}
	}

}
