package com.rewardapp.rewardprogramdto;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*Class for representing customer reward details, including total points, monthly breakdown, and transactions.*/


public class RewardResponse {

private String customerName;
private int totalPoints;
private Map<String, Integer> monthlyBreakdown;
private List<TransactionDetail> transactions;
private static final Logger logger = LoggerFactory.getLogger (RewardResponse.class);

public RewardResponse(String customerName, int totalPoints, Map<String, Integer> monthlyBreakdown,
		List<TransactionDetail> transactions) {
	super();
	this.customerName = customerName;
	this.totalPoints = totalPoints;
	this.monthlyBreakdown = monthlyBreakdown;
	this.transactions = transactions;
}

public String getCustomerName() {
	return customerName;
}

public void setCustomerName(String customerName) {
	this.customerName = customerName;
}

public int getTotalPoints() {
	return totalPoints;
}

public void setTotalPoints(int totalPoints) {
	this.totalPoints = totalPoints;
}

public Map<String, Integer> getMonthlyBreakdown() {
	return monthlyBreakdown;
}

public void setMonthlyBreakdown(Map<String, Integer> monthlyBreakdown) {
	this.monthlyBreakdown = monthlyBreakdown;
}

public List<TransactionDetail> getTransactions() {
	return transactions;
}

public void setTransactions(List<TransactionDetail> transactions) {
	this.transactions = transactions;
}


}