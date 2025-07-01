package com.rewardapp.rewardservice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.logging.slf4j.SLF4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reward.rewardutil.RewardCalculator;
import com.rewardapp.rewardexception.CustomerNotFoundException;
import com.rewardapp.rewardprogramdto.RewardResponse;
import com.rewardapp.rewardprogramdto.Transaction;
import com.rewardapp.rewardprogramdto.TransactionDetail;
import com.rewardapp.rewardrepository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class RewardService {


	
private final DateTimeFormatter month_format = DateTimeFormatter.ofPattern("yyyy-MM");

private static final  Logger Logger=LoggerFactory.getLogger(RewardService.class);

@Autowired
private TransactionRepository repository;


/*Calculates reward points for a specific customer between a date range.
 
 */

public RewardResponse getRewards (String customer, LocalDate start, LocalDate end) {

List<Transaction> allCustomerTransactions= repository.findByCustomerNameIgnoreCase(customer);
if (allCustomerTransactions.isEmpty()) {

throw new CustomerNotFoundException(customer);

}


List<Transaction> filteredTransactions = repository

.findByCustomerNameIgnoreCaseAndDateBetween (customer, start, end);

Logger.info("This is an info log: reward calculated for specific customer");

return buildRewardResponse(customer, filteredTransactions);

}

/**

* Calculates reward points for all customers within a date range.

*/

public List<RewardResponse> getRewards(LocalDate start, LocalDate end) {

List<Transaction> allTransactions = repository.findByDateBetween(start, end);

return allTransactions.stream()

.map(Transaction::getCustomerName)

.distinct()

.map(name -> {

List<Transaction> customerTx = repository

.findByCustomerNameIgnoreCaseAndDateBetween (name, start, end);

return buildRewardResponse(name, customerTx);

})
.collect(Collectors.toList());


}

/*

-Core reward calculation logic, including monthly breakdown and details.

*/

private RewardResponse buildRewardResponse(String customer, List<Transaction> transactions) {

Map<String, Integer> monthlyPoints = new HashMap<>();

List<TransactionDetail> transactionDetails = new ArrayList<>();

int totalPoints = 0;

for (Transaction tx: transactions) {

int points = RewardCalculator.calculate(tx.getAmount());

String month = tx.getDate().format(month_format);

monthlyPoints.merge(month, points, Integer::sum);

transactionDetails.add(new TransactionDetail(

tx.getAmount(), tx.getDate().toString(), points));

totalPoints += points;

}

return new RewardResponse(customer, totalPoints, monthlyPoints, transactionDetails);

}
}



