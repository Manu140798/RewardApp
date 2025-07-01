package com.rewardapp.rewardprogramdto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*

	Customer class with a single field 'name'.

*/
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Getter
	@Setter
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
