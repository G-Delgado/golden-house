package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoldenHouseTest {
	
	private void setupScenaryOne() {
		
	}
	
	private void setupScenaryTwo() {
		
	}
	
	@Test
	public void testGoldenHouseClientList() {
		setupScenaryOne();
		
		GoldenHouse gh = new GoldenHouse();
		
		assertNotNull(gh.getClients());
		assertTrue(gh.getClients().isEmpty());
	}
	
	@Test
	public void testAddClient() {
		setupScenaryTwo();
		
		GoldenHouse gh = new GoldenHouse();
		String n = "Felipe";
		String ln = "Alvarado";
		String id = "f5453r";
		String ad = "Calle 5 #65-57";
		String ph = "3167814343";
		String obs = "Sapetas";
			
		gh.addClient(n, ln, id, ad, ph, obs);
		assertEquals(1, gh.getClients().size());
		
		Client cl = gh.getClients().get(0);
		assertEquals(n, cl.getName());
		assertEquals(ln, cl.getLastName());
		assertEquals(id, cl.getId());
		assertEquals(ad, cl.getAddress());
		assertEquals(ph, cl.getPhoneNumber());
		assertEquals(obs, cl.getObservations());
	}

}
