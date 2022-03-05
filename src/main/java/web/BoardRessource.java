package web;

import dto.add.AddBoardDto;
import dto.batch.BatchBoardDto;
import dto.list.BoardDto;
import dto.mapper.BoardMapper;
import dto.relations.ManyToOneDto;
import dto.relations.OneToOneDto;
import entities.Board;
import entities.Section;
import service.BoardService;
import service.SectionService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/board")
@Produces({"application/json", "application/xml"})
public class BoardRessource {

    BoardService boardService = new BoardService();
    SectionService sectionService = new SectionService();
    BoardMapper mapper = new BoardMapper();

    @GET
    @Path("/{boardId}")
    public BoardDto findOne(@PathParam("boardId") Long boardId) {
        Board board = boardService.findOne(boardId);
        return mapper.toDto(board);
    }

    @GET
    @Path("/works-on/{userId}")
    public List<BoardDto> findAllByCardId(@PathParam("userId") Long userId) {
        List<Board> boards = boardService.findAllByUserId(userId);
        return boards.stream().map(board -> mapper.toDto(board)).collect(Collectors.toList());
    }

    @GET
    public List<BoardDto> findAll() {
        List<Board> boards = boardService.findAll();
        return boards.stream().map(board -> mapper.toDto(board)).collect(Collectors.toList());
    }

    @POST
    @Consumes("application/json")
    public Response save(AddBoardDto boardDto) {
        boardService.save(mapper.toBoard(boardDto));
        return Response.ok().entity("Le nouvel utilisateur a été ajouté avec succès.").build();
    }

    @POST
    @Path("/many")
    @Consumes("application/json")
    public Response save(BatchBoardDto boardList) {
        boardList.getBoardList().forEach(addBoardDto -> boardService.save(mapper.toBoard(addBoardDto)));
        return Response.ok().entity(boardList.getBoardList().size() + " nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

    @PUT
    @Path("/{boardId}")
    @Consumes("application/json")
    public BoardDto update(@PathParam("boardId") Long id, AddBoardDto boardDto) {
        Board old = boardService.findOne(id);
        if (old != null) {
            Board newBoard = mapper.toBoard(boardDto);
            old.setTitle(newBoard.getTitle());
            boardService.update(old);
        } else throw new AssertionError("Aucun board portant ce identifiant n'a été retrouvé.");
        return mapper.toDto(old);
    }

    @PUT
    @Path("addSection/many")
    @Consumes("application/json")
    public BoardDto addManySection(ManyToOneDto dto) {
        Board emitter = boardService.findOne(dto.getMainId());
        List<Section> sections = new ArrayList<>();

        for (Long id : dto.getForeignIds()) {
            Section s = sectionService.findOne(id);
            if (s != null) {
                sections.add(s);
            } else throw new AssertionError("Aucune section portant l'identifiant : " + id + " n'a été retrouvée.");
        }

        if (emitter != null) {
            for (Section section : sections) {
                emitter.addSection(section);
                boardService.update(emitter);
                sectionService.update(section);
            }
        } else throw new AssertionError("Aucun board portant ce identifiant n'a été retrouvé.");

        return mapper.toDto(emitter);
    }

    @PUT
    @Path("addSection")
    @Consumes("application/json")
    public BoardDto addSection(OneToOneDto dto) {
        Board emitter = boardService.findOne(dto.getMainId());
        Section s = sectionService.findOne(dto.getForeignId());

        if (emitter != null && s != null) {
            emitter.addSection(s);
            boardService.update(emitter);
            sectionService.update(s);
        } else if (emitter == null) {
            throw new AssertionError("Aucun board portant ce identifiant n'a été retrouvé.");
        } else throw new AssertionError("Veuillez entrer un identifiant valide pour la section.");
        return mapper.toDto(emitter);
    }

    @DELETE
    @Path("/{boardId}")
    public Response deleteById(@PathParam("boardId") Long boardId) {
        boardService.deleteById(boardId);
        return Response.ok().entity(" nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

}
