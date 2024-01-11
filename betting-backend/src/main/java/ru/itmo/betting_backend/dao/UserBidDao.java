package ru.itmo.betting_backend.dao;

import ru.itmo.betting_backend.model.UserBid;

public interface UserBidDao {

    void persist(UserBid userBid);
}
