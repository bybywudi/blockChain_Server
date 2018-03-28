package cn.itcast.dao;

import cn.itcast.domain.Block;
import cn.itcast.domain.User;
import org.dom4j.Attribute;

public interface UserDao {

	void add(User user);

	User find(String username, String password);

	void addNewBolck(Block block,int nowid);

	int blockHash(int id);

	public boolean isHashRight(int id,String userId);
	//查找注册的用户是否在数据库中存在
	boolean find(String username);

}