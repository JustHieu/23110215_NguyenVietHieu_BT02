package vn.hieunguyen.daoimplement;

import java.sql.*;
import vn.hieunguyen.configs.DBConnectSQL;
import vn.hieunguyen.dao.ResetTokenDao;

public class ResetTokenDaoImpl implements ResetTokenDao {
    @Override
    public void save(int userId, String token, Timestamp exp) {
        String sql = "INSERT INTO dbo.PasswordResetToken(user_id, token, expires_at) VALUES(?,?,?)";
        try (Connection c = new DBConnectSQL().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, token);
            ps.setTimestamp(3, exp);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public Integer findUserIdByToken(String token) {
        String sql = "SELECT user_id FROM dbo.PasswordResetToken WHERE token = ?";
        try (Connection c = new DBConnectSQL().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public Timestamp findExpiryByToken(String token) {
        String sql = "SELECT expires_at FROM dbo.PasswordResetToken WHERE token = ?";
        try (Connection c = new DBConnectSQL().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, token);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getTimestamp(1);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public void delete(String token) {
        String sql = "DELETE FROM dbo.PasswordResetToken WHERE token = ?";
        try (Connection c = new DBConnectSQL().getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, token);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
