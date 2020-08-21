package org.cbo.testcontainers.data;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "myuser")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
}
