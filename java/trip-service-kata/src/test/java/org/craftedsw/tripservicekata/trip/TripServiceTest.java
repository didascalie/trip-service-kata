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
    User guest = null;
    User viewer = new User();
    User traveler = new User();

    @Test(expected = UserNotLoggedInException.class)
    public void guestsCantSeeTrips() throws Exception {
        Mockito.doReturn(guest).when(tripService).getLoggedUser();

        User notImportant = null;
        tripService.getTripsByUser(notImportant, guest);
    }

    @Test
    public void viewerCantSeeTripsWhenTravelerHasNoFriends() throws Exception {

        List<Trip> trips = tripService.getTripsByUser(traveler, viewer);

        Assertions.assertThat(trips).isEmpty();
    }

    @Test
    public void viewerCantSeeTripsOfTravelersHeIsNotFriendsWith() throws Exception {
        traveler.addFriend(new User());

        List<Trip> trips = tripService.getTripsByUser(traveler, viewer);

        Assertions.assertThat(trips).isEmpty();
    }

    @Test
    public void viewerCanSeeTripsWhenFriendsWithTraveler() throws Exception {
        List<Trip> trips = Arrays.asList(CORSE, ROME);
        Mockito.doReturn(trips).when(tripService).findTripsByUser(traveler);

        traveler.addFriend(viewer);

        List<Trip> visibleTrips = tripService.getTripsByUser(traveler, viewer);

        Assertions.assertThat(visibleTrips).isEqualTo(trips);


    }
}
