package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {
    
    protected static final User GUEST = null;

    @Test(expected=UserNotLoggedInException.class)
    public void fails_for_guests() throws Exception {
        TripService tripService = new TripService() {
            @Override
            protected User getLoggedUser() {
                return GUEST;
            }
        };
        tripService.getTripsByUser(null);
    }
	
}
