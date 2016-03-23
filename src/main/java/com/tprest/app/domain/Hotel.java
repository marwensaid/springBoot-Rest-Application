package com.tprest.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by marwen on 02/03/16.
 */

@Entity
@Table(name = "hotel")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Hotel {
    @javax.persistence.Id
    @GeneratedValue()
    private long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String description;

    @Column()
    String city;

    @Column()
    private int rating;

    public Hotel() {
    }

    public Hotel(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public long getId() {
        return this.id;
    }

    // for tests ONLY
    public void setId(long id) {
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Hotel {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", rating=" + rating +
                '}';
    }
}
