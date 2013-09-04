package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	private static final ArrayList<Trip> NO_TRIPS = new ArrayList<Trip>();

    public TripService(TripDAO mock) {
        // TODO Auto-generated constructor stub
    }

    public List<Trip> getTripsByUser(User traveller, User viewer) throws UserNotLoggedInException {
		assertUserIsLogged(viewer);
		
		return traveller.isFriendsWith(viewer) ? findTripsByUser(traveller) : NO_TRIPS;
	}

    protected void assertUserIsLogged(User loggedUser) throws UserNotLoggedInException {
        if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
    }

    public List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

}
