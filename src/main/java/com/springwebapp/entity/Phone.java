package com.springwebapp.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by prateek on 21/12/16.
 */
@Entity
@Table(name = "phone")
@Getter @Setter
@NoArgsConstructor
public class Phone {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
