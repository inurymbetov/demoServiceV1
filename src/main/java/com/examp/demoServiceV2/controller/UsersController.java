package com.examp.demoServiceV2.controller;

import com.examp.demoServiceV2.dto.UsersDto;
import com.examp.demoServiceV2.service.ServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {

    private final ServiceInterface<UsersDto> serviceInterface;

    @GetMapping()
    private ResponseEntity<List<UsersDto>> getAll() {
        return ResponseEntity.ok(serviceInterface.getAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<UsersDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(serviceInterface.getById(id));
    }

    @PostMapping("/create")
    private ResponseEntity<UsersDto> create(@RequestBody UsersDto dto) {
        UsersDto body = serviceInterface.create(dto);
        URI uri = UriComponentsBuilder.fromPath("/api/v1/users/{id}").buildAndExpand(body.getId()).toUri();
        return ResponseEntity.created(uri).body(body);
    }

    @PutMapping("/update")
    private ResponseEntity<UsersDto> update(@RequestBody UsersDto dto) {
        return ResponseEntity.ok(serviceInterface.update(dto));
    }

}
