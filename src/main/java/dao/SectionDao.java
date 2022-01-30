package dao;

import dao.generic.AbstractJpaDao;
import entities.Board;

import java.util.List;

public class SectionDao extends AbstractJpaDao<Long, Board> {

    public SectionDao() {
        super();
    }

    @Override
    public void setClazz(Class<Board> clazzToSet) {
        super.setClazz(clazzToSet);
    }

    @Override
    public Board findOne(Long id) {
        return super.findOne(id);
    }

    @Override
    public List<Board> findAll() {
        return super.findAll();
    }

    @Override
    public void save(Board entity) {
        super.save(entity);
    }

    @Override
    public Board update(Board entity) {
        return super.update(entity);
    }

    @Override
    public void delete(Board entity) {
        super.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        super.deleteById(entityId);
    }
}
