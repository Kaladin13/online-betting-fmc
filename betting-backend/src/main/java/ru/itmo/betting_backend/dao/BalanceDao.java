package ru.itmo.betting_backend.dao;

public interface BalanceDao {

    public void createBalanceTicket(String type, Long userId);
}
