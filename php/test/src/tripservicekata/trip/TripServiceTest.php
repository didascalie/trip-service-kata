<?php

class TripServiceTest extends PHPUnit_Framework_TestCase {

    /**
     * @var TripService
     */
    private $tripService;

    protected function setUp() {
        $this->tripService = new TripServiceWODependencies();
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
    }
}

class TripServiceWODependencies extends TripService {

    protected function getLoggedUser() {
        return null;
    }
}
