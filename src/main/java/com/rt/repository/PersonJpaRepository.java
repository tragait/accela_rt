package com.rt.repository;

import com.rt.model.Person;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;

@ComponentScan("com.rt.model")
public interface PersonJpaRepository extends JpaRepository<Person, Integer> {
}
