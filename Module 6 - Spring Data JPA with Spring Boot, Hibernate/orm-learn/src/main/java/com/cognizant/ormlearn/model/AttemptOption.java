package com.cognizant.ormlearn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attempt_option")
public class AttemptOption {
    @Id
    @Column(name = "ao_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ao_aq_id")
    private AttemptQuestion attemptQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ao_op_id")
    private OptionItem option;

    @Column(name = "ao_selected")
    private boolean selected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AttemptQuestion getAttemptQuestion() {
        return attemptQuestion;
    }

    public void setAttemptQuestion(AttemptQuestion attemptQuestion) {
        this.attemptQuestion = attemptQuestion;
    }

    public OptionItem getOption() {
        return option;
    }

    public void setOption(OptionItem option) {
        this.option = option;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
