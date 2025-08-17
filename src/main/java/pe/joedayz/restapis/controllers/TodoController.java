package pe.joedayz.restapis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.joedayz.restapis.domains.Todo;
import pe.joedayz.restapis.services.TodoService;
import pe.joedayz.restapis.services.TodoTypeService;

import java.util.List;

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

    @GetMapping()
    public List<Todo> findAll(@RequestParam String sort, @RequestParam String order, @RequestParam int pageNumber, @RequestParam int numOfRecords) {
        return todoService.findAll(sort, Sort.Direction.fromString(order), pageNumber, numOfRecords);
    }
}