package com.rewardapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import com.rewardapp.rewardprogramdto.RewardResponse;


/*this is complete API test*/
@SpringBootTest (webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RewardProgramApplicationTests {
			
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	
	private String baseUrl (String path) { return "http://localhost:" + port+path;}

	@Test
	public void testGetRewardsForSingleCustomer() {


	String url= baseUrl("/api/rewards/Satyam?startDate=2024-04-01&endDate=2024-06-30");

	ResponseEntity<RewardResponse> response= restTemplate.getForEntity(url, RewardResponse.class);

	assertEquals(HttpStatus.OK, response.getStatusCode());

	RewardResponse reward =response.getBody();

	assertNotNull(reward);

	assertEquals("Satyam", reward.getCustomerName());

	assertTrue(reward.getTotalPoints() > 0);

	assertTrue(reward.getMonthlyBreakdown().containsKey("2024-04"));

}
	@Test
	public void testGetRewardsForAllCustomers() {

	String url = baseUrl("/api/rewards?startDate=2024-04-01&endDate=2024-06-30");

	ResponseEntity<RewardResponse[]> response = restTemplate.getForEntity(url, RewardResponse[].class);

	assertEquals(HttpStatus.OK, response.getStatusCode());
	RewardResponse[] rewards = response.getBody();
	assertNotNull(rewards);
	assertTrue(rewards.length >= 2);
	}

	@Test
	public void testCustomerNotFound() {

	String url = baseUrl("/api/rewards/UnknownUser?startDate=2024-04-01&endDate=2024-06-30");

	ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

	assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	assertTrue(response.getBody().contains("Customer not found: UnknownUser"));
}}