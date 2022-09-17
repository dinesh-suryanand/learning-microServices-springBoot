package in.dinesh.restful.restfulservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonalController {

    @GetMapping("/v1/person")
    public Personv1 getFirstVersionOfPerson(){
        return new  Personv1("Bob Marley");
    }

    @GetMapping("/v2/person")
    public Personv2 getSecondVersionOfPerson(){
        return new Personv2(new Name("Bob" , "The Builder"));
    }
}
