package com.cognizant.springlearn.dao;
import com.cognizant.springlearn.model.Country; import org.springframework.stereotype.Repository; import java.util.ArrayList; import java.util.List;
@Repository public class CountryDao { private static final List<Country> COUNTRIES = new ArrayList<>(); static { COUNTRIES.add(new Country("IN","India")); COUNTRIES.add(new Country("US","United States")); } public List<Country> getAll(){return COUNTRIES;} public Country add(Country country){ COUNTRIES.add(country); return country; } }
