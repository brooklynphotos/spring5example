package photos.brooklyn.spring5example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Note that this is not public as Spring will find it
 */
@RestController
@RequestMapping("/echo")
class EchoController {

    @GetMapping(value="/{word}")
    public String echo(@PathVariable("word") String word) {
        return word;
    }
}
