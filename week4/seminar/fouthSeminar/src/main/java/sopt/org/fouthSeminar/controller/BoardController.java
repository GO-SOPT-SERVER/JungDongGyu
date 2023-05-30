package sopt.org.fouthSeminar.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sopt.org.fouthSeminar.common.dto.ApiResponse;
import sopt.org.fouthSeminar.config.jwt.JwtService;
import sopt.org.fouthSeminar.config.resolver.UserId;
import sopt.org.fouthSeminar.controller.dto.request.BoardImageListRequestDto;
import sopt.org.fouthSeminar.controller.dto.request.BoardRequestDto;
import sopt.org.fouthSeminar.exception.Success;
import sopt.org.fouthSeminar.external.client.aws.S3Service;
import sopt.org.fouthSeminar.service.BoardService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@SecurityRequirement(name = "JWT Auth")
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final S3Service s3Service;
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
    @PostMapping(value = "/create"
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse create(
//            @RequestHeader("Authorization") String accessToken,
            // 직접적인 Token을 다루지 않고 어노테이션과 Handler를 통해 값만 가져옴
            @UserId Long userId,
            @ModelAttribute @Valid final BoardRequestDto request) {
        // @UserId를 통해서 타입까지 적합한 userId 값만을 가져올 수 있음
//        boardService.create(Long.parseLong(jwtService.getJwtContents(accessToken)), request);
        log.info("BoardController.creat() : {}", request.getContent());
        String boarThumbnailImageUrl = s3Service.uploadImage(request.getThumbnail(), "board");
        boardService.create(userId, boarThumbnailImageUrl, request);
        return ApiResponse.success(Success.CREATE_BOARD_SUCCESS);
    }
    @PostMapping(value = "/create/list"
            , consumes = MediaType.MULTIPART_FORM_DATA_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse createImages(
//            @RequestHeader("Authorization") String accessToken,
            // 직접적인 Token을 다루지 않고 어노테이션과 Handler를 통해 값만 가져옴
            @UserId Long userId,
            @ModelAttribute @Valid final BoardImageListRequestDto request) {
        // @UserId를 통해서 타입까지 적합한 userId 값만을 가져올 수 있음
//        boardService.create(Long.parseLong(jwtService.getJwtContents(accessToken)), request);
        List<String> boarThumbnailImageUrl = s3Service.uploadImages(request.getBoardImages(), "board");
        boardService.createImages(userId, boarThumbnailImageUrl, request);
        return ApiResponse.success(Success.CREATE_BOARD_SUCCESS);
    }
}
