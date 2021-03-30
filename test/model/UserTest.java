package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
		
		User user = new User(n,ln,id,us,p);
		
		assertEquals(n, user.getName());
		assertEquals(ln, user.getLastName());
		assertEquals(id, user.getId());
		assertEquals(us, user.getUsername());
		assertEquals(p, user.getPassword());
	}

}
