package org.craftedsw.tripservicekata;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.Trip;
import org.craftedsw.tripservicekata.trip.TripDAO;
import org.craftedsw.tripservicekata.trip.TripService;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Before;
import org.junit.Test;

public class TripServiceTest {
    
    private static final List<Trip> NO_TRIPS = asList();
    private static final User LOGGED_USER = new User();
    private static final User GUEST = null;
    private static final Trip TRIP_TO_BAHAMAS = new Trip();
    private static final Trip TRIP_TO_LONDON = new Trip();
    TripService service;
    private TripDAO tripDao;

     
    @Before
    public void initBeforeTest() throws Exception {
        tripDao = mock(TripDAO.class);
        service = new TripService(tripDao);
    }
    
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
        when(tripDao.findTripsBy(someUser)).thenReturn(NO_TRIPS);
        User otherUser = new User();
        someUser.addFriend(otherUser );
        
        List<Trip> trips = service.getTripsByUser(someUser, LOGGED_USER);
        assertEquals(NO_TRIPS, trips);
    }
    
    @Test public void 
    we_get_no_trips_if_the_user_hasnt_traveled() throws Exception {
        User someUser = new User();
        List<Trip> allTrips = asList(TRIP_TO_BAHAMAS, TRIP_TO_LONDON);
        when(tripDao.findTripsBy(someUser)).thenReturn(allTrips);
        someUser.addFriend(LOGGED_USER);
        someUser.addTrip(TRIP_TO_BAHAMAS);
        someUser.addTrip(TRIP_TO_LONDON);
        
        List<Trip> trips = service.getTripsByUser(someUser, LOGGED_USER);
        assertEquals(allTrips, trips);;
    }
    
    
	
}
