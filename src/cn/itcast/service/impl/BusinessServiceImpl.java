package cn.itcast.service.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.dao.impl.UserDaoImpl;
import cn.itcast.domain.Block;
import cn.itcast.domain.User;
import cn.itcast.exception.UserExistException;
import cn.itcast.utils.ServiceUtils;

//对web层提供所有业务服务
public class BusinessServiceImpl {

	private UserDao dao = new UserDaoImpl();	//以后可以采用工厂模式  或spring技术 解决层与层分离的问题

	//对web层提供注册服务
	public void register(User user) throws UserExistException {
		//先判断当前要注册 的用户是否存在
		boolean b = dao.find(user.getUsername());
		if(b) {
			throw new UserExistException();		//	发现要注册的用户已存在，则给web层抛一个已存在异常，提醒web层处理这个异常，给用户一个友好提示
		}else {
			user.setPassword(ServiceUtils.md5(user.getPassword()));
			dao.add(user);
		}
	}

	//对web层提供登录服务
	public User login(String username, String password) {	///aaa 123
		password = ServiceUtils.md5(password);
		return dao.find(username,password);
	}

	//写一个新的区块
	public boolean addNewBolck(int id,String userId){
		if(dao.isHashRight(id,userId)){
			return true;
		}else{
			return false;
		}
	}


}
