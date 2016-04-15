package bg.jwd.library.dao.book;

import java.util.Set;

import bg.jwd.library.entity.book.Book;

public interface BookDao {

	Book findBook(String name);

	Book findBook(long id);

	Set<Book> findBooks();

	Set<Book> findBooks(String searchedWord, String searchParam);

	Set<Book> findBooks(int searchedNumber, String searchParam);

	boolean addBook(Book book);

	boolean deleteBook(Long id);

	boolean update(Book book);

}
