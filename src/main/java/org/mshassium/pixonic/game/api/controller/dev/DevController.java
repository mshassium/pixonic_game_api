package org.mshassium.pixonic.game.api.controller.dev;

import io.swagger.models.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class DevController {

    @RequestMapping("/")
    public String redirectToSwagger(Model model) {
        return "redirect:/swagger-ui.html";
    }

}
