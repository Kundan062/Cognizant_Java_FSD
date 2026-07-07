package com.cognizant.springlearn;

import com.cognizant.springlearn.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringLearnApplication implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
        LOGGER.info("Inside main");
    }

    @Override
    public void run(String... args) throws Exception {
        displayDate();
        displayCountry();
        demonstrateScopes();
        displayCountries();
    }

    private void displayDate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat dateFormat = context.getBean("dateFormat", SimpleDateFormat.class);
        LOGGER.debug("Formatted date: {}", dateFormat.format(new Date()));
    }

    private void displayCountry() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        Country country = context.getBean("countryIN", Country.class);
        LOGGER.debug("country={}", country);
    }

    private void demonstrateScopes() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        LOGGER.debug("Singleton same instance: {}", context.getBean("countryIN") == context.getBean("countryIN"));
        LOGGER.debug("Prototype same instance: {}", context.getBean("countryUS") == context.getBean("countryUS"));
    }

    @SuppressWarnings("unchecked")
    private void displayCountries() {
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        List<Country> countries = context.getBean("countryList", List.class);
        LOGGER.debug("countries={}", countries);
    }
}
