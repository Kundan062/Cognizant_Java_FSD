package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {
    @Query("select distinct a from Attempt a " +
            "join fetch a.user u " +
            "join fetch a.attemptQuestions aq " +
            "join fetch aq.question q " +
            "join fetch q.options " +
            "join fetch aq.attemptOptions ao " +
            "join fetch ao.option " +
            "where u.id = :userId and a.id = :attemptId")
    Attempt getAttempt(@Param("userId") int userId, @Param("attemptId") int attemptId);
}
