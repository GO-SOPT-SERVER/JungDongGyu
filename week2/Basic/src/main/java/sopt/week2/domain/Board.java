package sopt.week2.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Board {
    private Long boardId;
    private LocalDateTime dateTime;
    private Long authorId;
    private String title;
    private String content;
}
