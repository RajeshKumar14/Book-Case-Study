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
@Table (name = "book_master")
public class Book {
    @Id
    @GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "belongs_to")
    private String belongsTo;

    @Column (name = "book_name")
    private String bookName;

    @Column (name = "authors")
    private String authors;

    @Column (name = "created_at")
    private Timestamp created_at;

    @Column (name = "updated_at")
    private Timestamp updated_at;
}
