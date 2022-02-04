package web;

import dto.add.AddCardDto;
import dto.batch.BatchCardDto;
import dto.list.CardDto;
import dto.mapper.CardMapper;
import entities.Card;
import service.CardService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/card")
@Produces({"application/json", "application/xml"})
public class CardRessource {

    CardService service = new CardService();
    CardMapper mapper = new CardMapper();

    @GET
    @Path("/{cardId}")
    public CardDto findOne(@PathParam("cardId") Long cardId) {
        Card card = service.findOne(cardId);
        return mapper.toDto(card);
    }

    @GET
    public List<CardDto> findAll() {
        List<Card> cards = service.findAll();
        return cards.stream().map(card -> mapper.toDto(card)).collect(Collectors.toList());
    }

    @POST
    @Consumes("application/json")
    public Response save(AddCardDto cardDto) {
        service.save(mapper.toCard(cardDto));

        return Response.ok().entity("Le nouvel utilisateur a été ajouté avec succès.").build();
    }

    @POST
    @Path("/many")
    @Consumes("application/json")
    public Response save(BatchCardDto cardList) {
        cardList.getCardList().forEach(addCardDto -> service.save(mapper.toCard(addCardDto)));
        return Response.ok().entity(cardList.getCardList().size() + " nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

    @PUT
    @Path("/{cardId}")
    @Consumes("application/json")
    //TODO: Récupérer un id dans le path et les nouvelles valeurs dans le body
    public CardDto update(@PathParam("cardId") Long id, AddCardDto cardDto) {
        Card old = service.findOne(id);
        if (old != null) {
            Card newCard = mapper.toCard(cardDto);
            old.setLibelle(newCard.getLibelle());
            old.setCreatedDate(newCard.getCreatedDate());
            old.setDeadLine(newCard.getDeadLine());
            old.setAllocatedTime(newCard.getAllocatedTime());
            old.setLieu(newCard.getLieu());
            old.setUrl(newCard.getUrl());
            old.setNote(newCard.getNote());
            service.update(old);
        } else throw new AssertionError("Aucune fiche portant ce identifiant n'a été retrouvé.");
        return mapper.toDto(old);
    }

    @DELETE
    @Path("/{cardId}")
    public Response deleteById(@PathParam("cardId") Long cardId) {
        service.deleteById(cardId);
        return Response.ok().entity(" nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

}
