package bg.jwd.library.service.user;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import bg.jwd.library.constant.CommonConstants;
import bg.jwd.library.constant.UserConstants;
import bg.jwd.library.dao.user.UserDao;
import bg.jwd.library.entity.user.LibraryUser;

@Service
public final class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public Set<LibraryUser> findUsers() {
		return userDao.findUsers();
	}

	@Override
	public LibraryUser findUser(String username) {
		return userDao.findUser(username);
	}

	@Override
	public boolean addUser(LibraryUser user) {
		String enteredPassword = user.getPassword();
		String encryptedPassword = DigestUtils.md5DigestAsHex(enteredPassword.getBytes());
		user.setPassword(encryptedPassword);
		user.setStatus("active");

		return userDao.addUser(user);
	}

	@Override
	public boolean updateUser(LibraryUser user) {
		String enteredPassword = user.getPassword();
		String encryptedPassword = DigestUtils.md5DigestAsHex(enteredPassword.getBytes());
		user.setPassword(encryptedPassword);

		return userDao.updateUser(user);
	}

	@Override
	public boolean deactivateUser(Long id) {
		LibraryUser user = userDao.findUser(id);
		user.setStatus("deactivated");

		return userDao.updateUser(user);
	}

	@Override
	public Set<LibraryUser> findUsers(String searchedWord, String searchParam) {
		if (searchedWord.isEmpty() || CommonConstants.ASTERISK.equals(searchedWord)) {
			return userDao.findUsers();
		}

		if (UserConstants.ROLE.equals(searchParam)) {
			return userDao.findUsers(searchedWord.toUpperCase(), searchParam);
		}

		return userDao.findUsers(searchedWord, searchParam);
	}
}