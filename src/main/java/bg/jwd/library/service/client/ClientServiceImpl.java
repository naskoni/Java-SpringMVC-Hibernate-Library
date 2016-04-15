package bg.jwd.library.service.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.jwd.library.constant.CommonConstants;
import bg.jwd.library.dao.client.ClientDao;
import bg.jwd.library.dao.user.UserDao;
import bg.jwd.library.entity.client.Client;
import bg.jwd.library.entity.user.LibraryUser;
import bg.jwd.library.util.ParseUtils;
import bg.jwd.library.util.UserUtils;

@Service
public final class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;

	@Autowired
	private UserDao userdao;

	@Override
	public Client findClient(String name) {
		return clientDao.findClient(name);
	}

	@Override
	public Client findClient(long id) {
		return clientDao.findClient(id);
	}

	@Override
	public Set<Client> findClients() {
		return clientDao.findClients();
	}

	@Override
	public Set<Client> findClients(String searchedWord, String searchParam) {
		if (searchedWord.isEmpty() || CommonConstants.ASTERISK.equals(searchedWord)) {
			return clientDao.findClients();
		}

		if (CommonConstants.BIRTHDATE.equals(searchParam) && ParseUtils.tryParseDate(searchedWord)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date parsed = sdf.parse(searchedWord);
				java.sql.Date searchedDate = new java.sql.Date(parsed.getTime());
				return clientDao.findClients(searchedDate, searchParam);
			} catch (ParseException e) {
				// already caught in ParseUtils
			}
		}

		return clientDao.findClients(searchedWord, searchParam);
	}

	@Override
	public boolean addClient(Client client) {
		LibraryUser user = userdao.findUser(UserUtils.getUser().getUsername());
		client.setCreatedBy(user);

		return clientDao.addClient(client);
	}

	@Override
	public boolean deleteClient(Long id) {
		return clientDao.deleteClient(id);
	}

	@Override
	public boolean update(Client client) {
		LibraryUser user = userdao.findUser(UserUtils.getUser().getUsername());
		client.setCreatedBy(user);

		return clientDao.update(client);
	}
}
