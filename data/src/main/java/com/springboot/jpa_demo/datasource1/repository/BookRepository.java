package com.springboot.jpa_demo.datasource1.repository;

import com.alibaba.fastjson.JSONObject;
import com.springboot.jpa_demo.datasource1.domain.Book;
import org.hibernate.mapping.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import javax.persistence.LockModeType;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Lock(LockModeType.READ)
    Book findById(int id);
    List<Book> findByIdOrName(String id, String name);
    List<Book> findByNameLike(@Nullable String name);
    List<Book> findByNameContaining(String name);
    List<Book> findByNameIn(Collection name);

    Page<Book> findAll(Pageable pageable);
    Book saveAndFlush(Book book);
    Book getOne(Integer gymId);

    @Query(nativeQuery = true,value = "select gym.id as gymId ,gym.name as gymName, trainer.id as trainerId,trainer.name as trainerName " +
            "from gym " +
            "left join gym_trainer on gym_trainer.gym_id=gym.id " +
            "left join trainer on trainer.id=gym_trainer.trainer_id " +
            "where gym.id=?1")
    List<JSONObject> getGymTrainer(String id);


}
