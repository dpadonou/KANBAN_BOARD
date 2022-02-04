package web;

import dto.add.AddSectionDto;
import dto.batch.BatchSectionDto;
import dto.list.SectionDto;
import dto.mapper.SectionMapper;
import entities.Section;
import service.SectionService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/section")
@Produces({"application/json", "application/xml"})
public class SectionRessource {

    SectionService service = new SectionService();
    SectionMapper mapper = new SectionMapper();

    @GET
    @Path("/{sectionId}")
    public SectionDto findOne(@PathParam("sectionId") Long sectionId) {
        Section section = service.findOne(sectionId);
        return mapper.toDto(section);
    }

    @GET
    public List<SectionDto> findAll() {
        List<Section> sections = service.findAll();
        return sections.stream().map(section -> mapper.toDto(section)).collect(Collectors.toList());
    }

    @POST
    @Consumes("application/json")
    public Response save(AddSectionDto sectionDto) {
        service.save(mapper.toSection(sectionDto));
        
        return Response.ok().entity("Le nouvel utilisateur a été ajouté avec succès.").build();
    }

    @POST
    @Path("/many")
    @Consumes("application/json")
    public Response save(BatchSectionDto sectionList) {
        sectionList.getSectionList().forEach(addSectionDto -> service.save(mapper.toSection(addSectionDto)));
        return Response.ok().entity(sectionList.getSectionList().size() + " nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

    @PUT
    @Path("/{sectionId}")
    @Consumes("application/json")
    //TODO: Récupérer un id dans le path et les nouvelles valeurs dans le body
    public SectionDto update(@PathParam("sectionId") Long id, AddSectionDto sectionDto) {
        Section old = service.findOne(id);
        if (old != null) {
            Section newSection = mapper.toSection(sectionDto);
            old.setName(newSection.getName());
            service.update(old);
        } else throw new AssertionError("Aucune section portant ce identifiant n'a été retrouvé.");
        return mapper.toDto(old);
    }

    @DELETE
    @Path("/{sectionId}")
    public Response deleteById(@PathParam("sectionId") Long sectionId) {
        service.deleteById(sectionId);
        return Response.ok().entity(" nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

}
