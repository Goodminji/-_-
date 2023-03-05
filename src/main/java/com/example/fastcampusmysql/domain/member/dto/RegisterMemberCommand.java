package com.example.fastcampusmysql.domain.member.dto;

import java.time.LocalDate;
/*
* record : getter,setter 자동으로 만들어줌
* */
public record RegisterMemberCommand(
        String email,
        String nickname,
        LocalDate birthday
) {
}
