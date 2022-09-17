package in.dinesh.restful.restfulservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

//    @Autowired
    private MessageSource messageSource;
    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }
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

    @GetMapping(path = "/hello-i18n")
    public String sayHelloInternationalization(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);
//        return "Hello World Internationalization";
    }
}
