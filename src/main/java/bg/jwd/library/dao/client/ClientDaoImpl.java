package bg.jwd.library.dao.client;

import java.sql.Date;
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
import bg.jwd.library.entity.client.Client;

@SuppressWarnings("unchecked")
@Repository
public final class ClientDaoImpl implements ClientDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Client findClient(String name) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.like("name", name));
		List<Client> clients = criteria.list();
		session.close();

		return clients.isEmpty() ? null : clients.get(0);
	}

	@Override
	public Client findClient(long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.like(CommonConstants.ID, id));
		List<Client> clients = criteria.list();
		session.close();

		return clients.isEmpty() ? null : clients.get(0);
	}

	@Override
	public Set<Client> findClients() {
		Session session = sessionFactory.openSession();
		String sql = "FROM Client";
		Query query = session.createQuery(sql);
		List<Client> clients = query.list();
		session.close();

		return new HashSet<>(clients);
	}

	@Override
	public Set<Client> findClients(String searchedWord, String searchParam) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.like(searchParam, "%" + searchedWord + "%"));
		List<Client> clients = criteria.list();
		session.close();

		return new HashSet<>(clients);
	}

	@Override
	public Set<Client> findClients(Date searchedDate, String searchParam) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.like(searchParam, searchedDate));
		List<Client> clients = criteria.list();
		session.close();

		return new HashSet<>(clients);
	}

	@Transactional
	@Override
	public boolean addClient(Client client) {
		Session session = sessionFactory.openSession();
		long idCounter = findClients().size();
		client.setId(++idCounter);
		session.save(client);
		session.flush();

		return true;
	}

	@Transactional
	@Override
	public boolean deleteClient(Long id) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Client.class);
		criteria.add(Restrictions.eq(CommonConstants.ID, id)).uniqueResult();
		List<Client> row = criteria.list();
		session.delete(row.get(0));
		session.flush();

		return true;
	}

	@Transactional
	@Override
	public boolean update(Client client) {
		Session session = sessionFactory.openSession();
		session.saveOrUpdate(client);
		session.flush();

		return true;
	}
}
