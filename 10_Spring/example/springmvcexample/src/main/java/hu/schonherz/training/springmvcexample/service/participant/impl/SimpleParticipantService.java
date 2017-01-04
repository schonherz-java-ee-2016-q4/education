package hu.schonherz.training.springmvcexample.service.participant.impl;

import hu.schonherz.training.springmvcexample.data.dao.GenericCrudDAO;
import hu.schonherz.training.springmvcexample.data.domain.ParticipantDTO;
import hu.schonherz.training.springmvcexample.service.domain.Participant;
import hu.schonherz.training.springmvcexample.service.participant.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SimpleParticipantService implements ParticipantService {

    @Autowired
    private GenericCrudDAO<ParticipantDTO, Integer> participantDAO;

    @Override
    public Collection<Participant> getAll() {
        List<Participant> participants = new ArrayList<>();

        for (final ParticipantDTO participantDTO : participantDAO.findAll()) {
            participants.add(toParticipant(participantDTO));
        }

        return participants;
    }

    @Override
    public Participant getById(final int id) {
        return toParticipant(participantDAO.findById(id));
    }

    @Override
    public Participant save(final Participant participant) {
        return toParticipant(participantDAO.save(toDto(participant)));
    }

    // These static methods could be extracted into a utility class
    private static ParticipantDTO toDto(final Participant participant) {
        return ParticipantDTO.builder()
                .name(participant.getName())
                .email(participant.getEmail())
                .build();
    }

    private static Participant toParticipant(final ParticipantDTO participantDTO) {
        return Participant.builder()
                .name(participantDTO.getName())
                .email(participantDTO.getEmail())
                .build();
    }
}
