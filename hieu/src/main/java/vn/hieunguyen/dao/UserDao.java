package vn.hieunguyen.dao;

import vn.hieunguyen.models.User;

public interface UserDao {
	User get(String username);

	User findByUserName(String username);

	User findUserByName(String fullName);

	String findUsernameByEmail(String email);

	Integer findUserIdByEmail(String email);

	void updatePasswordById(int userId, String newPassword);

	void insert(User user);

	boolean checkExistEmail(String email);

	boolean checkExistUsername(String username);

	boolean checkExistPhone(String phone);
}
