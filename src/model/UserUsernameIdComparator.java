package model;

import java.util.Comparator;

public class UserUsernameIdComparator implements Comparator<User>{

	@Override
	public int compare(User u1, User u2) {
		int result = 0;
		if (u1.getUsername().compareTo(u2.getUsername()) > 0) {
			result = 1;
		} else if (u1.getUsername().compareTo(u2.getUsername()) < 0){
			result = -1;
		} else {
			result =  u1.getId().compareTo(u2.getId());
		}
		return result;
	}

}
