package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.DependendClassCallDuringUnitTestException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;


public class TripDAOTest {
    @Test(expected=DependendClassCallDuringUnitTestException.class) public void 
    accesses_the_database() throws Exception {
         TripDAO dao = new TripDAO();
         dao.findTripsBy(new User());
    }

}
