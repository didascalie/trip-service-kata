package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	private static final ArrayList<Trip> NO_TRIPS = new ArrayList<Trip>();

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = getLoggedUser();
		assertUserIsLogged(loggedUser);
		
		return user.isFriendsWith(loggedUser) ? findTripsByUser(user) : NO_TRIPS;
	}

    protected void assertUserIsLogged(User loggedUser) throws UserNotLoggedInException {
        if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
    }

    public List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

    public User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }
	
}
