package pe.joedayz.restapis.controllers;


import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import pe.joedayz.restapis.domains.TodoType;
import pe.joedayz.restapis.services.TodoTypeService;

@RestController  // Combina @Controller y @ResponseBody
@RequestMapping("/api/todoType")  // Mapea los web requests a /api/todoType
public class TodoTypeController {


  private TodoTypeService todoTypeService;


  @Autowired
  public TodoTypeController(TodoTypeService todoTypeService) {
    this.todoTypeService = todoTypeService;
  }

  @GetMapping("/hello")  // Solo maneja peticiones GET
  public String hello() {
    return "Hello World from Spring Boot !";
  }

  @PostMapping(consumes={"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public TodoType create(@RequestBody @Valid TodoType todoType) {
    return todoTypeService.create(todoType);
  }

  @GetMapping(value = "/{code}", produces = {"application/xml", "application/json"})
  public ResponseEntity<TodoType> read(@PathVariable("code") String code) {
    TodoType todoType = todoTypeService.findByCode(code);
    if(null != todoType) {
      return new ResponseEntity<>(todoType, HttpStatus.OK);
    }else{
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @PutMapping
  public TodoType updateTodo(@RequestBody TodoType todoType) {
    return todoTypeService.update(todoType);
  }

  @DeleteMapping("/{code}")
  public ResponseEntity delete(@PathVariable("code") String code) {
    try {
      todoTypeService.deleteByCode(code);
      return new ResponseEntity(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping()
  public List<TodoType> finaAll(@RequestParam  String sort, @RequestParam String order,
      @RequestParam int pageNumber, @RequestParam int numOfRecords) {
    return todoTypeService.findAll(sort, Sort.Direction.fromString(order), pageNumber, numOfRecords);
  }


}
