package com.lukas.verstraete.restaurants_web.web;

import com.lukas.verstraete.restaurants_domain.domain.Restaurant;
import com.lukas.verstraete.restaurants_domain.service.RestaurantFacade;
import com.lukas.verstraete.restaurants_domain.util.ServiceException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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
    
    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create()
    {
        return new Restaurant();
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ObjectError> add(@Validated @RequestBody Restaurant restaurant, BindingResult result)
    {
        if(result.hasErrors())
        {
            for(ObjectError e : result.getAllErrors())
                System.out.println(e.getDefaultMessage());
            return result.getAllErrors();
        }
        try 
        {
            services.addRestaurant(restaurant);
        }
        catch(ServiceException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
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
    
    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ObjectError> update(@Validated @RequestBody Restaurant restaurant, BindingResult result)
    {
        if(result.hasErrors()) {
            for(ObjectError e : result.getAllErrors())
                System.out.println(e.getDefaultMessage());
            return result.getAllErrors();
        }
        try
        {
            services.updateRestaurant(restaurant);
            services.updateAdress(restaurant.getAdress());
        }
        catch(ServiceException e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    @RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
    public void remove(@PathVariable long id)
    {
        try
        {
            services.deleteRestaurant(id);
        }
        catch(ServiceException e)
        {
            System.out.println(e.getMessage());
        }
    }
}