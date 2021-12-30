package com.infiniteskills.data.entities.ids;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CurrencyId implements Serializable{

	private String name;
	
	private String countryName;
	
	public CurrencyId() {
		
	}

	public String getName() {
		return name;
	}

	public String getCountryName() {
		return countryName;
	}

	public CurrencyId(String name, String countryName) {
		super();
		this.name = name;
		this.countryName = countryName;
	}

	
	
	
}
