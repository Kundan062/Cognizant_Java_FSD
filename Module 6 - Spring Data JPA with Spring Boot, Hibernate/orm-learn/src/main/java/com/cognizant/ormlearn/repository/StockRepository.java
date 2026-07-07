package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByCodeAndDateBetween(String code, LocalDate startDate, LocalDate endDate);

    List<Stock> findByCodeAndCloseGreaterThan(String code, double close);

    List<Stock> findTop3ByOrderByVolumeDesc();
}
