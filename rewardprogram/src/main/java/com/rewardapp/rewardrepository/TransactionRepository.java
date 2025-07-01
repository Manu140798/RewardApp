package com.rewardapp.rewardrepository;
import java.time. LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rewardapp.rewardprogramdto.Transaction;

/*ransaction Repository interface for accessing Transaction data from the database.*/

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

List<Transaction> findByCustomerNameIgnoreCase (String customerName);
List<Transaction> findByCustomerNameIgnoreCaseAndDateBetween(String customerName, LocalDate start, LocalDate end);
List<Transaction> findByDateBetween (LocalDate start, LocalDate end);

}
