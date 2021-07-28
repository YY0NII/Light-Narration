package com.yoni.Light.Narration.Controllers;

import com.yoni.Light.Narration.Models.Book;
import com.yoni.Light.Narration.Services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/LightNovelsAPI")
public class BookController {
    BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    //GET ALL LIGHT NOVELS
    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookService.findAll();
    }

    //GET LIGHT NOVEL BY ID
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookService.findById(id);
    }
}
