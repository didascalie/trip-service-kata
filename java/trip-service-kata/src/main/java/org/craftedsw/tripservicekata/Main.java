package org.craftedsw.tripservicekata;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.TripDAO;
import org.craftedsw.tripservicekata.trip.TripService;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class Main {

    public static void main(String[] args)  {
        TripService tripService = new TripService(new TripDAO());
        try {
            tripService.getTripsByUser(new User(), UserSession.getInstance().getLoggedUser());
        } catch (UserNotLoggedInException e) {
            throw new RuntimeException(e);
        }
    }
}
