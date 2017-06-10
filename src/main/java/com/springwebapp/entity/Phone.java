package com.springwebapp.entity;

import javax.persistence.*;

/**
 * Created by prateek on 21/12/16.
 */
@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Phone() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
