package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

public class TripService {

	private TripDAO tripDao;

    public TripService(TripDAO tripDao) {
        this.tripDao = tripDao;
    }

    public List<Trip> getTripsByUser(User user, User loggedUser) {
        if (loggedUser == null)
            throw new UserNotLoggedInException(); 
		return user.isFriendsWith(loggedUser) ?
		        tripDao.findTripsBy(user) : noTrips();
    }

    private ArrayList<Trip> noTrips() {
        return new ArrayList<Trip>();
    }

}
