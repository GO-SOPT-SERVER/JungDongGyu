package sopt.week2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.week2.controller.dto.request.BoardRequestDto;
import sopt.week2.domain.Board;
import sopt.week2.service.BoardService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시물 검색 (Query Parameter) 해당 제목의 게시물 검색
     *
     * @param title
     * @return Board
     */
    @GetMapping("/board")
    public ResponseEntity findBoardByTitle(@RequestParam("title") String title) {
        try {
            return ResponseEntity.ok(boardService.findBoardByTitle(title));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity findBoardById(@PathVariable("boardId") Long boardId) {
        try {
            return ResponseEntity.ok(boardService.findBoardById(boardId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/board")
    public ResponseEntity postBoard(
            @RequestBody BoardRequestDto boardRequestDto
    ) {
        try {
            Board postBoard = boardService.saveBoard(boardRequestDto);
            return ResponseEntity.ok(postBoard);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

    }

    @PutMapping("/board/{boardId}")
    public ResponseEntity createOrUpdate(
            @PathVariable("boardId") Long boardId
            , @RequestBody BoardRequestDto boardRequestDto
    ) {
        try {
            Board board;
            if (boardService.isBoardExist(boardId)) {
                board = boardService.updateBoard(boardId, boardRequestDto);
            } else {
                board = boardService.saveBoard(boardRequestDto);
            }
            return ResponseEntity.ok(board);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity viewBoardList() {
        List<Board> boards = boardService.getAllBoard();
        if (boards.isEmpty()) {
            return ResponseEntity.ok("작성된 게시물이 없습니다.");
        }
        return ResponseEntity.ok(boards);
    }

    @DeleteMapping("/delete/{boardId}")
    public ResponseEntity deleteBoard(
            @PathVariable("boardId") Long boardId
    ){
        try {
            boardService.deleteBoard(boardId);
            return ResponseEntity.ok("삭제가 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
