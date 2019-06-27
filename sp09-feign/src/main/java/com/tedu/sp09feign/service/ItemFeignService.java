package com.tedu.sp09feign.service;

import com.tedu.sp01.pojo.Item;
import com.tedu.web.util.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "item-service" ,fallback =ItemFeignServiceFB.class )
public interface ItemFeignService {
	@GetMapping("/{orderId}")  //这里的注解是使用了代理对象反向拼接url,
		// 可以根据Feignclient注解里面的服务ID得知主机端口+item-service
		// 另一方面，这种请求的格式也有利于负载均衡
	JsonResult<List<Item>> getItems(@PathVariable String orderId);

	@PostMapping("/decreaseNumber")
	JsonResult decreaseNumber(@RequestBody List<Item> items);
}
