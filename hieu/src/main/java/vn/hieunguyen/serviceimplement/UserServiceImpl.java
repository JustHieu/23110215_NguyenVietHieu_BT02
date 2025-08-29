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
    public boolean register(String username, String password, String email, String fullname, String phone) {
        username = username == null ? "" : username.trim();
        email    = email == null ? "" : email.trim().toLowerCase();

        if (username.isEmpty() || email.isEmpty() || password == null || password.isEmpty())
            return false;

        if (userDao.checkExistUsername(username) || userDao.checkExistEmail(email))
            return false;

        User u = new User();
        u.setUserName(username);
        u.setPassWord(password);                // TODO: hash
        u.setEmail(email);
        u.setFullName(fullname);
        u.setPhone(phone);
        u.setAvatar(null);
        u.setRoleid(2);                         
        u.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));

        userDao.insert(u);
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
