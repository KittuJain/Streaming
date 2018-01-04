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

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "100"))
    @Query(value = "select * from sample", nativeQuery = true)
    Stream<Sample> getAll();

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "100"))
    @Query(value = "select * from sample FETCH NEXT 10000 ROWS ONLY ", nativeQuery = true)
    Stream<Sample> getFirst10000();

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "100"))
    @Query(value = "select * from sample FETCH NEXT 100000 ROWS ONLY ", nativeQuery = true)
    Stream<Sample> getFirst1L();

    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "100"))
    List<Sample> findAll();

    @Query(value = "select * from sample fetch next 10000 rows only", nativeQuery = true)
    List<Sample> getFirst10000WithoutStreaming();

}