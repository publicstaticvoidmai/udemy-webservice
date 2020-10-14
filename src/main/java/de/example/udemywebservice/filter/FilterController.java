package de.example.udemywebservice.filter;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import de.example.udemywebservice.model.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


//dynamic filtering
@RestController
public class FilterController {

    @GetMapping("/filtering")
    public MappingJacksonValue getSomeBean(){

        SomeBean someBean = new SomeBean("hello", "my", "friend");

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
        //only field1 in response

        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleBeanPropertyFilter);
        //filter id => bean @JsonFilter("filter id")

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filters);

        return mapping;
    }
}
