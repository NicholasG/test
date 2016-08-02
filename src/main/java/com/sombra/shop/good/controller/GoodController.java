package com.sombra.shop.good.controller;

import com.sombra.shop.good.dao.GoodDAO;
import com.sombra.shop.good.domain.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( value = "/goods" )
public class GoodController {

    private final GoodDAO dao;

    @Autowired
    public GoodController( GoodDAO dao ) {
        this.dao = dao;
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Good> getOne( @PathVariable( "id" ) Long id ) {
        Good good = dao.findOneById( id );
        return new ResponseEntity<Good>( good, HttpStatus.OK );
    }

}
