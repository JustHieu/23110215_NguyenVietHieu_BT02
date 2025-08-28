package vn.hieunguyen.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.hieunguyen.configs.DBConnectSQL;
import vn.hieunguyen.dao.UserDao;
import vn.hieunguyen.models.User;

public class UserDaoImpl implements UserDao {

    @Override
    public void insert(User user) {
        final String sql =
            "INSERT INTO [dbo].[User] (email, username, fullname, [password], avatar, roleid, phone, createddate) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = new DBConnectSQL().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassWord());
            ps.setString(5, user.getAvatar());
            ps.setInt(6, user.getRoleid());
            ps.setString(7, user.getPhone());
            ps.setDate(8, user.getCreatedDate()); // createddate nên là DATE/DATETIME
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByUserName(String username) {
        final String sql =
            "SELECT id, email, username, fullname, [password], avatar, roleid, phone, createddate " +
            "FROM [dbo].[User] WHERE username = ?";
        try (Connection conn = new DBConnectSQL().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User get(String username) { return findByUserName(username); }

    @Override
    public User findUserByName(String fullName) {
        final String sql =
            "SELECT id, email, username, fullname, [password], avatar, roleid, phone, createddate " +
            "FROM [dbo].[User] WHERE fullname = ?";
        try (Connection conn = new DBConnectSQL().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, fullName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkExistEmail(String email) {
        final String sql = "SELECT 1 FROM [dbo].[User] WHERE email = ?";
        try (Connection conn = new DBConnectSQL().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (Exception e) { return false; }
    }

    @Override
    public boolean checkExistUsername(String username) {
        final String sql = "SELECT 1 FROM [dbo].[User] WHERE username = ?";
        try (Connection conn = new DBConnectSQL().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (Exception e) { return false; }
    }

    @Override
    public boolean checkExistPhone(String phone) {
        final String sql = "SELECT 1 FROM [dbo].[User] WHERE phone = ?";
        try (Connection conn = new DBConnectSQL().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) { return rs.next(); }
        } catch (Exception e) { return false; }
    }

    private User mapRow(ResultSet rs) throws Exception {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setEmail(rs.getString("email"));
        u.setUserName(rs.getString("username"));
        u.setFullName(rs.getString("fullname"));
        u.setPassWord(rs.getString("password"));     // đã chọn [password] trong SELECT
        u.setAvatar(rs.getString("avatar"));
        u.setRoleid(rs.getInt("roleid"));
        u.setPhone(rs.getString("phone"));
        u.setCreatedDate(rs.getDate("createddate"));
        return u;
    }
}
