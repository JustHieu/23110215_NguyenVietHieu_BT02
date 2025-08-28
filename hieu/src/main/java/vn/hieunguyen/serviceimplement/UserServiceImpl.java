package vn.hieunguyen.serviceimplement;

import vn.hieunguyen.dao.UserDao;
import vn.hieunguyen.daoimplement.*;
import vn.hieunguyen.models.User;
import vn.hieunguyen.services.UserService;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = this.get(username);          
        if (user != null && password.equals(user.getPassWord())) {
        	System.out.println("Try login: u=" + username + ", dbUser=" + (user!=null ? user.getUserName() : "null"));
        	if (user != null) {
        	    System.out.println("DB password = [" + user.getPassWord() + "]");
        	}

            return user;
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userDao.findByUserName(username); 
    }

    @Override
    public boolean register(String email, String password, String username, String fullname, String phone) {
        username = username == null ? null : username.trim();
        email    = email == null ? null : email.trim().toLowerCase();

        if (userDao.checkExistUsername(username) || userDao.checkExistEmail(email)) {
            return false;
        }

        java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        User u = new User();
        u.setEmail(email);
        u.setPassWord(password); // hoặc hash như gợi ý BCrypt

        u.setUserName(username);
        u.setFullName(fullname);
        u.setAvatar(null);
        u.setRoleid(5);
        u.setPhone(phone);
        u.setCreatedDate(date);

        userDao.insert(u); // nhớ bắt duplicate key trong DAO
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }
}
