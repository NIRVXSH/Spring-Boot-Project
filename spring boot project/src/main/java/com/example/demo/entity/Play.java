package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "play")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Play {
    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")   
    private String description;

    @Column(name = "price")
    private String price;

    @Column(name = "json")
    private String json;
}
