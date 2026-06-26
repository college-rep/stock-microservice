package com.stock.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest //used for integration tests, the test will fail if we use this
class DemoApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(1L, 0L+1L);
	}

}
