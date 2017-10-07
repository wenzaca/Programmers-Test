package com.programmers.apply.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.programmers.apply.entities.Position;
import com.programmers.apply.service.PositionService;

@RunWith(SpringRunner.class)
public class PositionServiceImplTest {
	@TestConfiguration
	static class PositionServiceImplTestContextConfiguration {

		@Bean
		public PositionService positionService() {
			return new PositionServiceImpl();
		}
	}

	@Autowired
	private PositionService positionService;

	@Test
	public void whenValidAddress_thenReturnRightLatitudeLongitude() {
		String address = "Avenida John Dalton, 301 Edifício 3, Cj. 13A - Techno Park Campinas, Campinas - SP, 13069-330";

		Position position = positionService.getLongitudeLatitudeByAdress(address);
		Boolean validate = (position != null && position.getLatitude() != null && position.getLongitude() != null)
				? true : false;
		assertThat(validate).isTrue();
	}
}
