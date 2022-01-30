package jpa;

import entities.*;
import utils.TableList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

public class JpaTest {

    public static void main(String[] args) {

        EntityManager manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            fillDatabase(manager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        getDatabaseContent(manager);
        
        manager.close();
        EntityManagerHelper.closeEntityManagerFactory();
    }

    private static void getDatabaseContent(EntityManager manager) {
        listBoard(manager);
        listSection(manager);
        listCarte(manager);
        listGitCarte(manager);
        listUser(manager);

        getSomeCard(manager);
    }

    private static void fillDatabase(EntityManager manager){
        Card c1 = createCarte("Ajouter un bouton submit", LocalDateTime.now().minusHours(12), LocalDateTime.now().plusDays(2), "Istic Beaulieu", "http://localhost:5432/carte/0", "Description", manager);
        Card c2 = createCarte("Corriger le code couleur de la fenêtre de lancement", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(1).plusHours(3), "Istic Beaulieu", "http://localhost:5432/carte/1", "Description", manager);

        GitCard c3 = createGitCarte("Gérer le responsive sur le landing page", LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(5).plusHours(2), "Istic Beaulieu", "http://localhost:5432/carte/2", "Description", "https://gitlab.istic.univ-rennes1.fr/adjedjemel/stardp/-/issues", "baae61804e65cc73a7201a7252750c76066a30", manager);
        GitCard c4 = createGitCarte("Applique le patter Observer aux gestionnaire de fichiers", LocalDateTime.now().minusHours(5), LocalDateTime.now().plusDays(1).plusHours(2), "Istic Beaulieu", "http://localhost:5432/carte/3", "Description", "https://gitlab.istic.univ-rennes1.fr/adjedjemel/stardp/-/issues", "1f7a7a472abf3dd9643fd615f6da379c4acb3e3a ", manager);
        GitCard c5 = createGitCarte("Ajouter un captcha au formulaire de connexion", LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(2).minusHours(6), "Istic Beaulieu", "http://localhost:5432/carte/4", "Description", "https://gitlab.istic.univ-rennes1.fr/adjedjemel/stardp/-/issues", "83baae61804e65cc73a7201a7252750c76066a30 ", manager);
        GitCard c6 = createGitCarte("Gérer la réinitialisation des mots de passe oubliés", LocalDateTime.now().minusDays(4), LocalDateTime.now().minusDays(3), "Istic Beaulieu", "http://localhost:5432/carte/5", "Description", "https://gitlab.istic.univ-rennes1.fr/adjedjemel/stardp/-/issues", "7a7a472abf3dd9643fd615f6da379c4acb3e3a", manager);

        Section paused = createSection("PAUSED", manager);
        paused.addCarte(c2);
        paused.addCarte(c3);
        Section pending = createSection("PENDING", manager);
        pending.addCarte(c1);
        pending.addCarte(c4);
        Section done = createSection("DONE", manager);
        done.addCarte(c5);
        done.addCarte(c6);

        manager.merge(paused);
        manager.merge(pending);
        manager.merge(done);

        Board board1 = createBoard("TABLEAU 1", manager);
        board1.addSection(paused);
        board1.addSection(pending);
        board1.addSection(done);
        Board board2 = createBoard("TABLEAU 2", manager);
        board2.addSection(paused);
        board2.addSection(pending);
        board2.addSection(done);

        manager.merge(board1);
        manager.merge(board2);

        User john = createUser("John", "DOE", manager);
        john.addOwned(c1);
        john.addOwned(c2);
        john.addOwned(c3);
        john.addOwned(c4);
        john.addOwned(c5);
        john.addOwned(c6);
        User blake = createUser("Blake", "JOHN", manager);
        blake.addTache(c1);
        blake.addTache(c2);
        blake.addTache(c3);
        blake.addTache(c4);
        User luke = createUser("Luke", "LUCKY", manager);
        luke.addTache(c5);
        luke.addTache(c6);

        manager.merge(john);
        manager.merge(blake);
        manager.merge(luke);

        manager.merge(c1);
        manager.merge(c2);
        manager.merge(c3);
        manager.merge(c4);
        manager.merge(c5);
        manager.merge(c6);
    }

    public static User createUser(String firstName, String lastName, EntityManager manager) {
        User user = new User(lastName, firstName);
        manager.persist(user);
        return user;
    }

    private static Board createBoard(String title, EntityManager manager){
        Board board = new Board(title);
        manager.persist(board);
        return board;
    }

    private static Section createSection(String name, EntityManager manager){
        Section section = new Section(name);
        manager.persist(section);
        return section;
    }

    private static Card createCarte(String libelle, LocalDateTime createdDate, LocalDateTime deadLine, String lieu, String url, String note, EntityManager manager){
        Card card = new Card(libelle, createdDate, deadLine, lieu, url, note);
        manager.persist(card);
        return card;
    }

    private static GitCard createGitCarte(String libelle, LocalDateTime createdDate, LocalDateTime deadLine, String lieu, String url, String note, String urlIssue, String gitHash, EntityManager manager){
        GitCard card = new GitCard(libelle, createdDate, deadLine, lieu, url, note, urlIssue, gitHash);
        manager.persist(card);
        return card;
    }

    private static void listUser(EntityManager manager) {
        List<User> result = manager.createQuery("Select u From User u", User.class).getResultList();
        System.out.println("Nombre d'utilisateurs:" + result.size());

        TableList tl = new TableList(3, "ID", "FIRST_NAME", "LAST_NAME").withUnicode(true);
        for (User user : result) {
            tl.addRow(String.valueOf(user.getId()), user.getFirstName(), user.getLastName());
        }
        tl.print();
    }

    private static void listBoard(EntityManager manager) {
        List<Board> result = manager.createQuery("Select b From Board b", Board.class).getResultList();
        System.out.println("Nombre de tableaux:" + result.size());

        TableList tl = new TableList(2, "ID", "TITLE").withUnicode(true);
        for (Board board : result) {
            tl.addRow(String.valueOf(board.getId()), board.getTitle());
        }
        tl.print();
    }

    private static void listSection(EntityManager manager) {
        List<Section> result = manager.createQuery("Select s From Section s", Section.class).getResultList();
        System.out.println("Nombre de sections:" + result.size());

        TableList tl = new TableList(3, "ID", "NAME", "TABLEAU").withUnicode(true);
        for (Section section : result) {
            for (Board board : section.getBoards()){
                tl.addRow(String.valueOf(section.getId()), section.getName(), board.getTitle());
            }
        }
        tl.print();
    }

    private static void listCarte(EntityManager manager) {
        List<Card> result = manager.createQuery("Select c From Card c", Card.class).getResultList();
        System.out.println("Nombre de cartes:" + result.size());

        TableList tl = new TableList(11, "ID", "LIBELLE", "CREATED_DATE", "DEAD_LINE", "ALLOCATED_TIME", "LIEU", "URL", "NOTE", "PERSONNE_EN_CHARGE", "CREATOR", "SECTION_COURANTE").withUnicode(true);
        for (Card card : result) {
            for (User user : card.getInCharge()){
                tl.addRow(String.valueOf(card.getId()), card.getLibelle(), card.getCreatedDate().toString(), card.getDeadLine().toString(), String.valueOf(card.getAllocatedTime()), card.getLieu(), card.getUrl(), card.getNote(), (user.getFirstName() + " " + user.getLastName()), (card.getCreator().getFirstName() + " " + card.getCreator().getLastName()), card.getSection().getName());
            }
        }
        tl.print();
    }

    private static void listGitCarte(EntityManager manager) {
        List<GitCard> result = manager.createQuery("Select gc From GitCard gc", GitCard.class).getResultList();
        System.out.println("Nombre de cartes:" + result.size());
        
        TableList tl = new TableList(13, "ID", "LIBELLE", "CREATED_DATE", "DEAD_LINE", "ALLOCATED_TIME", "LIEU", "URL", "NOTE", "URL_ISSUE", "GIT_HASH", "PERSONNE_EN_CHARGE", "CREATOR", "SECTION_COURANTE").withUnicode(true);
        for (GitCard card : result) {
            for (User user : card.getInCharge()){
                tl.addRow(String.valueOf(card.getId()), card.getLibelle(), card.getCreatedDate().toString(), card.getDeadLine().toString(), String.valueOf(card.getAllocatedTime()), card.getLieu(), card.getUrl(), card.getNote(), card.getUrlIssue(), card.getGitHash(), (user.getFirstName() + " " + user.getLastName()), (card.getCreator().getFirstName() + " " + card.getCreator().getLastName()), card.getSection().getName());
            }
        }
        tl.print();
    }

    private static void getSomeCard(EntityManager manager) {
        List<GitCard> result = manager.createQuery("Select c From GitCard c WHERE c.allocatedTime > 24", GitCard.class).getResultList();
        System.out.println("Nombre de cartes:" + result.size());

        TableList tl = new TableList(11, "ID", "LIBELLE", "CREATED_DATE", "DEAD_LINE", "ALLOCATED_TIME", "LIEU", "URL", "NOTE", "PERSONNE_EN_CHARGE", "CREATOR", "SECTION_COURANTE").withUnicode(true);
        for (GitCard card : result) {
            for (User user : card.getInCharge()){
                tl.addRow(String.valueOf(card.getId()), card.getLibelle(), card.getCreatedDate().toString(), card.getDeadLine().toString(), String.valueOf(card.getAllocatedTime()), card.getLieu(), card.getUrl(), card.getNote(), (user.getFirstName() + " " + user.getLastName()), (card.getCreator().getFirstName() + " " + card.getCreator().getLastName()), card.getSection().getName());
            }
        }
        tl.print();
    }

}
