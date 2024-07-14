package me.sepehrasadiyan.wallet_v2;

import org.springframework.boot.SpringApplication;

public class TestWalletV2Application {

	public static void main(String[] args) {
		SpringApplication.from(WalletV2Application::main).with(TestcontainersConfiguration.class).run(args);
	}

}
