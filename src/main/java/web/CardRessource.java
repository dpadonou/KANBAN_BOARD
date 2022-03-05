package web;

import dto.add.AddCardDto;
import dto.batch.BatchCardDto;
import dto.list.CardDto;
import dto.mapper.CardMapper;
import dto.relations.ManyToOneDto;
import dto.relations.OneToOneDto;
import entities.Card;
import entities.User;
import service.CardService;
import service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/card")
@Produces({"application/json", "application/xml"})
public class CardRessource {

    CardService cardService = new CardService();
    UserService userService = new UserService();
    CardMapper mapper = new CardMapper();

    @GET
    @Path("/{cardId}")
    public CardDto findOne(@PathParam("cardId") Long cardId) {
        Card card = cardService.findOne(cardId);
        return mapper.toDto(card);
    }

    @GET
    public List<CardDto> findAll() {
        List<Card> cards = cardService.findAll();
        return cards.stream().map(card -> mapper.toDto(card)).collect(Collectors.toList());
    }

    @POST
    @Consumes("application/json")
    public Response save(AddCardDto cardDto) {
        cardService.save(mapper.toCard(cardDto));

        return Response.ok().entity("Le nouvel utilisateur a été ajouté avec succès.").build();
    }

    @POST
    @Path("/many")
    @Consumes("application/json")
    public Response save(BatchCardDto cardList) {
        cardList.getCardList().forEach(addCardDto -> cardService.save(mapper.toCard(addCardDto)));
        return Response.ok().entity(cardList.getCardList().size() + " nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

    @PUT
    @Path("/{cardId}")
    @Consumes("application/json")
    public CardDto update(@PathParam("cardId") Long id, AddCardDto cardDto) {
        Card old = cardService.findOne(id);
        if (old != null) {
            Card newCard = mapper.toCard(cardDto);
            old.setLibelle(newCard.getLibelle());
            old.setCreatedDate(newCard.getCreatedDate());
            old.setDeadLine(newCard.getDeadLine());
            old.setAllocatedTime(newCard.getAllocatedTime());
            old.setLieu(newCard.getLieu());
            old.setUrl(newCard.getUrl());
            old.setNote(newCard.getNote());
            cardService.update(old);
        } else throw new AssertionError("Aucune fiche portant ce identifiant n'a été retrouvé.");
        return mapper.toDto(old);
    }

    @PUT
    @Path("assignTo/many")
    @Consumes("application/json")
    public CardDto assignManyUsers(ManyToOneDto dto) {
        Card emitter = cardService.findOne(dto.getMainId());
        List<User> users = new ArrayList<>();

        for (Long id : dto.getForeignIds()) {
            User u = userService.findOne(id);
            if (u != null) {
                users.add(u);
            } else
                throw new AssertionError("Une erreur est survenu lors de l'assignation de l'utilisateur d'identifiant : " + id + ".");
        }

        if (emitter != null) {
            for (User user : users) {
                emitter.assignToSomeOne(user);
                cardService.update(emitter);
                userService.update(user);
            }
        } else throw new AssertionError("Aucune card portant ce identifiant n'a été retrouvé.");

        return mapper.toDto(emitter);
    }

    @PUT
    @Path("assignTo")
    @Consumes("application/json")
    public CardDto addTask(OneToOneDto dto) {
        Card emitter = cardService.findOne(dto.getMainId());
        User user = userService.findOne(dto.getForeignId());

        if (emitter != null && user != null) {
            emitter.assignToSomeOne(user);
            cardService.update(emitter);
            userService.update(user);
        } else if (emitter == null) {
            throw new AssertionError("Aucune card portant ce identifiant n'a été retrouvé.");
        } else throw new AssertionError("Veuillez entrer un identifiant utilisateur valide.");
        return mapper.toDto(emitter);
    }

    @DELETE
    @Path("/{cardId}")
    public Response deleteById(@PathParam("cardId") Long cardId) {
        cardService.deleteById(cardId);
        return Response.ok().entity(" nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

}
