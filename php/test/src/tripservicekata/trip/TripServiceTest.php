<?php

class TripServiceTest extends PHPUnit_Framework_TestCase {

    /**
     * @var TripService
     */
    private $tripService;

    protected function setUp() {
        $this->tripService = $this->getMock('TripService', array('getLoggedUser'));
    }

    /**
     * @expectedException UserNotLoggedInException
     * @test
     * @covers TripService::getTripsByUser
     */
    public function it_throws_an_exception_if_the_user_is_a_guest() {
        $this->tripService->getTripsByUser(new User());
    }
    
    /**
     * @test
     */
    public function it_doesnt_return_trips_if_the_given_user_has_no_friends() {
        $this->tripService->expects($this->any())
                ->method('getLoggedUser')
                ->will($this->returnValue(new User()));
        $tripsByUser = $this->tripService->getTripsByUser(new User());
        $noTrips = array();
        $this->assertEquals($noTrips, $tripsByUser);
    }
}

class TripServiceWODependencies extends TripService {

    protected function getLoggedUser() {
        return null;
    }
}
