package com.example.fastcampusmysql.domain.member;

import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class MemberTest {

    @DisplayName("회원은 닉네임을 변경 할 수 있다")
    @Test
    public void testChangeName(){
        /* objectMother 패턴 :
          테스트 할떄 사용하는 패턴이며며,
          테스트에 필요한 객체를 생성/만들어주는데 도와주는 객체

        LongStream.range(0,10)
                .mapToObj(MemberFixtureFactory::create)
                .forEach(member -> {
                    System.out.println();
                });
         */
        var member = MemberFixtureFactory.create();
        var expected = "pnv";
        member.changeNickname(expected);

        Assertions.assertEquals(expected,member.getNickname());

   }

    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    @Test
    public void testNicknameMaxLength(){
        /* objectMother 패턴 :
          테스트 할떄 사용하는 패턴이며며,
          테스트에 필요한 객체를 생성/만들어주는데 도와주는 객체

        LongStream.range(0,10)
                .mapToObj(MemberFixtureFactory::create)
                .forEach(member -> {
                    System.out.println();
                });
         */
        var member = MemberFixtureFactory.create();
        var expected = "pnv2222222222";


        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> member.changeNickname(expected)
        );

    }
}
