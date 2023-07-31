package com.quachhengtony.tms.todoservice.controller;

//import com.quachhengtony.tms.todoservice.client.SubscriberClient;
import com.quachhengtony.tms.todoservice.dto.TodoItemDto;
import com.quachhengtony.tms.todoservice.service.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(path = "/api/todoitems")
public class TodoItemsController {

    @Autowired
    private TodoItemService todoItemService;

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private SubscriberClient subscriberClient;

    @GetMapping
    public ResponseEntity<List<TodoItemDto>> getAll() {
        try {
            String response = restTemplate.getForObject("http://subscriber-service/api/subscribers/", String.class);
            System.out.println(response);
            if (!response.equalsIgnoreCase("subscribed")) {
                return ResponseEntity.badRequest().build();
            } else {
                return new ResponseEntity<>(todoItemService.getAll(), HttpStatus.OK);
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoItemDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(todoItemService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TodoItemDto> create(@RequestBody TodoItemDto dto) {
        TodoItemDto todoItemDto = todoItemService.create(dto);
        return new ResponseEntity<>(todoItemDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody TodoItemDto dto) {
        todoItemService.update(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        todoItemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
