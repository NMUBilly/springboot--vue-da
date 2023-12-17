package com.B1lly.app.DAO;

import com.B1lly.app.bean.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookDAO {
    List<Book> listBookPrice();
    List<Book> listPage(@Param("page") int page,@Param("size") int size,
                        @Param("bname")String bname);
    int count(@Param("bname") String bname);
    int deleteBook(int bid);
    int addBook(Book book);
    int editBook(Book book);
}

