<?php

namespace Test\TripServiceKata\Trip;

use PHPUnit_Framework_TestCase;
use TripServiceKata\Trip\TripService;
use TripServiceKata\User\User;
use TripServiceKata\Trip\Trip;
use TripServiceKata\Exception\UserNotLoggedInException;

class TripServiceTest extends PHPUnit_Framework_TestCase {

    private $GUEST = null;
    /**
     * @var TripService
     */
    private $tripService;
    
    /** @var User */
    private $loggedUser, $anotherUser, $someUser;

    protected function setUp() {
        $this->tripService = $this->getMockBuilder("TripServiceKata\Trip\TripService")
                ->setMethods(array('getLoggedUser', 'findTripsFor'))
                ->getMock();
        $this->loggedUser = new User("Fred");
        $this->someUser = new User("Simone");
        $this->anotherUser = new User("Lucile");
    }

    /**
     * @expectedException TripServiceKata\Exception\UserNotLoggedInException
     * @test
     * @covers TripService::getTripsByUser
     */
    public function it_throws_an_exception_if_the_user_is_a_guest() {
        $this->tripService->method('getLoggedUser')
                ->will($this->returnValue($this->GUEST));

        $this->tripService->getTripsByUser($this->someUser);
    }

    
    /** @test */
    public function it_doesnt_return_trips_if_the_given_user_has_no_friends() {
        $this->tripService->method('getLoggedUser')
                ->will($this->returnValue($this->loggedUser));

        $tripsByUser = $this->tripService->getTripsByUser($this->someUser);

        $noTrips = array();
        $this->assertEquals($noTrips, $tripsByUser);
    }
    
    
    /** @test */
    public function it_doesnt_return_trips_if_loggedUser_and_given_user_are_not_friends() {
        $this->tripService->method('getLoggedUser')
                ->will($this->returnValue($this->loggedUser));

        $this->someUser->addFriend($this->anotherUser);
        $tripsByUser = $this->tripService->getTripsByUser($this->someUser);
        $noTrips = array();
        $this->assertEquals($noTrips, $tripsByUser);
    }
    
    /** @test */
    public function viewer_sees_trips_if_the_givenUser_is_friends_with_him() {
        $trips = array(new Trip(), new Trip());
        $this->tripService->expects($this->any())
                ->method('findTripsFor')
                ->with($this->equalTo($this->someUser))
                ->will($this->returnValue($trips));
        $this->tripService->method('getLoggedUser')
                ->will($this->returnValue($this->loggedUser));
        
        $this->someUser->addFriend($this->loggedUser);
        $tripsByUser = $this->tripService->getTripsByUser($this->someUser);
        $this->assertEquals($trips, $tripsByUser);
    }
    
   
}

