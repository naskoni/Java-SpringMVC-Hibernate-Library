package bg.jwd.library.service.user;

import java.util.Set;

import bg.jwd.library.entity.user.LibraryUser;

public interface UserService {

	Set<LibraryUser> findUsers();

	Set<LibraryUser> findUsers(String searchedWord, String searchParam);

	LibraryUser findUser(String username);

	boolean addUser(LibraryUser user);

	boolean updateUser(LibraryUser user);

	boolean deactivateUser(Long id);

}
