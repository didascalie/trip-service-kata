"use strict";

module.exports = class User {

    constructor(name) {
        this.name = name;
        this.trips = [];
        this.friends = [];
    }

    isFriendsWith(user) {
        return this.friends.indexOf(user) > -1;
    }

    getTrips() {
        return this.trips;
    }

    getFriends() {
        return this.friends;
    }

    addFriend(user) {
        this.friends.push(user);
    }

    addTrip(trip) {
        this.trips.push(trip);
    }
}
