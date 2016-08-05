package com.sombra.shop.category.controller;

import com.sombra.shop.category.domain.Category;
import com.sombra.shop.category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sombra.shop.role.Role.ROLE_ADMIN;

@RestController
@RequestMapping( value = "/categories" )
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger( CategoryController.class );

    private final CategoryService categoryService;

    public CategoryController( CategoryService categoryService ) {
        Assert.notNull( categoryService, "categoryService must not be null" );
        this.categoryService = categoryService;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>( categoryService.getAll(), HttpStatus.OK );
    }

    @RequestMapping( method = RequestMethod.POST )
    @Secured( value = { ROLE_ADMIN } )
    public ResponseEntity<Void> addCategory( @RequestBody Category category ) {
        categoryService.add( category );
        return new ResponseEntity<>( HttpStatus.CREATED );
    }

    @RequestMapping( method = RequestMethod.PUT )
    @Secured( value = { ROLE_ADMIN } )
    public ResponseEntity<Void> editCategory( @RequestBody Category category ) {
        categoryService.update( category );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping( method = RequestMethod.DELETE )
    @Secured( value = { ROLE_ADMIN } )
    public ResponseEntity<Void> deleteCategory( @RequestParam( "id" ) Long id ) {
        categoryService.delete( id );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Category> getOne( @PathVariable( "id" ) Long id ) {
        return new ResponseEntity<>( categoryService.getOne( id ), HttpStatus.OK );
    }

}
