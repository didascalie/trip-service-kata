<?php

namespace Test\TripServiceKata\Trip;

use PHPUnit\Framework\TestCase;
use TripServiceKata\Exception\UserNotLoggedInException;
use TripServiceKata\Trip\Trip;
use TripServiceKata\Trip\TripService;
use TripServiceKata\User\User;

class TripServiceTest extends TestCase {

    /** @test
     * @expectedException TripServiceKata\Exception\UserNotLoggedInException
     */
    public function validatesTheUserIsNotGuest()
    {
        $tripService = new TripService();
        $trips = $tripService->_getTripsByUser(new User('Jenny'), null);
    }

    /** @test */
    public function showsNoTripsWhenUsersAreStrangers()
    {
        $tripService = new TripService();
        $traveler = new User('traveler');
        $currentUser = new User('currentUser');
        $trips = $tripService->_getTripsByUser($traveler, $currentUser);

        $this->assertEquals($this->noTrips(), $trips);
    }

    /** @test */
    public function showsTripsToFriends()
    {
        $crete = new Trip();
        $amsterdam = new Trip();
        $expectedTrips = [$amsterdam, $crete];
        // given
        $tripService = new TestableTripService($expectedTrips);

        $traveler = new User('traveler');
        $currentUser = new User('currentUser');
        $traveler->addFriend($currentUser);


        // when
        $trips = $tripService->_getTripsByUser($traveler, $currentUser);

        // then
        $this->assertEquals($expectedTrips, $trips);
    }

    private function noTrips()
    {
        return [];
    }
}
class TestableTripService extends TripService {
    private $tripsByTraveler;


    /**
     * TestableTripService constructor.
     */
    public function __construct($tripsByTraveler)
    {
        $this->tripsByTraveler = $tripsByTraveler;
    }

    protected function findTripsBy(User $user)
    {
        return $this->tripsByTraveler;
    }

}

