package com.examp.demoServiceV2.dao.user;

import com.examp.demoServiceV2.dao.Dao;
import com.examp.demoServiceV2.entity.users.Role;
import com.examp.demoServiceV2.entity.users.Users;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.conf.ParamType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.jooq.impl.DSL.*;

@Component
@RequiredArgsConstructor
public class UsersImpl extends Dao<Users> {

    private final JdbcTemplate jdbcTemplate;
    private final DSLContext dsl;

    public List<Users> getAll() {
        return jdbcTemplate.query(dsl.selectFrom(Users.TABLE).getSQL(), this::mapperResult);
    }

    public Users getById(long id) throws DataAccessException {
        final String sql = dsl.selectFrom(table(Users.TABLE))
                .where(
                        field(Users.ID).eq(id).and(field(Users.ACTIVE).eq(true))
                ).getSQL(ParamType.INLINED);
        return jdbcTemplate.queryForObject(sql, this::mapperResult);
    }

    public List<String> getRolesByUser(long id) {
        final String sql = String.format("select r.role_name from users_roles ur join users u on ur.users_id = u.id join roles r on r.id = ur.roles_id where ur.users_id = %s", id);
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString(1));
    }

    public Optional<Users> getByEmail(String email, boolean active) {
        final String sql = dsl.selectFrom(table(Users.TABLE))
                .where(
                        field(Users.EMAIL).eq(email).and(field(Users.ACTIVE).eq(active))
                ).getSQL(ParamType.INLINED);
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapperResult));
    }

    public Optional<Users> getByUid(String uid) {
        final String sql = dsl.selectFrom(table(Users.TABLE))
                .where(
                        field(Users.UID).eq(uid).and(field(Users.ACTIVE).eq(true))
                ).getSQL(ParamType.INLINED);
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapperResult));
    }

    public boolean userExistByEmail(String email, boolean active) {
        final String sql = dsl.selectFrom(table(Users.TABLE))
                .where(
                        field(Users.EMAIL).eq(email).and(field(Users.ACTIVE).eq(active))
                ).getSQL(ParamType.INLINED);
        List<Users> query = jdbcTemplate.query(sql, this::mapperResult);
        return query.size() != 0;
    }

    public boolean userExistByUid(String uid) {
        final String sql = dsl.selectFrom(table(Users.TABLE))
                .where(
                        field(Users.UID).eq(uid).and(field(Users.ACTIVE).eq(true))
                ).getSQL(ParamType.INLINED);
        List<Users> query = jdbcTemplate.query(sql, this::mapperResult);
        return query.size() != 0;
    }

    public void create(Users users) {
        final String sql = dsl.insertInto(table(Users.TABLE)).columns(
                field(Users.UID),
                field(Users.FIRST_NAME),
                field(Users.LAST_NAME),
                field(Users.EMAIL),
                field(Users.PASSWORD),
                field(Users.ACTIVE)
        ).values(
                users.getUid(),
                users.getFirstName(),
                users.getLastName(),
                users.getEmail(),
                users.getPassword(),
                users.isActive()
        ).getSQL(ParamType.INLINED);
        jdbcTemplate.update(sql);
    }

    public void update(Users users) {
        final String sql = dsl.update(table(Users.TABLE))
                .set(field(Users.FIRST_NAME), value(users.getFirstName()))
                .set(field(Users.LAST_NAME), value(users.getLastName()))
                .set(field(Users.EMAIL), value(users.getEmail()))
                .set(field(Users.PASSWORD), value(users.getPassword()))
                .where(field(Users.UID).eq(users.getUid()).and(field(Users.ACTIVE).eq(true)))
                .getSQL(ParamType.INLINED);
        jdbcTemplate.update(sql);
    }

    @Override
    protected Users mapperResult(ResultSet rs, int i) throws SQLException {
        return Users.builder()
                .id(rs.getLong(Users.ID))
                .uid(rs.getString(Users.UID))
                .firstName(rs.getString(Users.FIRST_NAME))
                .lastName(rs.getString(Users.LAST_NAME))
                .email(rs.getString(Users.EMAIL))
                .password(rs.getString(Users.PASSWORD))
                .active(rs.getBoolean(Users.ACTIVE))
                .build();
    }
}
