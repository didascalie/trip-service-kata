package org.craftedsw.tripservicekata;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.Collections;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata.trip.TripService;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {
    private static final User ME = new User();
    private static final User ANOTHER_USER = new User();
    TripService tripService = new TripService();
    User user = new User();
	
    @Test(expected=UserNotLoggedInException.class) public void 
    throws_exception_for_guests() throws Exception {
        User guest = null;
        tripService.getTripsByUser(user , guest);
    }
    Object noTrips = Collections.emptyList();
    
    @Test public void 
    no_trips_if_user_have_no_friends() throws Exception {
        assertThat(tripService.getTripsByUser(user, ME), equalTo(noTrips)); 
    }
    
    @Test public void 
    no_trips_if_were_not_friends_with_user() throws Exception {
        user.addFriend(ANOTHER_USER);
        assertThat(tripService.getTripsByUser(user, ME), equalTo(noTrips)); 
    }
    
    @Test public void 
    we_see_the_trips_of_friends() throws Exception {
        user.addFriend(ME);
        Trip tripToSingapore = new Trip();
        Object trips = asList(tripToSingapore);
        assertThat(tripService.getTripsByUser(user, ME), equalTo(trips)); 
        
    }
    
    
    
}
