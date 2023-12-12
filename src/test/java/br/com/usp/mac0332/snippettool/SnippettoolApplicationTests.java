package br.com.usp.mac0332.snippettool;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {"spring.profiles.active:test"})
@ActiveProfiles("test")
class SnippettoolApplicationTests {

	@Test
	void contextLoads() {
	}

}
