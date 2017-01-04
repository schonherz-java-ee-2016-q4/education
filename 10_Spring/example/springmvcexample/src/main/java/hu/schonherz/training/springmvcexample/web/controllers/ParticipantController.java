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

    /**
     * Handles calls to /participant/.
     * Will attach a model attribute named 'model' onto the request scope before
     * forwarding to index.jsp.
     *
     * This 'model' will be the backing bean of the form on the index page
     * (note the modelAttribute HTML attribute on the form tag!)
     * The model attribute itself will be an empty {@link ParticipantModel} instance.
     *
     * By filling the form, you will actually set the fields of this empty {@link ParticipantModel}.
     */
    @GetMapping(path = "/")
    public ModelAndView newParticipant() {
        return new ModelAndView(
                "index", // view name
                "model", // model name
                new ParticipantModel()); // actual model instance
    }

    // @PostMapping is a Spring MVC 4 shorthand for @RequestMapping with method = RequestMethod.POST

    /**
     * Handles POST requests to /participants/save.
     * Saves the form's backing bean (the ParticipantModel supplied by the previous method)
     * to the database and forwards to the success.jsp
     */
    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String saveParticipant(final @ModelAttribute ParticipantModel model /* form backing bean */) {
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
