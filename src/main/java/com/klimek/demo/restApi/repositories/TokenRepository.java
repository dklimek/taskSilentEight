package com.klimek.demo.restApi.repositories;

import com.klimek.demo.restApi.entities.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "tokens", path = "tokens")
public interface TokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findTopByName(String name);
}
