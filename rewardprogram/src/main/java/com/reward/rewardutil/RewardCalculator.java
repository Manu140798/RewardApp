package com.reward.rewardutil;

/*utility class RewardCalculator to calculate reward points
based on transaction amount.*/


public class RewardCalculator {
	
public static int calculate (double amount) 
 {

	int pts=0;
	if(amount >100) {
	pts += (int)((amount-100)*2) + 50;
	} 
	else if(amount >50)
	{
		
	pts += (int) (amount -50);
	
	}
	return pts;

 }
}
