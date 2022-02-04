package web;

import dto.add.AddBoardDto;
import dto.batch.BatchBoardDto;
import dto.list.BoardDto;
import dto.mapper.BoardMapper;
import entities.Board;
import service.BoardService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/board")
@Produces({"application/json", "application/xml"})
public class BoardRessource {

    BoardService service = new BoardService();
    BoardMapper mapper = new BoardMapper();

    @GET
    @Path("/{boardId}")
    public BoardDto findOne(@PathParam("boardId") Long boardId) {
        Board board = service.findOne(boardId);
        return mapper.toDto(board);
    }

    @GET
    public List<BoardDto> findAll() {
        List<Board> boards = service.findAll();
        return boards.stream().map(board -> mapper.toDto(board)).collect(Collectors.toList());
    }

    @POST
    @Consumes("application/json")
    public Response save(AddBoardDto boardDto) {
        service.save(mapper.toBoard(boardDto));
        
        return Response.ok().entity("Le nouvel utilisateur a été ajouté avec succès.").build();
    }

    @POST
    @Path("/many")
    @Consumes("application/json")
    public Response save(BatchBoardDto boardList) {
        boardList.getBoardList().forEach(addBoardDto -> service.save(mapper.toBoard(addBoardDto)));
        
        return Response.ok().entity(boardList.getBoardList().size() + " nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

    @PUT
    @Path("/{boardId}")
    @Consumes("application/json")
    //TODO: Récupérer un id dans le path et les nouvelles valeurs dans le body
    public BoardDto update(@PathParam("boardId") Long id, AddBoardDto boardDto) {
        Board old = service.findOne(id);
        if (old != null) {
            Board newBoard = mapper.toBoard(boardDto);
            old.setTitle(newBoard.getTitle());
            service.update(old);
        } else throw new AssertionError("Aucun board portant ce identifiant n'a été retrouvé.");
        return mapper.toDto(old);
    }

    @DELETE
    @Path("/{boardId}")
    public Response deleteById(@PathParam("boardId") Long boardId) {
        service.deleteById(boardId);
        return Response.ok().entity(" nouveaux utilisateurs ont été ajoutés avec succès.").build();
    }

}
