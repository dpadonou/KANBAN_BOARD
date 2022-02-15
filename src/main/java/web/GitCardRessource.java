package web;

import dto.add.AddGitCardDto;
import dto.batch.BatchGitCardDto;
import dto.list.GitCardDto;
import dto.mapper.GitCardMapper;
import entities.GitCard;
import service.GitCardService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/gitCard")
@Produces({"application/json", "application/xml"})
public class GitCardRessource {

    GitCardService service = new GitCardService();
    GitCardMapper mapper = new GitCardMapper();

    @GET
    @Path("/{gitCardId}")
    public GitCardDto findOne(@PathParam("gitCardId") Long gitCardId) {
        GitCard gitCard = service.findOne(gitCardId);
        return mapper.toDto(gitCard);
    }

    @GET
    public List<GitCardDto> findAll() {
        List<GitCard> gitCards = service.findAll();
        return gitCards.stream().map(gitCard -> mapper.toDto(gitCard)).collect(Collectors.toList());
    }

    @POST
    @Consumes("application/json")
    public Response save(AddGitCardDto gitCardDto) {
        service.save(mapper.toGitCard(gitCardDto));
        
        return Response.ok().entity("Le nouvel utilisateur a été ajouté avec succès.").build();
    }

    @POST
    @Path("/many")
    @Consumes("application/json")
    public Response save(BatchGitCardDto gitCardList) {
        gitCardList.getGitCardList().forEach(addGitCardDto -> service.save(mapper.toGitCard(addGitCardDto)));
        return Response.ok().entity(gitCardList.getGitCardList().size() + " nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

    @PUT
    @Path("/{gitCardId}")
    @Consumes("application/json")
    public GitCardDto update(@PathParam("gitCardId") Long id, AddGitCardDto gitCardDto) {
        GitCard old = service.findOne(id);
        if (old != null) {
            GitCard newGitCard = mapper.toGitCard(gitCardDto);
            old.setLibelle(newGitCard.getLibelle());
            old.setCreatedDate(newGitCard.getCreatedDate());
            old.setDeadLine(newGitCard.getDeadLine());
            old.setAllocatedTime(newGitCard.getAllocatedTime());
            old.setLieu(newGitCard.getLieu());
            old.setUrl(newGitCard.getUrl());
            old.setNote(newGitCard.getNote());
            old.setUrlIssue(newGitCard.getUrlIssue());
            old.setGitHash(newGitCard.getGitHash());
            service.update(old);
        } else throw new AssertionError("Aucune gitCard portant ce identifiant n'a été retrouvé.");
        return mapper.toDto(old);
    }

    @DELETE
    @Path("/{gitCardId}")
    public Response deleteById(@PathParam("gitCardId") Long gitCardId) {
        service.deleteById(gitCardId);
        return Response.ok().entity(" nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

}
