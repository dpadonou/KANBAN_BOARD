package dao;

import dao.generic.AbstractJpaDao;
import entities.Section;

import java.util.List;

public class SectionDao extends AbstractJpaDao<Long, Section> {

    public SectionDao() {
        super();
    }

    @Override
    public void setClass(Class<Section> sectionClass) {
        super.setClass(sectionClass);
    }

    @Override
    public Section findOne(Long id) {
        return super.findOne(id);
    }

    @Override
    public List<Section> findAll() {
        return super.findAll();
    }

    @Override
    public void save(Section entity) {
        super.save(entity);
    }

    @Override
    public Section update(Section entity) {
        return super.update(entity);
    }

    @Override
    public void delete(Section entity) {
        super.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        super.deleteById(entityId);
    }
}
