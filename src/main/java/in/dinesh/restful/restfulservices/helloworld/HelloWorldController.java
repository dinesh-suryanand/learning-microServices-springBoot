package in.dinesh.restful.restfulservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping(path = "/hello")
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-bean")
    public HelloBean helloBean(){
        return new HelloBean("Hello World");
    }

    @GetMapping(path = "/hello-bean/path/{name}")
    public HelloBean helloBeanName(@PathVariable String name){
//        return new HelloBean("Hello World " + name);
        return new HelloBean(String.format("Hello World , %s",name));
    }

    @GetMapping(path = "/hello/{name}")
    public String sayHelloName(@PathVariable String name){
        return "Hello World " + name;
    }
}
