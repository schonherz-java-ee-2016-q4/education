package hu.schonherz.training.springmvcexample.service.participant;

import hu.schonherz.training.springmvcexample.service.domain.Participant;
import hu.schonherz.training.springmvcexample.web.domain.ParticipantModel;

import java.util.Collection;

public interface ParticipantService {
    Collection<Participant> getAll();
    Participant getById(int id);
    Participant save(Participant participant);
}
