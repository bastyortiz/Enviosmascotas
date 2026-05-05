package com.duoc.enviosmascotas;

import com.duoc.enviosmascotas.repository.EnvioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class EnviosmascotasApplicationTests {

	@Autowired
	private EnvioRepository envioRepository;

	@Test
	void contextLoads() {
		assertTrue(envioRepository.count() >= 3);
	}

}
