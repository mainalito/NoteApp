package com.tutorial.demo.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.tutorial.demo.Repository.Repository;
import com.tutorial.demo.models.Notebook;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
public class Services {
    
    private static final Notebook NotFoundException = null;
    @Autowired
    private Repository repo;

    public void addNote( Notebook note){
        repo.save(note);
    }
    public void deleteNote(Long id){
     repo.deleteById(id);
    }
    public Notebook updateNote(Long id, Notebook note){
        Optional<Notebook> notebook = repo.findById(id);
        if(notebook.isPresent()){
            Notebook _notebook =  notebook.get();
            _notebook.setTitle(note.getTitle());
            _notebook.setDescription(note.getDescription());
            return repo.save(_notebook);
        }
        else{
            return NotFoundException;
        }
        
    }
    public List<Notebook> getAllnotes(String title){
        try{
            List<Notebook> notes = new ArrayList<>();
            if(title == null){
                repo.findAll().forEach(notes::add);
            }
            else{
                repo.findByTitleContaining(title).forEach(notes::add);
            }
            if(notes.isEmpty()){
                return null;
            }
            return notes;
        }catch(Exception e){
            return null;

        }
        
    }
    public List<Notebook> getAllnotes(){
            List<Notebook> notes = new ArrayList<>();
    
                repo.findAll().forEach(notes::add);

        
            return notes;
        
            
        
    }
}
