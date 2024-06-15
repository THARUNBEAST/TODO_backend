package com.todo.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://todo-app-i8l3.onrender.com")
@RestController
@RequestMapping("/")

public class Controller {

    @Autowired
    private Repo rep;

    @GetMapping
    public List<Pojo> getAll() {
        return rep.findAll();
    }

    @PostMapping
    public List<Pojo> addData(@RequestBody Pojo obj) {
        rep.save(obj);
        return rep.findAll();
    }

    @PutMapping("/{id}")
    public List<Pojo> updateData(@PathVariable String id, @RequestBody Pojo obj) {
        Pojo existingPojo = rep.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        existingPojo.setTodo(obj.getTodo());
        rep.save(existingPojo);
        return rep.findAll();
    }

    @DeleteMapping("/{id}")
    public List<Pojo> deleteData(@PathVariable String id) {
        rep.deleteById(id);
        return rep.findAll();
    }
}
