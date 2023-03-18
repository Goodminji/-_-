package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

    final private NamedParameterJdbcTemplate jdbcNamedTemplate;

    static final private String TABLE = "member";
    public Optional<Member> findbyId(Long id){

        var sql = String.format("SELECT * FROM %s WHERE id = :id",TABLE);
        var param = new MapSqlParameterSource()
                .addValue("id",id);

        RowMapper<Member> rowMapper = (ResultSet resultSet,int rowNum) -> Member.builder()
                .id(resultSet.getLong("id"))
                .email(resultSet.getString("email"))
                .nickname(resultSet.getString("nickname"))
                .birthday(resultSet.getObject("birthday", LocalDate.class))
                .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
                .build();

        var member = jdbcNamedTemplate.queryForObject(sql,param,rowMapper);
        return Optional.ofNullable(member);
    }
    public Member save(Member member){
        /*member id 넣고 갱신.삽입.
        * id 반환*/
        if(member.getId() == null){
            return insert(member);
        }
        return update(member);
    }

    private Member insert(Member member){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcNamedTemplate.getJdbcTemplate())
                .withTableName("Member")
                .usingGeneratedKeyColumns("id");
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);

        var id =  simpleJdbcInsert.executeAndReturnKey(parameterSource).longValue();
        return Member.builder()
                .id(id)
                .email(member.getEmail())
                .nickname(member.getNickname())
                .birthday(member.getBirthday())
                .createdAt(member.getCreatedAt())
                .build();
    }
    private Member update(Member member){
        var sql = String.format("UPDATE %s set email = :email, nickname = :nickname,birthday = :birthday WHERE id = :id",TABLE);

        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        jdbcNamedTemplate.update(sql,params);
        return member;
    }
}
