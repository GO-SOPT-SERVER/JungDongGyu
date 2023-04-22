package sopt.week2.controller.dto.request;

import lombok.Data;

@Data
public class BoardRequestDto {

    private Long authorId;
    private String title;
    private String content;
}
