package vn.hieunguyen.dao;

import java.sql.Timestamp;

public interface ResetTokenDao {
    void save(int userId, String token, Timestamp exp);
    Integer findUserIdByToken(String token);
    Timestamp findExpiryByToken(String token);
    void delete(String token);
}
