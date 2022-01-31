package service;

import dao.CardDao;
import entities.Card;

import java.util.List;

public class CardService {
    private final CardDao cardDao = new CardDao();

    public CardService() {
        cardDao.setClass(Card.class);
    }

    public Card findOne(Long id) {
        return cardDao.findOne(id);
    }

    public List<Card> findAll() {
        return cardDao.findAll();
    }

    public void save(Card entity) {
        cardDao.save(entity);
    }

    public Card update(Card entity) {
        return cardDao.update(entity);
    }

    public void delete(Card entity) {
        cardDao.delete(entity);
    }

    public void deleteById(Long entityId) {
        cardDao.deleteById(entityId);
    }
}
