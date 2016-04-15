package bg.jwd.library.dao.client;

import java.sql.Date;
import java.util.Set;

import bg.jwd.library.entity.client.Client;

public interface ClientDao {

	Client findClient(String name);

	Client findClient(long id);

	Set<Client> findClients();

	Set<Client> findClients(String searchedWord, String searchParam);

	Set<Client> findClients(Date searchedWord, String searchParam);

	boolean addClient(Client client);

	boolean deleteClient(Long id);

	boolean update(Client client);

}
