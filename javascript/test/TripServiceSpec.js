"use strict";

let assert = require('assert');
let sinon = require('sinon');

let TripService = require('../src/TripService');
let User = require('../src/User')

describe('TripService', () => {
    let getLoggedUserFn = sinon.stub(TripService.prototype, 'getLoggedUser');
    let findTripsByUserFn = sinon.stub(TripService.prototype, 'findTripsByUser');

    let viewer = new User()
    let someOtherUser = new User()
    let noTrips = []

    it('should... ', () => {
        let tripService = new TripService();
        getLoggedUserFn.returns(null)
        assert.throws(() => tripService.getTripsByUser());
    });

    it('not friends', function() {
        let tripService = new TripService();
        getLoggedUserFn.returns(viewer);

        let traveler = new User()
        let trips = tripService.getTripsByUser(traveler);

        assert.deepEqual(trips,  noTrips)
    })

    it('not friends but friends exist', function() {
        let tripService = new TripService();
        getLoggedUserFn.returns(viewer);

        let traveler = new User()

        traveler.addFriend(someOtherUser)
        let trips = tripService.getTripsByUser(traveler);

        assert.deepEqual(trips,  noTrips)
    })


    it('friends can see trips', function() {
        let tripService = new TripService();
        getLoggedUserFn.returns(viewer);
        var expectedTrips = ['london', 'berlin'];
        findTripsByUserFn.returns(expectedTrips)

        let traveler = new User()

        traveler.addFriend(viewer)
        let trips = tripService.getTripsByUser(traveler);

        assert.equal(trips,  expectedTrips)
    })


});
