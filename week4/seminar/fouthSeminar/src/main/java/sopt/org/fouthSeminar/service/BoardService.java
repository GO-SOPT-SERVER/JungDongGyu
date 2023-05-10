package sopt.org.fouthSeminar.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.org.fouthSeminar.controller.dto.request.BoardRequestDto;
import sopt.org.fouthSeminar.domian.Board;
import sopt.org.fouthSeminar.domian.User;
import sopt.org.fouthSeminar.exception.model.NotFoundException;
import sopt.org.fouthSeminar.infrastructure.BoardRepository;
import sopt.org.fouthSeminar.infrastructure.UserRepository;
import sopt.org.fouthSeminar.exception.Error;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void create(BoardRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_USER_EXCEPTION, Error.NOT_FOUND_USER_EXCEPTION.getMessage()));

        Board newBoard = Board.newInstance(
                user,
                request.getTitle(),
                request.getContent(),
                request.getIsPublic()
        );

        boardRepository.save(newBoard);
    }
}
