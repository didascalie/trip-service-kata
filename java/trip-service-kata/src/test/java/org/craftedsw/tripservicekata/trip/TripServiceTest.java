package org.craftedsw.tripservicekata.trip;

import junit.framework.Assert;
import org.assertj.core.api.Assertions;
import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class TripServiceTest {
    private static final Trip ROME = new Trip();
    private static final Trip CORSE = new Trip();
    TripService tripService = Mockito.spy(new TripService());
    Object guest = null;
    User viewer = new User();
    User traveler = new User();

    @Test(expected = UserNotLoggedInException.class)
    public void guestsCantSeeTrips() throws Exception {
        Mockito.doReturn(guest).when(tripService).getLoggedUser();

        User notImportant = null;
        tripService.getTripsByUser(notImportant);
    }

    @Test
    public void viewerCantSeeTripsWhenTravelerHasNoFriends() throws Exception {
        Mockito.doReturn(viewer).when(tripService).getLoggedUser();

        List<Trip> trips = tripService.getTripsByUser(traveler);

        Assertions.assertThat(trips).isEmpty();
    }

    @Test
    public void viewerCantSeeTripsOfTravelersHeIsNotFriendsWith() throws Exception {
        Mockito.doReturn(viewer).when(tripService).getLoggedUser();
        traveler.addFriend(new User());

        List<Trip> trips = tripService.getTripsByUser(traveler);

        Assertions.assertThat(trips).isEmpty();
    }

    @Test
    public void viewerCanSeeTripsWhenFriendsWithTraveler() throws Exception {
        Mockito.doReturn(viewer).when(tripService).getLoggedUser();
        List<Trip> trips = Arrays.asList(CORSE, ROME);
        Mockito.doReturn(trips).when(tripService).getTripsByUser(traveler);

        traveler.addFriend(viewer);

        List<Trip> visibleTrips = tripService.getTripsByUser(traveler);

        Assertions.assertThat(visibleTrips).isEqualTo(trips);


    }
}
