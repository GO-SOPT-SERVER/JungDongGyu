package sopt.week2.domain.repository;

import org.springframework.stereotype.Repository;
import sopt.week2.domain.Board;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public class BoardRepository {
    private final List<Board> boards = new ArrayList<>();
    private Long sequence = 0L;

//    public static void main(String[] args) {
//        BoardRepository repository = new BoardRepository();
//        Board firstBoard = new Board();
//        firstBoard.setBoardId(1L);
//        firstBoard.setTitle("Hi");
//        firstBoard.setContent("hello~Spring");
//        firstBoard.setAuthorId(1L);
//        firstBoard.setDateTime(LocalDateTime.now());
//        repository.saveBoard(firstBoard);
//
//        Board updateBoard = new Board();
//        firstBoard.setBoardId(1L);
//        firstBoard.setTitle("Hi");
//        firstBoard.setContent("hello~Jung");
//        firstBoard.setAuthorId(1L);
//        firstBoard.setDateTime(LocalDateTime.now());
//        repository.updateBoard(updateBoard);
//
//        Board findBoard = repository.findBoardById(1L);
//        System.out.println(findBoard);
//    }

    public void saveBoard(Board board) {
        board.setBoardId(++sequence);
        board.setDateTime(LocalDateTime.now());
        boards.add(board);
    }

    public Board updateBoard(Board updateBoard) {
        boards.replaceAll(board -> board.getBoardId().equals(updateBoard.getBoardId()) ?  updateBoard : board);
        return updateBoard;
    }

    public boolean isBoardExist(Long boardId) {
        return boards.stream()
                .anyMatch(board -> Objects.equals(board.getBoardId(), boardId));
    }

    public Board findBoardById(Long boardId) {
        return boards.stream()
                .filter(board -> Objects.equals(board.getBoardId(), boardId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 게시물입니다."));
    }

    public Board findBoardByTitle(String title) {
        return boards.stream()
                .filter(board -> Objects.equals(board.getTitle(), title))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 제목을 가진 게시물을 찾을 수 없습니다."));
    }
    public List<Board> searchAllBoards() {
        if (boards.size() == 0) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(boards);
    }

    public void deleteMemberById(Long boardId) {
        boolean isDeleted = boards.removeIf(board -> Objects.equals(board.getBoardId(), boardId));
        if (!isDeleted) {
            throw new IllegalArgumentException("존재하지 않는 게시물이므로 삭제되지 않습니다.");
        }
    }
}
