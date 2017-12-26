package com.example.springbootoracle.Repositories;

import com.example.springbootoracle.Models.Sample;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;


@Repository
public interface SampleRepository extends CrudRepository<Sample, Long> {

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "1000"))
    @Query(value = "select * from sample", nativeQuery = true)
    Stream<Sample> getAll();


    List<Sample> findAll();

}