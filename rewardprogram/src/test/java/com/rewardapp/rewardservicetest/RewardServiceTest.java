package com.rewardapp.rewardservicetest;

import com.rewardapp.rewardexception.CustomerNotFoundException;
import com.rewardapp.rewardprogramdto.RewardResponse;
import com.rewardapp.rewardprogramdto.Transaction;
import com.rewardapp.rewardrepository.TransactionRepository;
import com.rewardapp.rewardservice. RewardService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

import java.time. LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


/*Junit -mockito for service class */
	public class RewardServiceTest {

	@Mock
	private TransactionRepository repository;

	@InjectMocks
	private RewardService rewardService;

	private List<Transaction> mockTransactions;

	@BeforeEach
	public void setup() {

	MockitoAnnotations.openMocks (this);

	mockTransactions =List.of(

	new Transaction("cust1", LocalDate.of (2023, 2, 10), 80.0),
	new Transaction("cust1", LocalDate.of (2023, 1, 15), 120.0) );
	}

	@Test
	public void testGetRewardsForCustomer_Success() {

	LocalDate start =LocalDate.of(2023, 1, 1);

	LocalDate end =LocalDate.of(2023, 3, 31);
	
	String customer="cust1"; 

	when(repository.findByCustomerNameIgnoreCase(customer)).thenReturn(mockTransactions); 
	when (repository.findByCustomerNameIgnoreCaseAndDateBetween (customer, start, end)).thenReturn(mockTransactions);

	RewardResponse response =rewardService.getRewards(customer, start, end);

	assertNotNull(response);
	assertEquals("cust1", response.getCustomerName());
	assertTrue(response.getTotalPoints() > 0); 
	assertEquals(2, response.getTransactions().size());
	}
	
	@Test
	public void testGetRewardsForCustomerNotFound() {

	String customer ="nonexistent";
	LocalDate start =LocalDate.of (2023, 1, 1);
	LocalDate end= LocalDate.of (2023, 3, 31);
	
	when(repository.findByCustomerNameIgnoreCase(customer)).thenReturn (Collections.emptyList());

	assertThrows(CustomerNotFoundException.class, () -> { rewardService.getRewards(customer, start, end);

	});

	}

	@Test
	public void testGetRewardsForAllCustomers() {

	LocalDate start = LocalDate.of (2023, 1, 1);

	LocalDate end =LocalDate.of (2023, 3, 31);

	List<Transaction> allTx = new ArrayList<>(mockTransactions); 
	allTx.add(new Transaction("cust2", LocalDate.of (2023, 1, 5), 50.0));

	when(repository.findByDateBetween (start, end)).thenReturn(allTx);

	when(repository.findByCustomerNameIgnoreCaseAndDateBetween(eq("cust1"), eq(start), eq(end)))
     .thenReturn(mockTransactions);

	when (repository.findByCustomerNameIgnoreCaseAndDateBetween (eq("cust2"), eq(start), eq(end)))
	.thenReturn (List.of(new Transaction("cust2", LocalDate.of (2023, 1, 5), 50.0)));

	List<RewardResponse> responseList = rewardService.getRewards (start, end);

	assertNotNull (responseList);

	assertEquals (2, responseList.size());

	assertTrue(responseList.stream().anyMatch(r-> r.getCustomerName().equals("cust1"))); assertTrue(responseList.stream().anyMatch(r-> r.getCustomerName().equals("cust2")));
	
}
}