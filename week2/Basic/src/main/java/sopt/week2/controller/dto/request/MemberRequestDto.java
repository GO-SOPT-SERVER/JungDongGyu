package sopt.week2.controller.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
public class MemberRequestDto {
    private String name;
    private int age;

    private String phoneNumber;
}
