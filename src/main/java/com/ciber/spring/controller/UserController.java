package com.ciber.spring.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciber.spring.model.User;

@RestController
public class UserController {
	
	private Map<String, User> userMap = new HashMap<>();
	
	@PostConstruct
	public void initializeObject() {
		User user1 = new User();
		user1.setId("101");
		user1.setName("Sasi");
		user1.setAddress("Sivakasi");
		userMap.put(user1.getId(), user1);
		
		User user2 = new User();
		user2.setId("102");
		user2.setName("Srini");
		user2.setAddress("Sivakasi");
		userMap.put(user2.getId(), user2);
	}
	
	@RequestMapping(value="/user")
	@Cacheable("user")
	public Collection<User> getUser() throws InterruptedException {
		Thread.sleep(5000);
		return userMap.values();
	}
	
	@RequestMapping(value = "/evictCache")
	@CacheEvict("user")
	public String evictCache() {
		return "Cache is cleared successfully!..";
	}

}
