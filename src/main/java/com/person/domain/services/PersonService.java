package com.person.domain.services;

import com.person.application.exception.BusinessException;
import com.person.application.exception.GenericException;
import com.person.application.exception.NotFoundException;
import com.person.domain.entities.Person;
import com.person.domain.repositorys.PersonRepository;
import com.person.interfaces.mappers.PersonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    public Page<Person> list(PageRequest pageRequest) throws GenericException {
        try{
            return repository.findAll(pageRequest);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException("Houve um erro ao listar os registros");
        }
    }

    public Person save(Person person){
        try {
            return repository.save(person);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException("Houve um erro ao salvar o registro");
        }
    }

    public Person get(UUID uuid){
        try {
            return repository
                    .findById(uuid)
                    .orElseThrow(() -> new NotFoundException("Registro não localizado"));
        } catch (NotFoundException s) {
            log.warn("O registro com id [{}] não foi localizado", uuid);
            throw s;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException("Houve um erro ao recuperar o registro");
        }
    }

    public void delete(UUID uuid) {
        try {
            repository.findById(uuid)
                    .ifPresent(
                            product -> repository.delete(product));
       } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException("Houve um erro ao remover o registro");
        }

    }

    public Person update(UUID id, Person person){
        try {
            Optional<Person> personOptional = repository.findById(id);

            if (personOptional.isPresent()) {
                var personUpdated = personOptional.get();
                personUpdated.setPhones(person.getPhones());
                personUpdated.setBirth(person.getBirth());
                personUpdated.setName(person.getName());

                return repository.save(personUpdated);
            }

            throw new NotFoundException("Registro não encontrado!");

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new GenericException("Houve um erro ao salvar o produto");
        }
    }
}
