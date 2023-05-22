package sopt.org.fouthSeminar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sopt.org.fouthSeminar.common.dto.ApiResponse;
import sopt.org.fouthSeminar.config.jwt.JwtService;
import sopt.org.fouthSeminar.config.resolver.UserId;
import sopt.org.fouthSeminar.controller.dto.request.BoardRequestDto;
import sopt.org.fouthSeminar.exception.Success;
import sopt.org.fouthSeminar.service.BoardService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final JwtService jwtService;

/*    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(@RequestBody @Valid final BoardRequestDto request) {
        boardService.create(request);
        return ApiResponse.success(Success.CREATE_BOARD_SUCCESS);
    }*/

    /**
     * JWT 활용 Board Create Controller 메서드
     * @param accessToken JWT 토큰
     * @param request Request
     * @return Response
     */
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(
//            @RequestHeader("Authorization") String accessToken,
            // 직접적인 Token을 다루지 않고 어노테이션과 Handler를 통해 값만 가져옴
            @UserId Long userId,
            @RequestBody @Valid final BoardRequestDto request) {
        // @UserId를 통해서 타입까지 적합한 userId 값만을 가져올 수 있음
//        boardService.create(Long.parseLong(jwtService.getJwtContents(accessToken)), request);
        boardService.create(userId, request);
        return ApiResponse.success(Success.CREATE_BOARD_SUCCESS);
    }
}
