package service;

import dao.SectionDao;
import entities.Section;

import java.util.List;

public class SectionService {
    private final SectionDao sectionDao = new SectionDao();

    public SectionService() {
        sectionDao.setClass(Section.class);
    }

    public Section findOne(Long id) {
        return sectionDao.findOne(id);
    }

    public List<Section> findAll() {
        return sectionDao.findAll();
    }

    public void save(Section entity) {
        sectionDao.save(entity);
    }

    public Section update(Section entity) {
        return sectionDao.update(entity);
    }

    public void delete(Section entity) {
        sectionDao.delete(entity);
    }

    public void deleteById(Long entityId) {
        sectionDao.deleteById(entityId);
    }
}
