package com.example.fastcampusmysql.controller;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.serivce.MemberReadService;
import com.example.fastcampusmysql.domain.member.serivce.MemberWriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    final private MemberWriterService memberWriterService;
    final private MemberReadService memberReadService;

    @PostMapping("/members")
    public MemberDto register(@RequestBody RegisterMemberCommand command){
       var member = memberWriterService.create(command);
       return  memberReadService.toDto(member);
    }

    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable Long id){
        return  memberReadService.getMember(id);
    }


    @PostMapping("/{id}/name")
    public MemberDto changeNickname(@PathVariable Long id,@RequestBody String nicknames){
        memberWriterService.ChangeNickname(id, nicknames);
        return  memberReadService.getMember(id);
    }

    @GetMapping("/{memberId}/nicknameHistories")
    public List<MemberNicknameHistoryDto> getNicknameHistories(@PathVariable Long memberId){
        return  memberReadService.getNicknameHistories(memberId);
    }
}
