"use strict";

let UserSession = require('./UserSession');
let TripDAO = require('./TripDAO');

class TripService {

    getTripsByUser(user) {
        let loggedUser = this.getLoggedUser();
        this.assertNotGuest(loggedUser);

        let noTrips = [];
        return user.isFriendsWith(loggedUser) ? this.findTripsBy(user) : noTrips;
    }

    assertNotGuest(loggedUser) {
        if (loggedUser == null) {
            throw new Error('User not logged in.');
        }
    }

    findTripsBy(user) {
        return TripDAO.findTripsByUser(user);
    }

    getLoggedUser() {
        let loggedUser = UserSession.getLoggedUser();
        return loggedUser;
    }
}

module.exports = TripService
