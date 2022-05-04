package com.bootcamp.spring.bookstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.bootcamp.spring.bookstore.dto.BookResource;
import com.bootcamp.spring.bookstore.entity.Book;
import com.bootcamp.spring.bookstore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    private static Book book;
    private static BookResource bookResource;
    private static ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @BeforeAll
    private static void init() {
        book = new Book("1", "Darkness of light", "A1234");
        bookResource = new BookResource("1", "Darkness of light", "A1234", 30, 10);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void insertBookShouldReturnSuccessful() throws Exception {
        when(bookService.insert(any())).thenReturn(book);

        this.mvc.perform(post("/book").contentType(APPLICATION_JSON_VALUE).content(
                    objectMapper.writeValueAsBytes(bookResource)
                ))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("message", is("Insert successful")));
    }

    @Test
    public void findAllShouldReturnListOfAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(book);

        when(bookService.findAll()).thenReturn(books);

        this.mvc.perform(get("/book/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("message", IsCollectionWithSize.hasSize(1)))
                .andExpect(jsonPath("message[0].id", is("1")));
    }

    @Test
    public void deleteByIdShouldReturnSuccessfulMessage() throws Exception {
        Mockito.doNothing().when(bookService).deleteById(anyString());

        this.mvc.perform(MockMvcRequestBuilders.delete("/book").queryParam("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("message", is("Delete successful")));
    }

    @Test
    public void updateBookShouldReturnUpdatedBook() throws Exception {
        Book updatedBook = new Book("1", "Age of darkness", "A1234");
        when(bookService.updateById(any())).thenReturn(updatedBook);

        this.mvc.perform(MockMvcRequestBuilders.put("/book").contentType(APPLICATION_JSON_VALUE)
                .content(
                        objectMapper.writeValueAsBytes(updatedBook)
                ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("message.name", is("Age of darkness")));
    }

    @Test
    public void findByIdShouldReturnTheBookWithSameId() throws Exception {
        when(bookService.findById(anyString())).thenReturn(book);

        this.mvc.perform(get("/book").queryParam("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("message.id", is(book.getId())));
    }

    @Test
    public void findByIdShouldReturnNotFoundIfNoBookFound() throws Exception {
        when(bookService.findById(anyString())).thenThrow(NoSuchElementException.class);

        this.mvc.perform(get("/book").queryParam("id", "2"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message", is("Book not found")));
    }

    @Test
    public void findByAuthorIdShouldReturnBook() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(book);

        when(bookService.findByAuthorId(anyString())).thenReturn(books);

        this.mvc.perform(get("/book/author").queryParam("id", "A1234"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("message[0].authorId", is("A1234")));
    }
}
