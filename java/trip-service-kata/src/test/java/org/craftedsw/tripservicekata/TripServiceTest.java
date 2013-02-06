package org.craftedsw.tripservicekata;

import static org.junit.Assert.*;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.TripService;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {
    private User user = new User();
    private static final User FRIEND = new User();
    private static final User GUEST = new User();
	
    @Test(expected=UserNotLoggedInException.class) public void 
    throws_exception_for_guests() throws Exception {
        user = null;
        TripService tripService = new TestableTripService();
        tripService.getTripsByUser(FRIEND);
    }
    
    public class TestableTripService extends TripService {

        @Override
        protected User loggedUser() {
            return user;
        }
    }
}
