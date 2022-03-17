package web;

import dto.add.AddUserDto;
import dto.add.LoginUserDto;
import dto.batch.BatchUserDto;
import dto.list.UserDto;
import dto.list.UserSessionDto;
import dto.mapper.UserMapper;
import dto.relations.ManyToOneDto;
import dto.relations.OneToOneDto;
import entities.Card;
import entities.FeedBack;
import entities.User;
import service.CardService;
import service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/user")
@Produces({"application/json", "application/xml"})
public class UserRessource {

    UserService userService = new UserService();
    CardService cardService = new CardService();
    UserMapper mapper = new UserMapper();

    @GET
    @Path("/{userId}")
    public UserDto findOne(@PathParam("userId") Long userId) {
        User user = userService.findOne(userId);
        return mapper.toDto(user);
    }

    @POST
    @Path("/login")
    @Consumes("application/json")
    public UserDto loginUser(LoginUserDto userDto){
        User user = userService.loginUser(userDto.getEmail(),userDto.getPassword());
        return mapper.toDto(user);

    }

    @GET
    @Path("/works-on/{cardId}")
    public List<UserDto> findAllByCardId(@PathParam("cardId") Long cardId) {
        List<User> users = userService.findAllByCardId(cardId);
        return users.stream().map(user -> mapper.toDto(user)).collect(Collectors.toList());
    }

    @GET
    public List<UserDto> findAll() {
        List<User> users = userService.findAll();
        return users.stream().map(user -> mapper.toDto(user)).collect(Collectors.toList());
    }

    @POST
    @Consumes("application/json")
    public FeedBack save(AddUserDto userDTO) {
        userService.save(mapper.toUser(userDTO));
        //return Response.ok().entity("Le nouvel utilisateur a été ajouté avec succès.").build();
        return new FeedBack("Le nouvel utilisateur a été ajouté avec succès.");
    }

    @POST
    @Path("/many")
    @Consumes("application/json")
    public Response save(BatchUserDto userList) {
        userList.getUserList().forEach(addUserDto -> userService.save(mapper.toUser(addUserDto)));
        return Response.ok().entity(userList.getUserList().size() + " nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

    @PUT
    @Path("/{userId}")
    @Consumes("application/json")
    public UserDto update(@PathParam("userId") Long id, AddUserDto userDTO) {
        User old = userService.findOne(id);
        if (old != null) {
            User newUser = mapper.toUser(userDTO);
            old.setFirstName(newUser.getFirstName());
            old.setLastName(newUser.getLastName());
            userService.update(old);
        } else throw new AssertionError("Aucun utilisateur portant ce identifiant n'a été retrouvé.");
        return mapper.toDto(old);
    }

    @PUT
    @Path("createCard")
    @Consumes("application/json")
    public UserDto createCard(OneToOneDto dto) {
        User emitter = userService.findOne(dto.getMainId());
        Card card = cardService.findOne(dto.getForeignId());
        if (emitter != null && card != null) {
            emitter.addOwned(card);
            userService.update(emitter);
            cardService.update(card);
        } else if (emitter == null) {
            throw new AssertionError("Aucun utilisateur portant ce identifiant n'a été retrouvé.");
        } else throw new AssertionError("Veuillez entrer un identifiant valide pour la tâche.");
        return mapper.toDto(emitter);
    }

    @PUT
    @Path("createCard/many")
    @Consumes("application/json")
    public UserDto createManyCard(ManyToOneDto dto) {
        User emitter = userService.findOne(dto.getMainId());
        List<Card> cards = new ArrayList<>();

        for (Long id : dto.getForeignIds()) {
            Card c = cardService.findOne(id);
            if (c != null) {
                cards.add(c);
            } else
                throw new AssertionError("Une erreur est survenu lors de la création de la Card d'identifiant : " + id + ".");
        }

        if (emitter != null) {
            for (Card card : cards) {
                emitter.addOwned(card);
                userService.update(emitter);
                cardService.update(card);
            }
        } else throw new AssertionError("Aucun utilisateur portant ce identifiant n'a été retrouvé.");

        return mapper.toDto(emitter);
    }

    @PUT
    @Path("addTask/many")
    @Consumes("application/json")
    public UserDto addManyTask(ManyToOneDto dto) {
        User emitter = userService.findOne(dto.getMainId());
        List<Card> cards = new ArrayList<>();

        for (Long id : dto.getForeignIds()) {
            Card c = cardService.findOne(id);
            if (c != null) {
                cards.add(c);
            } else
                throw new AssertionError("Une erreur est survenu lors de la création de la Card d'identifiant : " + id + ".");
        }

        if (emitter != null) {
            for (Card card : cards) {
                emitter.addTache(card);
                userService.update(emitter);
                cardService.update(card);
            }
        } else throw new AssertionError("Aucun utilisateur portant ce identifiant n'a été retrouvé.");

        return mapper.toDto(emitter);
    }

    @PUT
    @Path("addTask")
    @Consumes("application/json")
    public UserDto addTask(OneToOneDto dto) {
        User emitter = userService.findOne(dto.getMainId());
        Card card = cardService.findOne(dto.getForeignId());

        if (emitter != null && card != null) {
            emitter.addTache(card);
            userService.update(emitter);
            cardService.update(card);
        } else if (emitter == null) {
            throw new AssertionError("Aucun utilisateur portant ce identifiant n'a été retrouvé.");
        } else throw new AssertionError("Veuillez entrer un identifiant valide pour la tâche.");
        return mapper.toDto(emitter);
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteById(@PathParam("userId") Long userId) {
        userService.deleteById(userId);
        return Response.ok().entity(" nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

}
