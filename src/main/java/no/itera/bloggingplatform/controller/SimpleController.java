package no.itera.bloggingplatform.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@RestController
public class SimpleController {

    @RequestMapping(value = {
            "/academy",
            "/academy/{query}"
    })
    public String simpleEndpoint(@PathVariable(required = false) String query) {
        return format(
                "Response to query = ['%s']",
                ofNullable(query).orElse("empty")
        );
    }
}
