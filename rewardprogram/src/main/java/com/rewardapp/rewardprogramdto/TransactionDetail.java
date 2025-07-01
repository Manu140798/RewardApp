package com.rewardapp.rewardprogramdto;

import lombok.*;

/* Class representing details of a transaction including amount, date, and earned reward points.
*/

@Setter
@Getter
@NoArgsConstructor
public class TransactionDetail {
	
	public double amount;
	public String date;
	public int points;
	
	
	
	public TransactionDetail(double amount, String date, int points) {
		super();
		this.amount = amount;
		this.date = date;
		this.points = points;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	

}
