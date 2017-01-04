package hu.schonherz.training.springmvcexample.web.domain;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id" })
public class ParticipantModel {
    private int id;
    private String name;
    private String email;
}
