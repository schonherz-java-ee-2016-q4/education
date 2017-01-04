package hu.schonherz.training.springmvcexample.data.dao.impl;

import hu.schonherz.training.springmvcexample.data.dao.GenericCrudDAO;
import hu.schonherz.training.springmvcexample.data.domain.ParticipantDTO;
import hu.schonherz.training.springmvcexample.data.mapper.ParticipantMapper;
import hu.schonherz.training.springmvcexample.data.queries.ParticipantQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ParticipantDAO implements GenericCrudDAO<ParticipantDTO, Integer> {

    private static final ParticipantMapper MAPPER = new ParticipantMapper();

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<ParticipantDTO> findAll() {
        return jdbcTemplate.query(ParticipantQueries.QUERY_FIND_ALL, MAPPER);
    }

    @Override
    public ParticipantDTO findById(final Integer id) {
        return jdbcTemplate.queryForObject(ParticipantQueries.QUERY_FIND_BY_ID, MAPPER, id);
    }

    @Override
    public ParticipantDTO save(final ParticipantDTO entity) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("promotion_tracker.participant")
                .usingColumns("name", "email")
                .usingGeneratedKeyColumns("id");

        final Map<String, Object> parameters = new HashMap<>(2);
        parameters.put("name", entity.getName());
        parameters.put("email", entity.getEmail());

        final Number generatedID = insert.executeAndReturnKey(parameters);
        if (generatedID != null) {
            entity.setId(generatedID.intValue());
        }

        return entity;
    }

    @Override
    public boolean remove(Integer integer) {
        return false;
    }
}
