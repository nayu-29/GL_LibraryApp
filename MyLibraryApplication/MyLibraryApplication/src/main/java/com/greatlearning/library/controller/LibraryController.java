package com.greatlearning.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.library.entity.Book;
import com.greatlearning.library.service.BookService;

//localhost:8085/books/list-books
@Controller
@RequestMapping("/books")
public class LibraryController {

	@Autowired
	BookService bookService;
	
	@GetMapping("/list")
	String getAllBooks(Model bm)
	{
		List<Book> allbooks= bookService.getAllBooks();
		bm.addAttribute("books",allbooks);
		return "books/list-books";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		Book b1=new Book();
		theModel.addAttribute("book",b1);
		return "books/book-form";
	}
	
	
	@PostMapping("/save")
	public String addBookByPostmanObjectBody(
	@ModelAttribute("book") Book b1)
	{
	bookService.addBook(b1);
	return "redirect:/books/list";
	}
	
	@PostMapping("/delete")
	String deleteBookById(@RequestParam("bookId") int bidd)
	{
		bookService.deleteById(bidd);
		return "redirect:/books/list";
	}
	
	
	@PostMapping("/showFormForUpdate")
	String updateBook(@RequestParam("bookId") int bidd,
					Model theModel)
	{
		Book dbbook=bookService.getBooksById(bidd);
		theModel.addAttribute("book",dbbook);
		return "books/book-form";
	}
}
