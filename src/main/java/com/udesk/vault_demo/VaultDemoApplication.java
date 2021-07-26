package com.udesk.vault_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
