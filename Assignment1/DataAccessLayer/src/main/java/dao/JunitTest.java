package dao;

import static org.junit.Assert.*;

import org.junit.Test;

import pingpong.Tournament;
import pingpong.User;

public class JunitTest {

	@Test
	public void testFindTournament() {
		TournamentsDao t = new TournamentsDao();
		Tournament found = t.findById(1);
		
		Tournament e = new Tournament(1,"World Championship", "playing", 0);
		
		assertEquals(found.getIdtournament(), e.getIdtournament());
		assertEquals(found.getName(), e.getName());
		assertEquals(found.getStatus(), e.getStatus());
		assertEquals(found.getIdwinner(), e.getIdwinner());
	}
	
	@Test
	public void testFindUser() {
		UsersDao t = new UsersDao();
		User found = t.findById(1);
		
		User e = new User(1,"admin", "admin@email.com", "admin", true);
		
		assertEquals(found.getIduser(), e.getIduser());
		assertEquals(found.getUser(), e.getUser());
		assertEquals(found.getMail(), e.getMail());
		assertEquals(found.getPassword(), e.getPassword());
	}

}
