"use strict";

function getLoggedUser() {
    throw new Error("UserSession.getLoggedUser() should not be called in an unit test");
}
module.exports = {getLoggedUser};
