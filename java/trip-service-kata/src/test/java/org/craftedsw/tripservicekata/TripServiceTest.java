package org.craftedsw.tripservicekata;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata.trip.TripService;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {
    private User loggedUser ;
    private static final User FRIEND = new User();
    private static final User GUEST = null;
    private static final User EXISTING_USER = new User();
    TripService tripService = new TestableTripService();
	
    @Test(expected=UserNotLoggedInException.class) public void 
    throws_exception_for_guests() throws Exception {
        loggedUser = GUEST;
        tripService.getTripsByUser(FRIEND);
    }
    
    @Test public void 
    no_trips_when_were_not_friends() throws Exception {
        loggedUser = EXISTING_USER;
        List<Trip> noTrips = Collections.emptyList();
        assertThat(tripService.getTripsByUser(FRIEND), equalTo(noTrips));
    }
    public class TestableTripService extends TripService {

        @Override
        protected User loggedUser() {
            return loggedUser;
        }
    }
}
