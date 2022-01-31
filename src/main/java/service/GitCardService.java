package service;

import dao.GitCardDao;
import entities.GitCard;

import java.util.List;

public class GitCardService {
    private final GitCardDao gitCardDao = new GitCardDao();

    public GitCardService() {
        gitCardDao.setClass(GitCard.class);
    }

    public GitCard findOne(Long id) {
        return gitCardDao.findOne(id);
    }

    public List<GitCard> findAll() {
        return gitCardDao.findAll();
    }

    public void save(GitCard entity) {
        gitCardDao.save(entity);
    }

    public GitCard update(GitCard entity) {
        return gitCardDao.update(entity);
    }

    public void delete(GitCard entity) {
        gitCardDao.delete(entity);
    }

    public void deleteById(Long entityId) {
        gitCardDao.deleteById(entityId);
    }
}
