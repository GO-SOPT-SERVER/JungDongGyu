package sopt.week2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Member {
    private Long memberId;
    private String name;
    private int age;
    private String phoneNumber;
}
