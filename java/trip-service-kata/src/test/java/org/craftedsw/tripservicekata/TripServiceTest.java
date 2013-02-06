package org.craftedsw.tripservicekata;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.TripService;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {
    TripService tripService = new TripService();
    User user = new User();
	
    @Test(expected=UserNotLoggedInException.class) public void 
    throws_exception_for_guests() throws Exception {
        User guest = null;
        tripService.getTripsByUser(user , guest);
    }
    
    
    
}
