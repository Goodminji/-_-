package com.example.fastcampusmysql.domain.member.repository;

import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.entity.MemberNicknameHistory;
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
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberNicknameHistoryRepository {

    final private NamedParameterJdbcTemplate jdbcNamedTemplate;

    static final private String TABLE = "MemberNicknameHistory";

    static final RowMapper<MemberNicknameHistory> rowMapper = (ResultSet resultSet, int rowNum) -> MemberNicknameHistory
            .builder()
            .id(resultSet.getLong("id"))
            .memberId(resultSet.getLong("memberId"))
            .nickname(resultSet.getString("nickname"))
            .createdAt(resultSet.getObject("createdAt",LocalDateTime.class))
            .build();

    public List<MemberNicknameHistory> findAllByMemberId(Long memberId){
        var sql = String.format("SELECT * FROM %s WHERE memberId = :memberId",TABLE);
        var parmas = new MapSqlParameterSource().addValue("memberId",memberId);
        return jdbcNamedTemplate.query(sql,parmas,rowMapper);
    }
    public MemberNicknameHistory save(MemberNicknameHistory member){
        if(member.getId() == null){
            return insert(member);
        }
        throw new UnsupportedOperationException("MemberNicknameHistory는 갱신을 지원하지 않습니다.");
    }

    private MemberNicknameHistory insert(MemberNicknameHistory member){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcNamedTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(member);

        var id =  simpleJdbcInsert.executeAndReturnKey(parameterSource).longValue();
        return MemberNicknameHistory.builder()
                .id(id)
                .memberId(member.getMemberId())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .build();
    }

}
