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
        let whateverUser = undefined;
        expect(() => tripService.getTripsByUser(whateverUser, guest)).throw('User not logged in.')
    });

    it('returns no trips when traveler has no friends', () => {
        const tripService = new TripService()

        let loggedUser = new User();

        let notFriendsWithLoggedUser = new User();
        const trips = tripService.getTripsByUser(notFriendsWithLoggedUser, loggedUser)

        expect(trips).empty
    });

    it('returns no trips when traveler has friends but logged user is not part of them', () => {
        const tripService = new TripService()

        let loggedUser = new User();

        let notFriendsWithLoggedUser = new User();
        notFriendsWithLoggedUser.addFriend(new User())
        const trips = tripService.getTripsByUser(notFriendsWithLoggedUser, loggedUser)

        expect(trips).empty
    });

    it('returns all trips when users are friends', () => {
        let allTrips = [new Trip('corse'), new Trip('algarve')];
        let traveler = new User();
        const tripDao ={findTripsByUser: sinon.stub()}
        tripDao.findTripsByUser.withArgs(traveler).returns(allTrips)
        const tripService = new TripService(tripDao)

        let loggedUser = new User();

        traveler.addFriend(loggedUser)
        const trips = tripService.getTripsByUser(traveler, loggedUser)

        expect(trips).eql(allTrips)
    });




});

