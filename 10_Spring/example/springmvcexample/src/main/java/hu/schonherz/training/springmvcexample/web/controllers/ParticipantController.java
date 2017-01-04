package hu.schonherz.training.springmvcexample.web.controllers;

import hu.schonherz.training.springmvcexample.service.domain.Participant;
import hu.schonherz.training.springmvcexample.service.participant.ParticipantService;
import hu.schonherz.training.springmvcexample.web.domain.ParticipantModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping(path = "/")
    public ModelAndView newParticipant() {
        return new ModelAndView("index", "model", new ParticipantModel());
    }

    // @PostMapping is a Spring MVC 4 shorthand for @RequestMapping with method = RequestMethod.POST
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String saveParticipant(final @ModelAttribute ParticipantModel model) {
        participantService.save(toParticipant(model));
        return "success";
    }

    private static Participant toParticipant(final ParticipantModel model) {
        return Participant.builder()
                .name(model.getName())
                .email(model.getEmail())
                .build();
    }
}
