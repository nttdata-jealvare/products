package org.nttdata.entities;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Product extends PanacheEntity {

    private String code;
    private String name;
    private String description;
}
