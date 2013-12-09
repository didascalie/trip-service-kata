package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.DependendClassCallDuringUnitTestException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripDAOTest {
    
    @Test(expected=DependendClassCallDuringUnitTestException.class) public void 
    calls_the_real_thing() throws Exception {
         new TripDAO().findTripsBy(new User());
    }

}
