package org.craftedsw.tripservicekata;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata.trip.TripService;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import org.mockito.Mockito;

public class TripServiceTest {
    
    private static final List<Object> NO_TRIPS = asList();
    private static final User LOGGED_USER = new User();
    private static final User GUEST = null;
    private static final Trip TRIP_TO_BAHAMAS = new Trip();
    private static final Trip TRIP_TO_LONDON = new Trip();
    TripService service = Mockito.spy(new TripService());

    @Test(expected=UserNotLoggedInException.class) public void 
    throws_userNotLoggedInException_when_the_viewer_is_guest() throws Exception {
         
        User someOne = null;
        service.getTripsByUser(someOne, GUEST);
    }
    
    @Test public void 
    we_get_no_trips_when_the_given_user_has_no_friends() throws Exception {
        User userWithNoFriends = new User();
        List<Trip> result = service.getTripsByUser(userWithNoFriends, LOGGED_USER );
        
        assertEquals(NO_TRIPS, result);;
    }
    
    @Test public void 
    we_get_no_trips_if_were_not_friends_with_the_given_user() throws Exception {
        User someUser = new User();
        doReturn(asList()).when(service).findTripsByUser(someUser);
        User otherUser = new User();
        someUser.addFriend(otherUser );
        
        List<Trip> trips = service.getTripsByUser(someUser, LOGGED_USER);
        assertEquals(NO_TRIPS, trips);
    }
    
    @Test public void 
    we_get_no_trips_if_the_user_hasnt_traveled() throws Exception {
        User someUser = new User();
        List<Trip> allTrips = asList(TRIP_TO_BAHAMAS, TRIP_TO_LONDON);
        doReturn(allTrips).when(service).findTripsByUser(someUser);
        someUser.addFriend(LOGGED_USER);
        someUser.addTrip(TRIP_TO_BAHAMAS);
        someUser.addTrip(TRIP_TO_LONDON);
        
        List<Trip> trips = service.getTripsByUser(someUser, LOGGED_USER);
        assertEquals(allTrips, trips);;
    }
    
    
	
}
