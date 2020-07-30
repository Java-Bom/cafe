package com.javabom.cafe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CafeTable {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    protected CafeTable() {
    }

    public CafeTable(final String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
