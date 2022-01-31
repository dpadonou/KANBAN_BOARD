package web;

import dto.AddUserDto;
import dto.BatchUserDto;
import dto.UserDTO;
import dto.mapper.UserMapper;
import entities.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.Body;
import service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Path("/user")
@Produces({"application/json", "application/xml"})
public class UserRessource {

    UserService service = new UserService();
    UserMapper mapper = new UserMapper();

    @GET
    @Path("/{userId}")
    public UserDTO findOne(@PathParam("userId") Long userId)  {
        User user = service.findOne(userId);
        return mapper.toDto(user);
    }

    @GET
    public List<UserDTO> findAll()  {
        List<User> users = service.findAll();
        return users.stream().map(user -> mapper.toDto(user)).collect(Collectors.toList());
    }

    @POST
    @Consumes("application/json")
    public Response save(AddUserDto userDTO) {
        service.save(mapper.toUser(userDTO));
        //TODO Use created and retreive the created ressource url
        return Response.ok().entity("Le nouvel utilisateur a été ajouté avec succès.").build();
    }

    @POST
    @Path("/all")
    @Consumes("application/json")
    public Response save(BatchUserDto userList) {
        userList.getUserList().forEach(addUserDto -> service.save(mapper.toUser(addUserDto)));
        //TODO Use created and retreive the created ressource url
        return Response.ok().entity(userList.getUserList().size() + " nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

    @PUT
    @Path("/{userId}")
    @Consumes("application/json")
    //TODO: Récupérer un id dans le path et les nouvelles valeurs dans le body
    public UserDTO update(@PathParam("userId") Long id, AddUserDto userDTO){
        User old = service.findOne(id);
        if (old != null){
            User newUser = mapper.toUser(userDTO);
            old.setFirstName(newUser.getFirstName());
            old.setLastName(newUser.getLastName());
            service.update(old);
        }else throw new AssertionError("Aucun utilisateur portant ce identifiant n'a été retrouvé.");
        return mapper.toDto(old);
    }

}
