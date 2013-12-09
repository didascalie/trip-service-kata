package org.craftedsw.tripservicekata.trip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {
    
    private static final Trip TO_NICE = new Trip();
    private static final Trip TO_BERLIN = new Trip();
    
    protected static final User GUEST = null;
    private static final User REGISTERED_USER = new User();
    private TripDAO tripDao = mock(TripDAO.class);
    TripService tripService = new TripService(tripDao);

    @Test(expected=UserNotLoggedInException.class)
    public void fails_for_guests() throws Exception {
        User irrelevantUser = null;
        tripService.getTripsByUser(irrelevantUser, GUEST);
    }
    
    @Test public void 
    logged_user_cant_see_trips_of_strangers() throws Exception {
         User stranger = new User();
         List<Trip> trips = tripService.getTripsByUser(stranger, REGISTERED_USER );
         assertTrue(trips.isEmpty());
    }
    
    @Test public void 
    logged_user_cant_see_any_trips_if_there_are_none() throws Exception {
        User friend = new User();
        friend.addFriend(REGISTERED_USER);

        List<Trip> trips = tripService.getTripsByUser(friend, REGISTERED_USER);
        assertTrue(trips.isEmpty());
    }
    
    @Test public void 
    logged_users_sees_trips_of_his_friends() throws Exception {
        User friend = new User();
        friend.addFriend(REGISTERED_USER);
        friend.addTrip(TO_NICE);
        friend.addTrip(TO_BERLIN);
        List<Trip> allTrips = Arrays.asList(TO_NICE, TO_BERLIN);
        doReturn(allTrips).when(tripDao).findTripsBy(friend);
        
        List<Trip> trips = tripService.getTripsByUser(friend, REGISTERED_USER);
        
        assertEquals(allTrips, trips);
    }
	
}
