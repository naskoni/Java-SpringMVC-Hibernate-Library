package bg.jwd.library.service.book;

import java.util.Set;

import bg.jwd.library.entity.book.Book;

public interface BookService {

	Book findBook(String name);

	Book findBook(long id);

	Set<Book> findBooks();

	boolean deleteBook(Long id);

	boolean addBook(Book book);

	boolean updateBook(Book book);

	Set<Book> findBooks(String searchedWord, String searchParam);
}
