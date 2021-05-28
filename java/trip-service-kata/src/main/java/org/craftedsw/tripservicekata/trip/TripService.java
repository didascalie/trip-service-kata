package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        return getTripsByUser(user, getLoggedUser());
    }

    public List<Trip> getTripsByUser(User user, User loggedUser1) throws UserNotLoggedInException {
		List<Trip> tripList = new ArrayList<Trip>();
		User loggedUser = loggedUser1;
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				tripList = findTripsByUser(user);
			}
			return tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

	protected List<Trip> findTripsByUser(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User getLoggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}
