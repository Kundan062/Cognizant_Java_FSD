package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional(readOnly = true)
    public List<Stock> getStocksByCodeAndMonth(String code, int year, int month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return stockRepository.findByCodeAndDateBetween(code, start, end);
    }

    @Transactional(readOnly = true)
    public List<Stock> getStocksByCodeAndCloseGreaterThan(String code, double close) {
        return stockRepository.findByCodeAndCloseGreaterThan(code, close);
    }

    @Transactional(readOnly = true)
    public List<Stock> getTop3ByVolume() {
        return stockRepository.findTop3ByOrderByVolumeDesc();
    }
}
