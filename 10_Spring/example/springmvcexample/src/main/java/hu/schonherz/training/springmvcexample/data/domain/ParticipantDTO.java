package hu.schonherz.training.springmvcexample.data.domain;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode(of = { "id" })
public class ParticipantDTO {
    private int id;
    private String name;
    private String email;
}
