package kachow.api_invocations.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import kachow.api_invocations.dto.TauxMonstreDTO;

public interface MonsterInvocRepository extends ReactiveMongoRepository<TauxMonstreDTO, String> {
}