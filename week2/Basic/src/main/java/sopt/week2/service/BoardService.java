package sopt.week2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.week2.controller.dto.request.BoardRequestDto;
import sopt.week2.domain.Board;
import sopt.week2.domain.repository.BoardRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board saveBoard(BoardRequestDto boardRequestDto) {
        Board board = new Board();
        board.setAuthorId(boardRequestDto.getAuthorId());
        board.setTitle(boardRequestDto.getTitle());
        board.setContent(boardRequestDto.getContent());
        boardRepository.saveBoard(board);
        return board;
    }

    public boolean isBoardExist(Long id) {
        return boardRepository.isBoardExist(id);
    }


    public Board findBoardByTitle(String title) {
        return boardRepository.findBoardByTitle(title);
    }
    public Board findBoardById(Long id) {
        return boardRepository.findBoardById(id);
    }

    public List<Board> getAllBoard() {
        return boardRepository.searchAllBoards();
    }

    public Board updateBoard(Long boardId, BoardRequestDto boardRequestDto) {
        Board updateBoard = new Board();
        // 바뀌지 않아야하는 정보
        updateBoard.setBoardId(boardId);
        updateBoard.setDateTime(LocalDateTime.now());
        updateBoard.setAuthorId(boardRequestDto.getAuthorId());

        // 바뀔 수 있는 정보 덮어쓰기
        updateBoard.setTitle(boardRequestDto.getTitle());
        updateBoard.setContent(boardRequestDto.getContent());
        return boardRepository.updateBoard(updateBoard);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteMemberById(id);
    }
}
