package vn.hieunguyen.daoimplement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import vn.hieunguyen.configs.DBConnectSQL;
import vn.hieunguyen.dao.UserDao;
import vn.hieunguyen.models.User;

public class UserDaoImpl implements UserDao {

	@Override
	public void insert(User u) {
	    final String getNextIdSql = "SELECT ISNULL(MAX(id), 0) + 1 FROM [dbo].[User]";
	    final String insertSql =
	        "INSERT INTO [dbo].[User] " +
	        "(id, username, [password], email, fullname, phone, avatar, roleid, createdDate) " +
	        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (Connection con = new DBConnectSQL().getConnection()) {
	        int nextId = 1;
	        try (PreparedStatement ps = con.prepareStatement(getNextIdSql);
	             ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                nextId = rs.getInt(1);
	            }
	        }

	        try (PreparedStatement ps = con.prepareStatement(insertSql)) {
	            ps.setInt(1, nextId);
	            ps.setString(2, u.getUserName());
	            ps.setString(3, u.getPassWord());
	            ps.setString(4, u.getEmail() == null ? null : u.getEmail().toLowerCase());
	            ps.setNString(5, u.getFullName());
	            ps.setString(6, u.getPhone());
	            ps.setString(7, u.getAvatar());
	            ps.setInt(8, u.getRoleid());
	            ps.setDate(9, u.getCreatedDate());

	            ps.executeUpdate();
	            u.setId(nextId); // gán lại id vào object
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Insert user failed: " + e.getMessage(), e);
	    }
	}




	@Override
	public User findByUserName(String username) {
		final String sql = "SELECT id, email, username, fullname, [password], avatar, roleid, phone, createddate "
				+ "FROM [dbo].[User] WHERE username = ?";
		try (Connection conn = new DBConnectSQL().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return mapRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User get(String username) {
		return findByUserName(username);
	}

	@Override
	public User findUserByName(String fullName) {
		final String sql = "SELECT id, email, username, fullname, [password], avatar, roleid, phone, createddate "
				+ "FROM [dbo].[User] WHERE fullname = ?";
		try (Connection conn = new DBConnectSQL().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, fullName);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return mapRow(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkExistEmail(String email) {
		final String sql = "SELECT 1 FROM [dbo].[User] WHERE email = ?";
		try (Connection conn = new DBConnectSQL().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean checkExistUsername(String username) {
		final String sql = "SELECT 1 FROM [dbo].[User] WHERE username = ?";
		try (Connection conn = new DBConnectSQL().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean checkExistPhone(String phone) {
		final String sql = "SELECT 1 FROM [dbo].[User] WHERE phone = ?";
		try (Connection conn = new DBConnectSQL().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, phone);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next();
			}
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String findUsernameByEmail(String email) {
		final String sql = "SELECT username FROM [dbo].[User] WHERE email = ?";
		try (Connection conn = new DBConnectSQL().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getString(1); // hoặc rs.getString("username")
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // không tìm thấy
	}

	@Override
	public Integer findUserIdByEmail(String email) {
		final String sql = "SELECT id FROM dbo.[User] WHERE email = ?";
		try (Connection conn = new DBConnectSQL().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updatePasswordById(int userId, String newPwd) {
		final String sql = "UPDATE dbo.[User] SET [password] = ? WHERE id = ?";
		try (Connection conn = new DBConnectSQL().getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, newPwd);
			ps.setInt(2, userId);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private User mapRow(ResultSet rs) throws Exception {
		User u = new User();
		u.setId(rs.getInt("id"));
		u.setEmail(rs.getString("email"));
		u.setUserName(rs.getString("username"));
		u.setFullName(rs.getString("fullname"));
		u.setPassWord(rs.getString("password"));
		u.setAvatar(rs.getString("avatar"));
		u.setRoleid(rs.getInt("roleid"));
		u.setPhone(rs.getString("phone"));
		u.setCreatedDate(rs.getDate("createdDate"));
		return u;
	}

}
