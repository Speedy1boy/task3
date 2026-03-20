package ru.vladislav.JavaNaumen.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cinemas") // Кинотеатры
public class Cinema {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
