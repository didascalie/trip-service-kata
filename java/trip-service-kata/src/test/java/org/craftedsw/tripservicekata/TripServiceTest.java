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
import org.junit.Before;
import org.junit.Test;

public class TripServiceTest {
    private static final User FRIEND = new User();
    private static final User GUEST = null;
    private static final User EXISTING_USER = new User();
    private static final User ANOTHER_USER = new User();
    private static final List<Trip> NO_TRIPS = Collections.emptyList();

    private User loggedUser ;
    private List<Trip> trips;
     
    @Test(expected=UserNotLoggedInException.class) public void 
    throws_exception_for_guests() throws Exception {
        TripService tripService = new TestableTripService();
        loggedUser = GUEST;
        tripService.getTripsByUser(FRIEND);
    }
    
    @Test public void 
    no_trips_when_the_user_has_no_friends() throws Exception {
        TripService tripService = new TestableTripService();
        loggedUser = EXISTING_USER;
        assertThat(tripService.getTripsByUser(FRIEND), equalTo(NO_TRIPS));
    }

    @Test public void 
    no_trips_when_were_not_friends_with_the_user() throws Exception {
        TripService tripService = new TestableTripService();
         loggedUser = EXISTING_USER;
         FRIEND.addFriend(ANOTHER_USER);
         assertThat(tripService.getTripsByUser(FRIEND), equalTo(NO_TRIPS));
    }
    
    @Test public void 
    we_see_the_trips_of_friends() throws Exception {
        TripService tripService = new TestableTripService();
        loggedUser = EXISTING_USER;
        FRIEND.addFriend(loggedUser);

        Trip toSingapore = new Trip();
        Trip toBahamas = new Trip();
        FRIEND.addTrip(toSingapore);
        FRIEND.addTrip(toBahamas);
        
        trips = asList(toSingapore, toBahamas);
        assertThat(tripService.getTripsByUser(FRIEND), equalTo(trips));
    }
    
    public class TestableTripService extends TripService {

        @Override
        protected User loggedUser() {
            return loggedUser;
        }
        
        @Override
        public List<Trip> findTripsBy(User user) {
            return trips;
        }
    }
}
