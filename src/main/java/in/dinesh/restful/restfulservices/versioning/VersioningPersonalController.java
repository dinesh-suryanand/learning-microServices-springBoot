package in.dinesh.restful.restfulservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonalController {

    // versioning using URI  -- URI versioning
    // ../v1/person
    // ../v2/person
    @GetMapping("/v1/person")
    public Personv1 getFirstVersionOfPerson(){
        return new  Personv1("Bob Marley");
    }

    @GetMapping("/v2/person")
    public Personv2 getSecondVersionOfPerson(){
        return new Personv2(new Name("Bob" , "The Builder"));
    }

    // versioning using parameters  -- Request Param Versioning
    // ../person?version=1
    // ../v2/person?version=12
    @GetMapping(path="/person",params = "version=1")
    public Personv1 getFirstVersionOfPersonRequestParam(){
        return new  Personv1("Bob Marley");
    }

    @GetMapping(path="/person",params = "version=2")
    public Personv2 getSecondVersionOfPersonRequestParam(){
        return new Personv2(new Name("Bob" , "The Builder"));
    }


    //versing using headers  --Headers Vesioning
    // ../person    URL is same but headers=[X-API-VERSION=1]
    // ../person    URL is same but headers=[X-API-VERSION=2]

    @GetMapping(path="/person/header",headers = "X-API-VERSION=1")
    public Personv1 getFirstVersionOfPersonRequestHeader(){
        return new  Personv1("Bob Marley");
    }

    @GetMapping(path="/person/header",headers = "X-API-VERSION=2")
    public Personv2 getSecondVersionOfPersonRequestHeader(){
        return new Personv2(new Name("Bob" , "The Builder"));
    }


    //versing using Accept Header  --Media type Vesioning
    // ../person    URL is same but produces=application/in.dinesh.app-v1+json
    // ../person    URL is same but produces=application/in.dinesh.app-v2+json
    @GetMapping(path="/person/accept",produces = "application/in.dinesh.app-v1+json")
    public Personv1 getFirstVersionOfAcceptRequestHeader(){
        return new  Personv1("Bob Marley");
    }

    @GetMapping(path="/person/header",produces = "application/in.dinesh.app-v2+json")
    public Personv2 getSecondVersionOfPersonAcceptHeader(){
        return new Personv2(new Name("Bob" , "The Builder"));
    }
}
