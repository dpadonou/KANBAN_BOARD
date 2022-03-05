package service;

import dao.BoardDao;
import entities.Board;

import java.util.List;

public class BoardService {

    private final BoardDao boardDao = new BoardDao();

    public BoardService() {
        boardDao.setClass(Board.class);
    }

    public Board findOne(Long id) {
        return boardDao.findOne(id);
    }

    public List<Board> findAllByUserId(Long userId) {
        return boardDao.findAllByUserId(userId);
    }

    public List<Board> findAll() {
        return boardDao.findAll();
    }

    public void save(Board entity) {
        boardDao.save(entity);
    }

    public Board update(Board entity) {
        return boardDao.update(entity);
    }

    public void delete(Board entity) {
        boardDao.delete(entity);
    }

    public void deleteById(Long entityId) {
        boardDao.deleteById(entityId);
    }

}
