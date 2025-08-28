package vn.hieunguyen.dao;

import vn.hieunguyen.models.User;

public interface UserDao {
	User get(String username);
    User findByUserName(String username);
	User findUserByName(String fullName);
	 void insert(User user);
	 boolean checkExistEmail(String email);
	 boolean checkExistUsername(String username);
	 boolean checkExistPhone(String phone);
}
