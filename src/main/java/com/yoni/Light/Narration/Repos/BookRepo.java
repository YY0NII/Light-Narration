package com.yoni.Light.Narration.Repos;

import com.yoni.Light.Narration.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
}
