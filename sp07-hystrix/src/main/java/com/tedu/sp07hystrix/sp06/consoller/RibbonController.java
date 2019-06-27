package com.tedu.sp07hystrix.sp06.consoller;

import java.util.List;
import java.util.Random;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tedu.sp01.pojo.Item;
import com.tedu.sp01.pojo.Order;
import com.tedu.sp01.pojo.User;
import com.tedu.web.util.JsonResult;

@RestController
public class RibbonController {
	@Autowired
	private RestTemplate rt;
	
	@GetMapping("/item-service/{orderId}")
	//这里要注意回滚的降级方法名称与下面的名称要一致
	@HystrixCommand(fallbackMethod = "getItemsFB")
	public JsonResult<List<Item>> getItems(@PathVariable String orderId) throws InterruptedException {
	    //这里服务器路径用 service-id 代替，ribbon 会向服务的多台集群服务器分发请求
		//这里为了测试重试和降级熔断的效果，让线程睡眠
		if (new Random().nextInt(10000)>2000) //80%的睡眠可能性
			Thread.sleep(new Random().nextInt(5000) );//睡眠5秒以内
		return rt.getForObject("http://item-service/{1}", JsonResult.class, orderId);
	}

	@PostMapping("/item-service/decreaseNumber")
	@HystrixCommand(fallbackMethod = "decreaseNumberFB")
	public JsonResult decreaseNumber(@RequestBody List<Item> items) {
		return rt.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);
	}

	@GetMapping("/user-service/{userId}")
	@HystrixCommand(fallbackMethod = "getUserFB")
	public JsonResult<User> getUser(@PathVariable Integer userId) {
		return rt.getForObject("http://user-service/{1}", JsonResult.class, userId);
	}

	@GetMapping("/user-service/{userId}/score")
	@HystrixCommand(fallbackMethod = "addScoreFB")
	public JsonResult addScore(
			@PathVariable Integer userId, Integer score) {
		return rt.getForObject("http://user-service/{1}/score?score={2}", JsonResult.class, userId, score);
	}
	
	@GetMapping("/order-service/{orderId}")
	@HystrixCommand(fallbackMethod = "getOrderFB")
	public JsonResult<Order> getOrder(@PathVariable String orderId) {
		return rt.getForObject("http://order-service/{1}", JsonResult.class, orderId);
	}

	@GetMapping("/order-service")
	@HystrixCommand(fallbackMethod = "addOrderFB")
	public JsonResult addOrder() {
		return rt.getForObject(
				"http://order-service/", JsonResult.class);
	}

	public JsonResult<List<Item>> getItemsFB(@PathVariable String orderId){

		return JsonResult.err("获取商品失败");
	}
	public JsonResult decreaseNumberFB(@RequestBody List<Item> items){
		return  JsonResult.err("删除失败");
	}
	public JsonResult<User> getUserFB(@PathVariable Integer userId){
		return  JsonResult.err("获取用户信息失败");

	}
	public JsonResult addScoreFB(@PathVariable Integer userId, Integer score){
		return JsonResult.err("添加积分失败");


	}
	public JsonResult<Order> getOrderFB(@PathVariable String orderId){
		return JsonResult.err("获取订单失败");
	}
	public JsonResult addOrderFB(){
		return JsonResult.err("添加订单失败");
	}





}
