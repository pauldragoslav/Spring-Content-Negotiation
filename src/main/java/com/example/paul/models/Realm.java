package com.example.paul.models;

import javax.persistence.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Entity
@Table(name = "user_realm")
@SequenceGenerator(name = "user_realm_seq", sequenceName = "user_realm_sequence")
public class Realm {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_realm_seq")
    @JacksonXmlProperty(isAttribute = true)
    private int id;

    @Column(unique = true)
    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(length = 32)
    private String key;

    public Realm() {}
    public Realm(int id, String name, String description, String key) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.key = key;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Realm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
