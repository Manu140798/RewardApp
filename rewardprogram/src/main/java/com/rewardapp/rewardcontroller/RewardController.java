package com.rewardapp.rewardcontroller;

import org.springframework.web.bind.annotation.RestController;

import com.rewardapp.rewardprogramdto.RewardResponse;
import com.rewardapp.rewardservice.RewardService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*REST controller for handling reward-related endpoints.
 Provides APIs to fetch reward data for all customers or a specific customer within a date range.
*/

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

@Autowired
private RewardService service;

@GetMapping
public List<RewardResponse> getAll(

@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate) {

return service.getRewards (startDate, endDate);

}

@GetMapping("/{customer}")
public RewardResponse getForCustomer (

@PathVariable String customer,
@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate,
@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate) {

return service.getRewards(customer, startDate, endDate);

}

}
