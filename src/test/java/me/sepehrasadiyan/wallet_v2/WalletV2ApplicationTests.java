package me.sepehrasadiyan.wallet_v2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class WalletV2ApplicationTests {

	@Test
	void contextLoads() {
	}

}
