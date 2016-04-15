package bg.jwd.library.service.book;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.jwd.library.constant.CommonConstants;
import bg.jwd.library.dao.book.BookDao;
import bg.jwd.library.entity.book.Book;
import bg.jwd.library.util.ParseUtils;

@Service
public final class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Override
	public Book findBook(String name) {
		return bookDao.findBook(name);
	}

	@Override
	public Book findBook(long id) {
		return bookDao.findBook(id);
	}

	@Override
	public Set<Book> findBooks() {
		return bookDao.findBooks();
	}

	@Override
	public boolean deleteBook(Long id) {
		return bookDao.deleteBook(id);
	}

	@Override
	public boolean addBook(Book book) {
		return bookDao.addBook(book);
	}

	@Override
	public boolean updateBook(Book book) {
		return bookDao.update(book);
	}

	@Override
	public Set<Book> findBooks(String searchedWord, String searchParam) {
		if (searchedWord.isEmpty() || CommonConstants.ASTERISK.equals(searchedWord)) {
			return bookDao.findBooks();
		}

		if (CommonConstants.YEAR.equals(searchParam) && ParseUtils.tryParseNumber(searchedWord)) {
			int searchedNumber = Integer.parseInt(searchedWord);
			return bookDao.findBooks(searchedNumber, searchParam);
		}

		return bookDao.findBooks(searchedWord, searchParam);
	}
}
