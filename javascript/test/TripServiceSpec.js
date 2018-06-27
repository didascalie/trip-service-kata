"use strict";

const { assert, expect }  = require('chai')
const sinon = require('sinon')
const User = require('../src/User')
const Trip = require('../src/Trip')
let TripService = require('../src/TripService');

describe('TripService', () => {

    it('throws error when user is not authentified', () => {
        const tripService = new TripService()

        let guest = undefined;
        sinon.stub(tripService, 'getLoggedUser').returns(guest)

        expect(() => tripService.getTripsByUser(undefined)).throw('User not logged in.')
    });

    it('returns no trips when traveler has no friends', () => {
        const tripService = new TripService()

        let loggedUser = new User();
        sinon.stub(tripService, 'getLoggedUser').returns(loggedUser)

        let notFriendsWithLoggedUser = new User();
        const trips = tripService.getTripsByUser(notFriendsWithLoggedUser)

        expect(trips).empty
    });

    it('returns no trips when traveler has friends but logged user is not part of them', () => {
        const tripService = new TripService()

        let loggedUser = new User();
        sinon.stub(tripService, 'getLoggedUser').returns(loggedUser)

        let notFriendsWithLoggedUser = new User();
        notFriendsWithLoggedUser.addFriend(new User())
        const trips = tripService.getTripsByUser(notFriendsWithLoggedUser)

        expect(trips).empty
    });

    it('returns all trips when users are friends', () => {
        const tripService = new TripService()

        let loggedUser = new User();
        sinon.stub(tripService, 'getLoggedUser').returns(loggedUser)
        let allTrips = [new Trip('corse'), new Trip('algarve')];
        sinon.stub(tripService, 'findTripsBy').returns(allTrips)

        let notFriendsWithLoggedUser = new User();
        notFriendsWithLoggedUser.addFriend(loggedUser)
        const trips = tripService.getTripsByUser(notFriendsWithLoggedUser)

        expect(trips).eql(allTrips)
    });




});

