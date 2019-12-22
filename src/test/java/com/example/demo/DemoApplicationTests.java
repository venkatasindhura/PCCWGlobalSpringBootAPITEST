package com.example.demo;

import java.net.URI;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.pccw.rest_api.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
	public static final String REST_SERVICE_URI = "http://localhost:8089/api/v1";
	@Test
	void contextLoads() {
	}

	
	@Test
	/* GET */
    private static void getUser(){
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI+"/users/1", User.class);
        System.out.println(user);
    }
	
 
	@Test
    /* DELETE */
    private static void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/3");
    }
}
