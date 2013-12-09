package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		assertNotGuest(); 
		return user.isFriendsWith(getLoggedUser()) ?
		        findTripsByUser(user) : noTrips();
	}

    private ArrayList<Trip> noTrips() {
        return new ArrayList<Trip>();
    }

    private void assertNotGuest() {
        if (getLoggedUser() == null)
            throw new UserNotLoggedInException();
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
	
}
