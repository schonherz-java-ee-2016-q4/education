package hu.schonherz.training.springmvcexample.data.mapper;

import hu.schonherz.training.springmvcexample.data.domain.ParticipantDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParticipantMapper implements RowMapper<ParticipantDTO> {

    @Override
    public ParticipantDTO mapRow(final ResultSet resultSet, final int i) throws SQLException {
        return ParticipantDTO.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .email(resultSet.getString("email"))
                .build();
    }
}
