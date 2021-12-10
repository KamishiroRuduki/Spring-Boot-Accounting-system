package com.ubayKyu.accountingSystem;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository2;

@SpringBootApplication
public class AccountingSystemApplication {

    @Autowired
    private UserInfoRepository2 UserInfoRepository2;
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
	public static void main(String[] args) {
		SpringApplication.run(AccountingSystemApplication.class, args);
	}

}
