package com.tedu.sp03.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tedu.sp01.pojo.User;
import com.tedu.sp01.service.UserService;
import com.tedu.web.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RefreshScope  //只有添加了这个注解的实例才会将刷新的数据重新注入，
// 否则spring框架自己判断成本太高
public class UserServiceImpl implements UserService {
	// Error creating bean with name 'userServiceImpl':
	// Injection of autowired dependencies failed
	// Could not resolve placeholder 'sp.user-service.users'in value "${sp.user-service.users}
	@Value("${sp.user-service.users}")
	private String userJson;
	
	@Override
	public User getUser(Integer id) {
		log.info("users json string : "+userJson);
		List<User> list = JsonUtil.from(userJson, new TypeReference<List<User>>() {});
		for (User u : list) {
			if (u.getId().equals(id)) {
				return u;
			}
		}
		
		return new User(id, "name-"+id, "pwd-"+id);
	}

	@Override
	public void addScore(Integer id, Integer score) {
		//TODO 这里增加积分
		log.info("user "+id+" - 增加积分 "+score);
	}

}