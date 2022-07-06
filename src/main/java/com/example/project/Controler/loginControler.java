package com.example.project.Controler;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Repository.LoginRepository;
import com.example.project.model.login;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("api/login")
public class loginControler {
    @Autowired
    private LoginRepository loginRepository;



    @PostMapping("/")
    public login addlogin(@RequestBody login login){
        return loginRepository.save(login);
    }
    @GetMapping("/")
    public List<login> getAll(){
        return loginRepository.findAll();
    }
    @PutMapping("/{id}")
    public ResponseEntity<login> updateLogin(@PathVariable long id, @RequestBody login login) throws RelationNotFoundException
    {
        login log = loginRepository.findById(id)
        .orElseThrow(() -> new RelationNotFoundException("Invalid Id"));
        log.setUser_name(login.getUser_name());
        log.setPassword(login.getPassword());
        login log1 = loginRepository.save(log);
        return ResponseEntity.ok(log1);
    }

    //get staff by id
    @GetMapping("/{id}")
    public ResponseEntity<login> getLoginById(@PathVariable long id) throws RelationNotFoundException
    {
        login log = loginRepository.findById(id)
        .orElseThrow(() -> new RelationNotFoundException("Invalid Id"));
        return ResponseEntity.ok(log);
    }

    //delete staff
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteStaff(@PathVariable long id) throws RelationNotFoundException
    {
        login log = loginRepository.findById(id)
        .orElseThrow(() -> new RelationNotFoundException("Invalid Id"));
        loginRepository.delete(log);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
   private static final long serialVersionUID=1L;
    public ResourceNotFoundException(String msg)
   {
       super(msg);
   } 
}
    
}
