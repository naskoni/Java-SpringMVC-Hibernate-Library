package bg.jwd.library.dao.user;

import java.util.Set;

import bg.jwd.library.entity.user.LibraryUser;

public interface UserDao {

	LibraryUser findUser(String username);

	LibraryUser findUser(Long id);

	Set<LibraryUser> findUsers();

	Set<LibraryUser> findUsers(String searchedWord, String searchParam);

	boolean addUser(LibraryUser user);

	boolean updateUser(LibraryUser user);

}
