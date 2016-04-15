package bg.jwd.library.dao.book;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bg.jwd.library.constant.CommonConstants;
import bg.jwd.library.entity.book.Book;

@SuppressWarnings("unchecked")
@Repository
public final class BookDaoImpl implements BookDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Book findBook(String name) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.like("name", name));
		List<Book> books = criteria.list();
		session.close();

		return books.isEmpty() ? null : books.get(0);
	}

	@Override
	public Book findBook(long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.like(CommonConstants.ID, id));
		List<Book> books = criteria.list();
		session.close();

		return books.isEmpty() ? null : books.get(0);
	}

	@Override
	public Set<Book> findBooks() {
		Session session = sessionFactory.openSession();
		String sql = "FROM Book";
		Query query = session.createQuery(sql);
		List<Book> books = query.list();
		session.close();

		return new HashSet<>(books);
	}

	@Override
	public Set<Book> findBooks(String searchedWord, String searchParam) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.like(searchParam, "%" + searchedWord + "%"));
		List<Book> books = criteria.list();
		session.close();

		return new HashSet<>(books);
	}

	@Override
	public Set<Book> findBooks(int searchedNumber, String searchParam) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.like(searchParam, searchedNumber));
		List<Book> books = criteria.list();
		session.close();

		return new HashSet<>(books);
	}

	@Transactional
	@Override
	public boolean addBook(Book book) {
		Session session = sessionFactory.openSession();
		long idCounter = findBooks().size();
		book.setId(++idCounter);
		session.save(book);
		session.flush();

		return true;
	}

	@Transactional
	@Override
	public boolean deleteBook(Long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Book.class);
		criteria.add(Restrictions.eq(CommonConstants.ID, id)).uniqueResult();
		List<Book> row = criteria.list();
		session.delete(row.get(0));
		session.flush();

		return true;
	}

	@Transactional
	@Override
	public boolean update(Book book) {
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(book);
		session.flush();

		return true;
	}
}
