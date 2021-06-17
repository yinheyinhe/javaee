package com.springboot.jpa_demo.datasource1.repository;

import com.springboot.jpa_demo.datasource1.domain.Consumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerRepository extends JpaRepository<Consumer,Integer> {
    Consumer saveAndFlush(Consumer consumer);
    Consumer getOne(Integer trainId);
}
