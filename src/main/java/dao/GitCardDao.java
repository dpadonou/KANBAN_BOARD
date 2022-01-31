package dao;

import dao.generic.AbstractJpaDao;
import entities.GitCard;

import java.util.List;

public class GitCardDao extends AbstractJpaDao<Long, GitCard> {

    public GitCardDao() {
        super();
    }

    @Override
    public void setClass(Class<GitCard> gitCardClass) {
        super.setClass(gitCardClass);
    }

    @Override
    public GitCard findOne(Long id) {
        return super.findOne(id);
    }

    @Override
    public List<GitCard> findAll() {
        return super.findAll();
    }

    @Override
    public void save(GitCard entity) {
        super.save(entity);
    }

    @Override
    public GitCard update(GitCard entity) {
        return super.update(entity);
    }

    @Override
    public void delete(GitCard entity) {
        super.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        super.deleteById(entityId);
    }
}
