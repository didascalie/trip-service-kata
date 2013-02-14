<?php 
class TripService {

	public function getTripsByUser(User $user) {
                $tripList = array();
		$loggedUser = $this->getLoggedUser();
		$isFriend = false;
		if ($loggedUser != null) {
                    foreach ($user->getFriends() as $friend) {
                        if ($friend == $loggedUser) {
                            $isFriend = true;
                            break;
                        }

                    }
                    if ($isFriend) {
                        $tripList = $this->findTripsFor($user);
                    }
                    return $tripList;
		} else {
			throw new UserNotLoggedInException();
		}
	}
	
        protected function getLoggedUser() {
            return UserSession::getInstance()->getLoggedUser();
        }
        
        protected function findTripsFor(User $user) {
            return TripDAO::findTripsByUser($user);
        }
}
?>