package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Country findCountryByCode(String code) {
        return countryRepository.findById(code).orElseThrow(() -> new CountryNotFoundException(code));
    }

    @Transactional
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    @Transactional
    public Country updateCountry(String code, String name) {
        Country country = findCountryByCode(code);
        country.setName(name);
        return countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String code) {
        countryRepository.delete(findCountryByCode(code));
    }

    @Transactional(readOnly = true)
    public List<Country> findCountriesByPartialName(String partialName) {
        return countryRepository.findByNameContainingIgnoreCase(partialName);
    }
}
