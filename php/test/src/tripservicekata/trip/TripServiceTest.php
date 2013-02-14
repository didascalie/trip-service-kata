<?php

class TripServiceTest extends PHPUnit_Framework_TestCase {

    /**
     * @var TripService
     */
    protected $object;

    protected function setUp() {
        $this->object = new TripService;
    }

    /**
     * @test
     * @covers TripService::getTripsByUser
     */
    public function it_throws_an_exception_if_the_user_is_a_guest() {
        
        $this->fail('This test has not been implemented yet.');
    }

}
