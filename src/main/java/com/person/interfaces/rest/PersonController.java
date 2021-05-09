package com.person.interfaces.rest;


import com.person.application.payload.mappers.PersonRequestMapper;
import com.person.application.payload.person.PersonRequest;
import com.person.domain.entities.Person;
import com.person.domain.services.PersonService;
import com.person.interfaces.dtos.PersonDto;
import com.person.interfaces.dtos.ResponseDto;
import com.person.interfaces.mappers.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/persons")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping
    public Page<PersonDto> list(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                @RequestParam(value = "size", defaultValue = "5", required = false) Integer size){

        var pageResult = service.list(PageRequest.of(page, size));
        return pageResult.map(PersonMapper::toDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> get(@PathVariable UUID id){

        Person person = service.get(id);

        return ResponseEntity.ok(PersonMapper.toDto(person));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody PersonRequest personRequest){

        Person person = service.save(PersonRequestMapper.map(personRequest));

        return new ResponseEntity<>(PersonMapper.toDto(person), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable UUID id,@Valid @RequestBody PersonRequest personRequest){

        Person person = service.update(id, PersonRequestMapper.map(personRequest));

        return new ResponseEntity<>(service.update(id, person), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable UUID id){

        service.delete(id);

        return new ResponseEntity(ResponseDto.builder().message("Pessoa removida com sucesso"), HttpStatus.NO_CONTENT);
    }
}

