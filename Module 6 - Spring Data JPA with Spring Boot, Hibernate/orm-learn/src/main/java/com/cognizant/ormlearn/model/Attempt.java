package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "attempt")
public class Attempt {
    @Id
    @Column(name = "at_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "at_us_id")
    private QuizUser user;

    @Column(name = "at_date")
    private LocalDate attemptDate;

    @OneToMany(mappedBy = "attempt", fetch = FetchType.LAZY)
    private Set<AttemptQuestion> attemptQuestions = new HashSet<AttemptQuestion>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QuizUser getUser() {
        return user;
    }

    public void setUser(QuizUser user) {
        this.user = user;
    }

    public LocalDate getAttemptDate() {
        return attemptDate;
    }

    public void setAttemptDate(LocalDate attemptDate) {
        this.attemptDate = attemptDate;
    }

    public Set<AttemptQuestion> getAttemptQuestions() {
        return attemptQuestions;
    }

    public void setAttemptQuestions(Set<AttemptQuestion> attemptQuestions) {
        this.attemptQuestions = attemptQuestions;
    }
}
