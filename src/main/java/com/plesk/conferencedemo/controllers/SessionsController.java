package com.plesk.conferencedemo.controllers;

import com.plesk.conferencedemo.models.Session;
import com.plesk.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")              //all request to this URL will be sent to this controller (tells router what URL will look like)
public class SessionsController {
    @Autowired //injects/autowires Session repository when SessionController is built, it will create instance of session repo and put it onto our class
    private SessionRepository sessionRepository;

    @GetMapping  //call to /api/v1/sessions will route to this method; @GetMapping tells which HTTP verb to use; it will be GET HTTP verb to call this endpoint.
    public List<Session> list() {                //it queries all sessions in DB and returns them as list of Session objects
        return sessionRepository.findAll();      //Then Spring MVC, will pass that over to Jackson(serializ library) turning those sessions into JSON and return them to caller.
    }

    @GetMapping
    @RequestMapping("{id}") //this mapping is addition to class @Request mapping (l.11), adding additional id to the URL.//id specifies specific session, which we want to return
    public Session get(@PathVariable Long id) {  //parameter pulls id from URL, injecting it into our method automatically. this is handled for by MVC,
        return sessionRepository.getOne(id);     //it returns and queries specific session for ID back to caller in JSON payload.
    }

    @PostMapping //POST for API call, here we are posting to the base part of the class (/api/v1/..), so we dont use REQUEST mapping
    public Session create(@RequestBody final Session session) { // Spring MVC takes all attributes in JSON payload & autom marshalling them into session object
        return sessionRepository.saveAndFlush(session);         //then we pass it to sessionRep but Iits not added to DB until its flushed
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT) //it will take all attributes on the request body for that session and replace them all on the DB record. If not all atrib are passed in, those atrib will be updated with null.// If only some attributes needs to be updated use PATCH instead of PUT.
    public Session update(@PathVariable Long id, @RequestBody Session session) {     //because its PUT, we expect all atrib to be passed in. We can also check if all atrib are there, otherwise can return 400
        Session existingSession = sessionRepository.getOne(id);   //first we need to find existing record by id from the URL param
        BeanUtils.copyProperties(session, existingSession, "session_id");  //will take existing session and copies incoming session data onto it.
        //give 3rd parameter, which tells that it needs to be ignored because its the primary key, otherwise will copy null value, when trying to update it, will cause exception
        return sessionRepository.saveAndFlush(existingSession);   //will add to DB and flush it
    }
}
















//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Session create(@RequestBody final Session session) {
//        return sessionRepository.saveAndFlush(session);
//    }
