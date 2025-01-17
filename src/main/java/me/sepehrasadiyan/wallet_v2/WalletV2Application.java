package me.sepehrasadiyan.wallet_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class WalletV2Application {

	public static void main(String[] args) {
		SpringApplication.run(WalletV2Application.class, args);
	}

}
