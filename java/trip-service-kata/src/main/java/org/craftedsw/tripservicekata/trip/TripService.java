package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

	private TripDAO dao;

    public TripService(TripDAO dao) {
        this.dao = dao;
    }

    public List<Trip> getTripsByUser(User traveller, User viewer) throws UserNotLoggedInException {
		assertUserIsLogged(viewer);
		
		return traveller.isFriendsWith(viewer) ? dao.findTripsBy(traveller) : noTrips();
	}

    private List<Trip> noTrips() {
        return new ArrayList<Trip>();
    }
    
    private void assertUserIsLogged(User loggedUser) throws UserNotLoggedInException {
        if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
    }


}
