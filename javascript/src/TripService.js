"use strict";

let UserSession = require('./UserSession');
let TripDAO = require('./TripDAO');


class TripService {

    constructor(tripDao) {
        this.tripDao = tripDao;

    }

    getTripsByUser(user, loggedUser) {
        this.assertNotGuest(loggedUser)
        const NO_TRIPS = [];
        return user.isFriendsWith(loggedUser) ? this.tripDao.findTripsByUser(user) : NO_TRIPS;
    }

    assertNotGuest(loggedUser) {
        if (loggedUser == null) {
            throw new Error('User not logged in.');
        }
    }

}

module.exports = TripService
