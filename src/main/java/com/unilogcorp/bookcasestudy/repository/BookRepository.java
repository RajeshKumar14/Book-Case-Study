package com.unilogcorp.bookcasestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.unilogcorp.bookcasestudy.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query ("update Book b set b.bookName =:bookName,b.authors=:authors  where b.belongsTo=:username")
    public void updateBookByUsername(@Param ("bookName") String bookName, @Param ("authors") String authors, @Param ("username") String username);
}
