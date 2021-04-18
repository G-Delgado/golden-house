package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserTest {

	private void setupScenaryOne() {
		
	}
	
	@Test
	public void testClient() {
		setupScenaryOne();
		
		String n = "Gabriel";
		String ln = "Delgado";
		String id = "sape13242";
		String us = "Mirrorbeast";
		String p = "sapetas";
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();
		
		User user = new User(n,ln,id,us,p, date, time);
		
		assertEquals(n, user.getName());
		assertEquals(ln, user.getLastName());
		assertEquals(id, user.getId());
		assertEquals(us, user.getUsername());
		assertEquals(p, user.getPassword());
		assertEquals(date, user.getDate());
		assertEquals(time, user.getTime());
	}

}
