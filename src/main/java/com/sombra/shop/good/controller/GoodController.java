package com.sombra.shop.good.controller;

import com.sombra.shop.good.domain.Good;
import com.sombra.shop.good.service.GoodService;
import com.sombra.shop.picture.domain.Picture;
import com.sombra.shop.picture.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.sombra.shop.role.Role.ROLE_ADMIN;

@RestController
@RequestMapping( value = "/goods" )
public class GoodController {

    private final GoodService goodService;

    private final PictureService pictureService;

    @Autowired
    public GoodController( GoodService goodService, PictureService pictureService ) {
        Assert.notNull( goodService, "goodService must not be null" );
        Assert.notNull( pictureService, "pictureService must not be null" );
        this.pictureService = pictureService;
        this.goodService = goodService;
    }

    @RequestMapping( method = RequestMethod.GET )
    public ResponseEntity<List<Good>> getAll() {
        return new ResponseEntity<>( goodService.getAll(), HttpStatus.OK );
    }

    @RequestMapping( method = RequestMethod.POST )
    @Secured( value = { ROLE_ADMIN } )
    public ResponseEntity<Void> addGood( @RequestBody Good good ) {
        goodService.add( good );
        return new ResponseEntity<>( HttpStatus.CREATED );
    }

    @RequestMapping( method = RequestMethod.PUT )
    @Secured( value = { ROLE_ADMIN } )
    public ResponseEntity<Void> editGood( @RequestBody Good good ) {
        goodService.update( good );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping( method = RequestMethod.DELETE )
    @Secured( value = { ROLE_ADMIN } )
    public ResponseEntity<Void> deleteGood( @RequestParam( "id" ) Long id ) {
        goodService.delete( id );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(
            value = "/pictures",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Picture>> getAllPictures( @RequestParam( "id" ) Long id ) {
        return new ResponseEntity<>( pictureService.getAllByGoodId( id ), HttpStatus.OK );
    }

    @RequestMapping(
            value = "/pictures",
            method = RequestMethod.POST
    )
    @Secured( value = { ROLE_ADMIN } )
    public ResponseEntity<Void> addPicture( @RequestParam( "id" ) Long id,
                                            @RequestParam( "file" ) MultipartFile picture )
            throws IOException {

        Good good = goodService.getOne( id );
        pictureService.add( good, picture );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(
            value = "/pictures",
            method = RequestMethod.DELETE
    )
    @Secured( value = { ROLE_ADMIN } )
    public ResponseEntity<Void> deletePicture( @RequestParam( "id" ) Long pictureId ) {
        pictureService.delete( pictureId );
        return new ResponseEntity<>( HttpStatus.OK );
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<Good> getOne( @PathVariable( "id" ) Long id ) {
        Good good = goodService.getOne( id );
        return new ResponseEntity<>( good, HttpStatus.OK );
    }

}
