package dao;

import dao.generic.AbstractJpaDao;
import entities.User;

import java.util.List;

public class UserDao extends AbstractJpaDao<Long, User> {

    public UserDao() {
        super();
    }

    @Override
    public void setClass(Class<User> userClass) {
        super.setClass(userClass);
    }

    @Override
    public User findOne(Long id) {
        return super.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return super.findAll();
    }

    public List<User> findAllByCardId(Long cardId) {
        String query = "SELECT u FROM User AS u JOIN u.taches AS c WHERE c.id = " + cardId;
        return entityManager.createQuery(query, User.class).getResultList();
    }
    public User loginUser(String email,String password){
        String query = "select u from User u where u.email = '" +email+"' and u.password='" +password+"'";
        return  entityManager.createQuery(query,User.class).getResultStream().findFirst().orElse(new User(0,"","","",""));

    }
    @Override
    public void save(User entity) {
        super.save(entity);
    }

    @Override
    public User update(User entity) {
        return super.update(entity);
    }

    @Override
    public void delete(User entity) {
        super.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        super.deleteById(entityId);
    }
}
