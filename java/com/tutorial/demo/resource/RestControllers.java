package com.tutorial.demo.resource;



import java.util.List;

import com.tutorial.demo.Repository.Repository;
import com.tutorial.demo.models.Notebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//REST-API 

@RestController
@RequestMapping("/api/v1")
public class RestControllers {
   
    @Autowired 
    Services service;
    
    @GetMapping("/notebook")
    public ResponseEntity<List<Notebook>> getAllNotes(@RequestParam(required = false) String title){
        return new ResponseEntity<>(service.getAllnotes(title), HttpStatus.OK);
    }
    @PostMapping("/notebooks")
    public ResponseEntity<Notebook> addNote(@RequestBody Notebook note){
        try{
            service.addNote(note);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/notebook/{id}")
public ResponseEntity<Notebook> updateNotes(@PathVariable("id") Long id, @RequestBody Notebook note){
        return new ResponseEntity<>(service.updateNote(id, note),HttpStatus.OK);
    }
    @DeleteMapping("/notebook/{id}")
    public ResponseEntity<HttpStatus> deleteNotebook(@PathVariable("id") Long id){
        service.deleteNote(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
    @GetMapping("/")
     public String hello(){
            return "hello spring";
        }
    
    
}

