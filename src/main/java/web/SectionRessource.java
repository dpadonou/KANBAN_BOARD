package web;

import dto.add.AddSectionDto;
import dto.batch.BatchSectionDto;
import dto.list.SectionDto;
import dto.mapper.SectionMapper;
import dto.relations.ManyToOneDto;
import dto.relations.OneToOneDto;
import entities.Card;
import entities.Section;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import service.CardService;
import service.SectionService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/section")
@Produces({"application/json", "application/xml"})
public class SectionRessource {

    SectionService sectionService = new SectionService();
    CardService cardService = new CardService();
    SectionMapper mapper = new SectionMapper();

    @GET
    @Path("/{sectionId}")
    @Operation(
            summary = "Retourne la section ayant un identifiant donné.",
            responses = {
                    @ApiResponse(
                            description = "Une section",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = SectionDto.class))
                    )
            }
    )
    public SectionDto findOne(@Parameter(description = "Identifiant de la section à retournée.", required = true) @PathParam("sectionId") Long sectionId) {
        Section section = sectionService.findOne(sectionId);
        return mapper.toDto(section);
    }

    @GET
    public List<SectionDto> findAll() {
        List<Section> sections = sectionService.findAll();
        return sections.stream().map(section -> mapper.toDto(section)).collect(Collectors.toList());
    }

    @POST
    @Consumes("application/json")
    public Response save(AddSectionDto sectionDto) {
        sectionService.save(mapper.toSection(sectionDto));

        return Response.ok().entity("Le nouvel utilisateur a été ajouté avec succès.").build();
    }

    @POST
    @Path("/many")
    @Consumes("application/json")
    public Response save(BatchSectionDto sectionList) {
        sectionList.getSectionList().forEach(addSectionDto -> sectionService.save(mapper.toSection(addSectionDto)));
        return Response.ok().entity(sectionList.getSectionList().size() + " nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

    @PUT
    @Path("/{sectionId}")
    @Consumes("application/json")
    public SectionDto update(@PathParam("sectionId") Long id, AddSectionDto sectionDto) {
        Section old = sectionService.findOne(id);
        if (old != null) {
            Section newSection = mapper.toSection(sectionDto);
            old.setName(newSection.getName());
            sectionService.update(old);
        } else throw new AssertionError("Aucune section portant ce identifiant n'a été retrouvé.");
        return mapper.toDto(old);
    }

    @PUT
    @Path("addTask/many")
    @Consumes("application/json")
    public SectionDto addManyTask(ManyToOneDto dto) {
        Section emitter = sectionService.findOne(dto.getMainId());
        List<Card> cards = new ArrayList<>();

        for (Long id : dto.getForeignIds()) {
            Card c = cardService.findOne(id);
            if (c != null) {
                cards.add(c);
            } else throw new AssertionError("Aucune tâche portant l'identifiant : " + id + " n'a été retrouvée.");
        }

        if (emitter != null) {
            for (Card card : cards) {
                emitter.addCarte(card);
                sectionService.update(emitter);
                cardService.update(card);
            }
        } else throw new AssertionError("Aucune section portant ce identifiant n'a été retrouvé.");

        return mapper.toDto(emitter);
    }

    @PUT
    @Path("addTask")
    @Consumes("application/json")
    public SectionDto addTask(OneToOneDto dto) {
        Section emitter = sectionService.findOne(dto.getMainId());
        Card card = cardService.findOne(dto.getForeignId());

        if (emitter != null && card != null) {
            emitter.addCarte(card);
            sectionService.update(emitter);
            cardService.update(card);
        } else if (emitter == null) {
            throw new AssertionError("Aucune section portant ce identifiant n'a été retrouvé.");
        } else throw new AssertionError("Veuillez entrer un identifiant valide pour la tâche.");
        return mapper.toDto(emitter);
    }

    @DELETE
    @Path("/{sectionId}")
    public Response deleteById(@PathParam("sectionId") Long sectionId) {
        sectionService.deleteById(sectionId);
        return Response.ok().entity(" nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

}
