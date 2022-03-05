package service;

import dao.UserDao;
import entities.User;

import java.util.List;

public class UserService {

    private final UserDao userDao = new UserDao();

    public UserService() {
        userDao.setClass(User.class);
    }

    public User findOne(Long id) {
        User u = userDao.findOne(id);
        return userDao.findOne(id);
    }

    public List<User> findAllByCardId(Long CardId) {
        return userDao.findAllByCardId(CardId);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void save(User entity) {
        userDao.save(entity);
    }

    public User update(User entity) {
        return userDao.update(entity);
    }

    public void delete(User entity) {
        userDao.delete(entity);
    }

    public void deleteById(Long entityId) {
        userDao.deleteById(entityId);
    }
}
