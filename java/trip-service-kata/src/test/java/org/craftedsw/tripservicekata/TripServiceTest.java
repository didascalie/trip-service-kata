package org.craftedsw.tripservicekata;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata.trip.TripService;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Ignore;
import org.junit.Test;

public class TripServiceTest {
    private User loggedUser ;
    private static final User FRIEND = new User();
    private static final User GUEST = null;
    private static final User EXISTING_USER = new User();
    private static final User ANOTHER_USER = new User();
    TripService tripService = new TestableTripService();
	
    @Test(expected=UserNotLoggedInException.class) public void 
    throws_exception_for_guests() throws Exception {
        loggedUser = GUEST;
        tripService.getTripsByUser(FRIEND);
    }
    
    @Test public void 
    no_trips_when_the_user_has_no_friends() throws Exception {
        loggedUser = EXISTING_USER;
        List<Trip> noTrips = Collections.emptyList();
        expectToSee(noTrips);
    }

    @Test public void 
    no_trips_when_were_not_friends_with_the_user() throws Exception {
         loggedUser = EXISTING_USER;
         FRIEND.addFriend(ANOTHER_USER);
         List<Trip> noTrips = Collections.emptyList();
         expectToSee(noTrips);
    }
    
    @Test @Ignore public void 
    we_see_the_trips_of_friends() throws Exception {
        loggedUser = EXISTING_USER;
        FRIEND.addFriend(loggedUser);
        Trip trip1 = new Trip();
        FRIEND.addTrip(trip1);
        Trip trip2 = new Trip();
        FRIEND.addTrip(trip2);
        
        expectToSee(asList(trip1, trip2));
        
        
    }
    
    private void expectToSee(List<Trip> noTrips) throws UserNotLoggedInException {
        assertThat(tripService.getTripsByUser(FRIEND), equalTo(noTrips));
    }
    
    public class TestableTripService extends TripService {

        @Override
        protected User loggedUser() {
            return loggedUser;
        }
    }
}
