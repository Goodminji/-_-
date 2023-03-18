package com.example.fastcampusmysql.util;

import com.example.fastcampusmysql.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class MemberFixtureFactory {
    static public Member create(){
        /* EasyRandom 테스트 코드 짤때 자바빈을 만들어주는 라이브러리
         랜덤하게 값을 만들어준다. */
        var param =  new EasyRandomParameters();

        return new EasyRandom(param).nextObject(Member.class);
    }

    static public Member create(Long seed){

        /* EasyRandom 테스트 코드 짤때 자바빈을 만들어주는 라이브러리
         랜덤하게 값을 만들어준다. */
        var param =  new EasyRandomParameters().seed(seed);

        return new EasyRandom(param).nextObject(Member.class);
    }
}
