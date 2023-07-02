package com.example.webfluxstart.group;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface GroupRepository extends ReactiveCrudRepository<Group, Long> {
}
