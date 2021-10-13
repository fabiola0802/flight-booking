package com.falterziu.flightdata.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Getter
@Setter
@ToString
@Entity
@Table(name="class")
public class ClassEntity extends BaseEntity {

    @Column
    private String name;


}
