package com.rewardapp.rewardprogramdto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private Long id;
private String customerName;
private double amount;
private LocalDate date;
public Transaction(Long id, String customerName, double amount, LocalDate date) {
	super();
	this.id = id;
	this.customerName = customerName;
	this.amount = amount;
	this.date = date;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}



}
