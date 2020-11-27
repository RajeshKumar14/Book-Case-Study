package com.unilogcorp.bookcasestudy.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "user_master")
public class User {
    @Id
    @GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
    @Column (name = "user_id")
    private long id;

    @Column (name = "username")
    private String username;

    @Column (name = "password")
    private String password;

    @Column (name = "role")
    private String role;

    @Column (name = "enabled")
    private boolean enabled;

    @Column (name = "created_at")
    private Timestamp created_at;

    @Column (name = "updated_at")
    private Timestamp updated_at;
}
