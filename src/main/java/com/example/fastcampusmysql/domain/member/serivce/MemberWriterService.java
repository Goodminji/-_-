package com.example.fastcampusmysql.domain.member.serivce;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
import com.example.fastcampusmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriterService {
    final private MemberRepository memberRepository;

    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public Member create(RegisterMemberCommand command){
            /*
            *  회원정보(이메일,닉네임,생년월일) 등록.
            *   - 닉네임은 10자를 넘길 수 없다.
            * */
            var member = Member.builder()
                    .nickname(command.nickname())
                    .email(command.email())
                    .birthday(command.birthday())
                    .build();
            var saveMember = memberRepository.save(member);
            saveMemberNicknameHistory(saveMember);
           return saveMember;

    }
    public void ChangeNickname(Long memberId , String nickName ){

        var member = memberRepository.findbyId(memberId).orElseThrow();
        member.changeNickname(nickName);

        saveMemberNicknameHistory(member);

        memberRepository.save(member);
    }

    private void saveMemberNicknameHistory(Member member){
        var history = MemberNicknameHistory.builder()
                .memberId(member.getId())
                .nickname(member.getNickname())
                .build();
        memberNicknameHistoryRepository.save(history);
    }

}
