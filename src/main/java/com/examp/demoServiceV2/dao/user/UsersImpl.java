package com.examp.demoServiceV2.dao.user;

import com.examp.demoServiceV2.dao.Dao;
import com.examp.demoServiceV2.entity.users.Users;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.conf.ParamType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.*;

@Component
@RequiredArgsConstructor
public class UsersImpl extends Dao<Users> {

    private final JdbcTemplate jdbcTemplate;
    private final DSLContext dsl;

    public List<Users> getAll() {
        return jdbcTemplate.query(dsl.selectFrom("users").getSQL(), this::mapperResult);
    }

    public Users getById(long id) throws DataAccessException {
        final String sql = dsl.selectFrom(table("users"))
                .where(
                        field("id").eq(id).and(field("active").eq(true))
                ).getSQL(ParamType.INLINED);
        return jdbcTemplate.queryForObject(sql, this::mapperResult);
    }

    public Optional<Users> getByEmail(String email, boolean active) {
        final String sql = dsl.selectFrom(table("users"))
                .where(
                        field("email").eq(email).and(field("active").eq(active))
                ).getSQL(ParamType.INLINED);
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapperResult));
    }

    public Optional<Users> getByUid(String uid) {
        final String sql = dsl.selectFrom(table("users"))
                .where(
                        field("uid").eq(uid).and(field("active").eq(true))
                ).getSQL(ParamType.INLINED);
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapperResult));
    }

    public boolean userExistByEmail(String email, boolean active) {
        final String sql = dsl.selectFrom(table("users"))
                .where(
                        field("email").eq(email).and(field("active").eq(active))
                ).getSQL(ParamType.INLINED);
        List<Users> query = jdbcTemplate.query(sql, this::mapperResult);
        return query.size() != 0;
    }

    public boolean userExistByUid(String uid) {
        final String sql = dsl.selectFrom(table("users"))
                .where(
                        field("uid").eq(uid).and(field("active").eq(true))
                ).getSQL(ParamType.INLINED);
        List<Users> query = jdbcTemplate.query(sql, this::mapperResult);
        return query.size() != 0;
    }

    public boolean create(Users users) {
        final String sql = dsl.insertInto(table("users")).columns(
                field("uid"),
                field("firstName"),
                field("lastName"),
                field("email"),
                field("password"),
                field("active")
        ).values(
                users.getUid(),
                users.getFirstName(),
                users.getLastName(),
                users.getEmail(),
                users.getPassword(),
                users.isActive()
        ).getSQL(ParamType.INLINED);
        int update = jdbcTemplate.update(sql);
        return update != 0;
    }

    public boolean update(Users users) {
        final String sql = dsl.update(table("users"))
                .set(field("firstName"), value(users.getFirstName()))
                .set(field("lastName"), value(users.getLastName()))
                .set(field("email"), value(users.getEmail()))
                .set(field("password"), value(users.getPassword()))
                .where(field("uid").eq(users.getUid()).and(field("active").eq(true)))
                .getSQL(ParamType.INLINED);
        int update = jdbcTemplate.update(sql);
        return update != 0;
    }

    @Override
    protected Users mapperResult(ResultSet rs, int i) throws SQLException {
        return Users.builder()
                .id(rs.getLong("id"))
                .uid(rs.getString("uid"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .active(rs.getBoolean("active"))
                .build();
    }
}
