package com.pagination.api;

import com.pagination.model.Book;
import com.pagination.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookApi {

    private final BookRepository bookRepository;

    @GetMapping
    public Page<Book> pagination(@RequestParam Integer page, @RequestParam Integer pageSize){
        Pageable pageable = PageRequest.of(page,pageSize);
        return bookRepository.findAll(pageable);
    }

    @GetMapping("/p")
    public Page<Book> pagination (Pageable pageable){ //parametreler: page , size , sort
        return bookRepository.findAll(pageable);
    }

    @GetMapping("/s")
    public Slice<Book> slice (Pageable pageable){ // Slice daha performanslı. çünkü pagination count query'i çalıştırır. kaç kayıt var diye sana döndürür.
        return bookRepository.findAll(pageable); //facebook gibi. asagiya gittikçe açılıyor. 3 kayıt varsa 4e kadar sorgu atar 1 fazlası var mı diye bakar.
    }

}
