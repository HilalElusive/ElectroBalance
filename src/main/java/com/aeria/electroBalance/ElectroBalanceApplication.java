package com.aeria.electroBalance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.aeria.electroBalance.repo.UserRepository;


@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class ElectroBalanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectroBalanceApplication.class, args);
	}

}
