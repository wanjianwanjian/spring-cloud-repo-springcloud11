package com.tedu.sp09feign.service;

import com.tedu.sp01.pojo.User;
import com.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

@Component
public class UserFeignServiceFB  implements  UserFeignService{
	@Override
	public JsonResult<User> getUser(Integer userId) {
		return JsonResult.err("获取用户信息失败");
	}

	@Override
	public JsonResult addScore(Integer userId, Integer score) {
		return JsonResult.err("添加积分失败");
	}
}
