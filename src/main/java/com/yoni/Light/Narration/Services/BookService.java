package com.yoni.Light.Narration.Services;

import com.yoni.Light.Narration.Client.OpenLibClient;
import com.yoni.Light.Narration.Exceptions.BookNotFoundException;
import com.yoni.Light.Narration.Models.Book;
import com.yoni.Light.Narration.Repos.BookRepo;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo){
        this.bookRepo = bookRepo;
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Book findById(Long id) {
        return bookRepo.findById(id).orElseThrow(()-> new BookNotFoundException(id));
    }
}
