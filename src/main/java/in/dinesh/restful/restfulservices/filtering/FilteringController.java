package in.dinesh.restful.restfulservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    //filtering done in SomeBean class i.e addeing @JSONProperty to edit the fields
//    @GetMapping("/filtering")
//    public SomeBean filtering(){
//        return new SomeBean("value1","value2","value3");
//    }



    //Dynamic filtering
    @GetMapping("/filtering")
    public MappingJacksonValue filtering(){

        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeamFilter",filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

//    @GetMapping("/filtering-list")
//    public List<SomeBean> filteringList(){
//        return Arrays.asList(new SomeBean("value1","value2","value3") ,new SomeBean("value7","value4","value69"));
//    }

    //Dynamic Filering of above one
    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList(){
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value7", "value4", "value69"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeamFilter",filter);
        mappingJacksonValue.setFilters(filters);

        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
