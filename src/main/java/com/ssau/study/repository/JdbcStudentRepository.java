package com.ssau.study.repository;

import com.ssau.study.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class JdbcStudentRepository implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private RowMapper<Student> studentMapper = (rs, rowNum) -> {
        Student student = new Student();
        student.setId(rs.getLong("id"));
        student.setName(rs.getString("name"));
        student.setBirthdate(rs.getDate("birthdate"));
        student.setNumber(rs.getInt("number"));
        return student;
    };

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from public.students", Integer.class);
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("select * from public.students", studentMapper);
    }

    @Override
    public List<Student> findAllByName(String name) {
        return namedParameterJdbcTemplate.query("select * from public.students where name ilike '%' || :name || '%'",
                Collections.singletonMap("name", name), studentMapper);
    }

    @Override
    public int update(Student student) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        ps.addValue("id", student.getId());
        ps.addValue("name", student.getName());
        ps.addValue("birthdate", student.getBirthdate());
        ps.addValue("number", student.getNumber());

        int result = namedParameterJdbcTemplate.update("update public.students " +
                        "set name = :name, birthdate = :birthdate, number = :number " +
                        "where id = :id ",
                ps);
        return result;
    }

    @Override
    public Student save(Student student) {
        MapSqlParameterSource ps = new MapSqlParameterSource();
        ps.addValue("name", student.getName());
        ps.addValue("birthdate", student.getBirthdate());
        ps.addValue("number", student.getNumber());

        int id = namedParameterJdbcTemplate.queryForObject(
                "INSERT INTO public.students (name, birthdate, number) VALUES (:name, :birthdate, :number) RETURNING id",
                ps, Integer.class
        );
        student.setId(id);
        return student;
    }


    @Override
    public int delete(long id) {
        return namedParameterJdbcTemplate.update(
                "DELETE FROM public.students WHERE id=:id", Collections.singletonMap("id", id)
        );
    }
}
