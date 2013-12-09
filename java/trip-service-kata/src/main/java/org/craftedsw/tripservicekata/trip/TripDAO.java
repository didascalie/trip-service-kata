package org.craftedsw.tripservicekata.trip;

import java.util.List;

import org.craftedsw.tripservicekata.exception.DependendClassCallDuringUnitTestException;
import org.craftedsw.tripservicekata.user.User;

public class TripDAO {

    /**
     * @deprecated (use instance method)
     */
	public static List<Trip> findTripsByUser(User user) {
		throw new DependendClassCallDuringUnitTestException(
				"TripDAO should not be invoked on an unit test.");
	}

    public List<Trip> findTripsBy(User user) {
        return findTripsByUser(user);
    }
	
}