package com.springsecurity;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.springsecurity.utilities.Encryption;
public class EncyptedDatasource extends DriverManagerDataSource{
	@Override
	public String getPassword() {
		
		return Encryption.decrypt(super.getPassword());
	}

	EncyptedDatasource(){}
}
