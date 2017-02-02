"use strict";

module.exports = class User {

    constructor() {
        this.friends = [];
    }

    getFriends() {
        return this.friends;
    }

    addFriend(newFriend) {
        this.friends.push(newFriend);
    }

}