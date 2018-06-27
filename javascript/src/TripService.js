"use strict";

let UserSession = require('./UserSession');
let TripDAO = require('./TripDAO');

class TripService {

    getTripsByUser(user) {
        let tripList = [];
        let loggedUser = this.getLoggedUser();
        let isFriend = false;
        if (loggedUser != null) {
            let friends = user.getFriends();
            for (let i=0; i < friends.length; i++) {
                let friend = friends[i];
                if (friend == loggedUser) {
                    isFriend = true;
                    break;
                }
            };
            if (isFriend) {
                tripList = this.findTripsBy(user);
            }
            return tripList;
        } else {
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
