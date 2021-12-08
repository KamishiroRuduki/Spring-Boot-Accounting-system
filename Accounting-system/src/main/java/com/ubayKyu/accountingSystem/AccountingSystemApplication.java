package com.ubayKyu.accountingSystem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ubayKyu.accountingSystem.entity.UserInfo2;
import com.ubayKyu.accountingSystem.repository.UserInfoRepository2;

@SpringBootApplication
public class AccountingSystemApplication {

    @Autowired
    private UserInfoRepository2 UserInfoRepository2;
    
    
	public static void main(String[] args) {
		SpringApplication.run(AccountingSystemApplication.class, args);
	}

/*	public void run(String... args) throws Exception{
		List<UserInfo2> user = UserInfoRepository2.findAll();
		String sql = "SELECT * FROM UserInfo";
		List<UserInfo2> userinfo = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(UserInfo2.class));
	    System.out.println(userinfo.size());
	    userinfo.forEach(System.out::println);
	    user.forEach(System.out::println);
	}*/
}
