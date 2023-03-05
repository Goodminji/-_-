package com.example.fastcampusmysql.domain.member.serivce;

import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberReadService {

    final private MemberRepository memberRepository;

    public MemberDto getMember(Long id){
        var member =  memberRepository.findbyId(id).get();
        return toDto(member);
    }
    public MemberDto toDto(Member member){

        return new MemberDto(member.getId(), member.getEmail(), member.getNickname(), member.getBirthday());
    }
}

