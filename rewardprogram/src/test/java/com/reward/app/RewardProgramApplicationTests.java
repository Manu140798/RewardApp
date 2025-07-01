package com.reward.app;

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

import org.junit.jupiter.api.Test;

@SpringBootTest (webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class RewardProgramApplicationTests {
			
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String baseUrl (String path) { return "http://localhost:" + port+path;}

	@Test
	public void testGetRewardsForSingleCustomer() {


	String url= baseUrl("/api/rewards/Alice?startDate=2025-04-01&endDate=2025-06-30");

	ResponseEntity<RewardResponse> response= restTemplate.getForEntity(url, RewardResponse.class);

	assertEquals(HttpStatus.OK, response.getStatusCode());

	RewardResponse reward =response.getBody();

	assertNotNull(reward);

	assertEquals("Alice", reward.getCustomerName());

	assertTrue(reward.getTotalPoints() > 0);

	assertTrue(reward.getMonthlyBreakdown().containsKey("2025-04"));

}
	@Test
	public void testGetRewardsForAllCustomers() {

	String url = baseUrl("/api/rewards?startDate=2025-04-01&endDate=2025-06-30");

	ResponseEntity<RewardResponse[]> response = restTemplate.getForEntity(url, RewardResponse[].class);

	assertEquals(HttpStatus.OK, response.getStatusCode());
	RewardResponse[] rewards = response.getBody();
	assertNotNull(rewards);
	assertTrue(rewards.length >= 2);
	}

	@Test
	public void testCustomerNotFound() {

	String url = baseUrl("/api/rewards/UnknownUser?startDate=2025-04-01&endDate=2025-06-30");

	ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

	assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

	assertTrue(response.getBody().contains("Customer not found: UnknownUser"));
}}