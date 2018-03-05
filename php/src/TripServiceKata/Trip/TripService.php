<?php

namespace TripServiceKata\Trip;

use Mockery\Exception;
use TripServiceKata\User\User;
use TripServiceKata\User\UserSession;
use TripServiceKata\Exception\UserNotLoggedInException;

class TripService
{
    public function getTripsByUser(User $user) {
        $loggedUser = UserSession::getInstance()->getLoggedUser();
        return $this->_getTripsByUser($user, $loggedUser);
    }

    /**
     * @param User $user
     * @param $loggedUser
     * @return array|void
     * @throws UserNotLoggedInException
     * @throws \TripServiceKata\Exception\DependentClassCalledDuringUnitTestException
     */
    public function _getTripsByUser(User $user, $loggedUser)
    {
        $tripList = array();
        $isFriend = false;
        if ($loggedUser != null) {
            foreach ($user->getFriends() as $friend) {
                if ($friend == $loggedUser) {
                    $isFriend = true;
                    break;
                }
            }
            if ($isFriend) {
                $tripList = $this->findTripsBy($user);
            }
            return $tripList;
        } else {
            throw new UserNotLoggedInException();
        }
    }

    protected function findTripsBy(User $user)
    {
        return TripDAO::findTripsByUser($user);
    }

}
