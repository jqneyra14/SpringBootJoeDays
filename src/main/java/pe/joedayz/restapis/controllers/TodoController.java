package pe.joedayz.restapis.controllers;

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
import org.springframework.web.bind.annotation.RestController;
import pe.joedayz.restapis.domains.Todo;
import pe.joedayz.restapis.services.TodoService;
import pe.joedayz.restapis.services.TodoTypeService;
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private TodoService todoService;
    private TodoTypeService todoTypeService;

    @Autowired
    public TodoController(TodoService todoService, TodoTypeService todoTypeService) {
        this.todoService = todoService;
        this.todoTypeService = todoTypeService;
    }

    @PostMapping
    public Todo create(@RequestBody  Todo todo) {
        return todoService.create(todo);
    }

    @GetMapping("/{id}")
    public Todo read(@PathVariable("id") Long id) {
        return todoService.findById(id);
    }

    @PutMapping
    public Todo update(@RequestBody Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try{
            todoService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}