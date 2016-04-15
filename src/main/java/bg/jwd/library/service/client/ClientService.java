package bg.jwd.library.service.client;

import java.util.Set;

import bg.jwd.library.entity.client.Client;

public interface ClientService {

	Client findClient(String name);

	Client findClient(long id);

	Set<Client> findClients();

	Set<Client> findClients(String searchedWord, String searchParam);

	boolean addClient(Client client);

	boolean deleteClient(Long id);

	boolean update(Client client);

}
