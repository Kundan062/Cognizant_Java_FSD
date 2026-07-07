package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Attempt;
import com.cognizant.ormlearn.model.AttemptQuestion;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.OptionItem;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.service.AttemptService;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        CountryService countryService = context.getBean(CountryService.class);
        EmployeeService employeeService = context.getBean(EmployeeService.class);
        DepartmentService departmentService = context.getBean(DepartmentService.class);
        SkillService skillService = context.getBean(SkillService.class);
        StockService stockService = context.getBean(StockService.class);
        AttemptService attemptService = context.getBean(AttemptService.class);

        testCountryServices(countryService);
        testStockQueries(stockService);
        testEmployeeQueries(employeeService, departmentService, skillService);
        testAttemptDetails(attemptService);
    }

    private static void testCountryServices(CountryService countryService) {
        LOGGER.info("Country hands-on start");
        LOGGER.debug("countries={}", countryService.getAllCountries());
        LOGGER.debug("country IN={}", countryService.findCountryByCode("IN"));
        countryService.addCountry(new Country("SG", "Singapore"));
        countryService.updateCountry("SG", "Republic of Singapore");
        LOGGER.debug("countries containing 'United'={}", countryService.findCountriesByPartialName("United"));
        countryService.deleteCountry("SG");
        LOGGER.info("Country hands-on end");
    }

    private static void testStockQueries(StockService stockService) {
        LOGGER.info("Stock query method hands-on start");
        LOGGER.debug("Facebook September 2019 stocks:{}", stockService.getStocksByCodeAndMonth("FB", 2019, 9));
        LOGGER.debug("Google stocks over 1250:{}", stockService.getStocksByCodeAndCloseGreaterThan("GOOGL", 1250.00));
        LOGGER.debug("Top 3 transaction volumes:{}", stockService.getTop3ByVolume());
        LOGGER.info("Stock query method hands-on end");
    }

    private static void testEmployeeQueries(EmployeeService employeeService,
                                            DepartmentService departmentService,
                                            SkillService skillService) {
        LOGGER.info("Employee mapping and query hands-on start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.debug("Department with employees:{}", departmentService.get(1).getEmployeeList());

        Skill skill = skillService.get(4);
        employee.getSkillList().add(skill);
        employeeService.save(employee);
        LOGGER.debug("Employee after adding skill:{}", employeeService.get(1).getSkillList());

        List<Employee> permanentEmployees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", permanentEmployees);
        permanentEmployees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));

        LOGGER.debug("Average salary for department 1:{}", employeeService.getAverageSalary(1));
        LOGGER.debug("All employees native query:{}", employeeService.getAllEmployeesNative());
        LOGGER.info("Employee mapping and query hands-on end");
    }

    private static void testAttemptDetails(AttemptService attemptService) {
        LOGGER.info("Quiz attempt HQL hands-on start");
        Attempt attempt = attemptService.getAttempt(1, 1);
        LOGGER.debug("Attempt user={} date={}", attempt.getUser().getName(), attempt.getAttemptDate());
        for (AttemptQuestion attemptQuestion : attempt.getAttemptQuestions()) {
            LOGGER.debug(attemptQuestion.getQuestion().getText());
            for (OptionItem option : attemptQuestion.getQuestion().getOptions()) {
                boolean selected = attemptQuestion.getAttemptOptions().stream()
                        .anyMatch(attemptOption -> attemptOption.getOption().getId().equals(option.getId())
                                && attemptOption.isSelected());
                LOGGER.debug("{} {} {}", option.getText(), option.isCorrect() ? attemptQuestion.getQuestion().getScore() : 0.0, selected);
            }
        }
        LOGGER.info("Quiz attempt HQL hands-on end");
    }
}
