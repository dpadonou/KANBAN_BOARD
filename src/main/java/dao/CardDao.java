package dao;

import dao.generic.AbstractJpaDao;
import entities.Card;

import java.util.List;

public class CardDao extends AbstractJpaDao<Long, Card> {

    public CardDao() {
        super();
    }

    @Override
    public void setClass(Class<Card> cardClass) {
        super.setClass(cardClass);
    }

    @Override
    public Card findOne(Long id) {
        return super.findOne(id);
    }

    @Override
    public List<Card> findAll() {
        return super.findAll();
    }

    @Override
    public void save(Card entity) {
        super.save(entity);
    }

    @Override
    public Card update(Card entity) {
        return super.update(entity);
    }

    @Override
    public void delete(Card entity) {
        super.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        super.deleteById(entityId);
    }
}
