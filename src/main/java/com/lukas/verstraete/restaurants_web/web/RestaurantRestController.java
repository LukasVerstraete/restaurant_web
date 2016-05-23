package com.lukas.verstraete.restaurants_web.web;

import com.lukas.verstraete.restaurants_domain.domain.Restaurant;
import com.lukas.verstraete.restaurants_domain.service.RestaurantFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/restaurant")
public class RestaurantRestController
{
    @Autowired
    private RestaurantFacade services;
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody Restaurant restaurant)
    {
        services.addRestaurant(restaurant);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable long id )
    {
        return services.getRestaurant(id);
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll(){
        return services.getAllRestaurants();
    }
}