package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @Column(name = "qt_id")
    private Integer id;

    @Column(name = "qt_text")
    private String text;

    @Column(name = "qt_score")
    private double score;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private Set<OptionItem> options = new HashSet<OptionItem>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Set<OptionItem> getOptions() {
        return options;
    }

    public void setOptions(Set<OptionItem> options) {
        this.options = options;
    }
}
