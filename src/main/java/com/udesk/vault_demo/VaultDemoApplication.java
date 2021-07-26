package com.udesk.vault_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@SpringBootApplication
public class VaultDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaultDemoApplication.class, args);
	}

	@Autowired
	Environment env;
	@RequestMapping("/")
	public String home() {
		String dbusername = env.getProperty("dbusername");
		String dbpassword = env.getProperty("dbpassword");
		return "hello world: " + dbusername+":"+dbpassword;
	}

	@Autowired private JdbcTemplate jdbcTemplate;
	@RequestMapping("/addUser")
	public Map addUser(){
		String name = UUID.randomUUID().toString();
		String pwd = UUID.randomUUID().toString();

		String sql = "INSERT INTO users (fullname, email, password) VALUES (?, ?, ?)";
		int result = jdbcTemplate.update(sql, name,name+"@temp.com",pwd);

		if (result > 0) {
			System.out.println("A new row has been inserted.");
		}
		Map<String, Object> rtn = new LinkedHashMap<>();
		rtn.put("status", 200);
		rtn.put("count", result);
		rtn.put("username", name);
		rtn.put("password", pwd);
		return rtn;
	}

	@RequestMapping("/users")
	public Map Users(){
		String sql = "Select * from users limit 1;";
		Map result  = jdbcTemplate.queryForMap(sql);

		return result;
	}


}
