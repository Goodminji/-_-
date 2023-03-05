package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.serivce.MemberReadService;
import com.example.fastcampusmysql.domain.member.serivce.MemberWriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {
    final private MemberWriterService memberWriterService;
    final private MemberReadService memberReadService;

    @PostMapping("/members")
    public MemberDto register(@RequestBody RegisterMemberCommand command){
       var member = memberWriterService.register(command);
       return  memberReadService.toDto(member);
    }

    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable Long id){
        return  memberReadService.getMember(id);
    }
}
