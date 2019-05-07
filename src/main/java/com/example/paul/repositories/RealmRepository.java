package com.example.paul.repositories;

import com.example.paul.models.Realm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RealmRepository extends JpaRepository<Realm, Integer> {

    boolean existsByName(String name);
    Optional<Realm> findById(int id);
}
