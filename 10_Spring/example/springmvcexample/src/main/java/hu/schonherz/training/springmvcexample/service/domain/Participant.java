package hu.schonherz.training.springmvcexample.service.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(of = { "id" })
public class Participant {
    private int id;
    private String name;
    private String email;
}
