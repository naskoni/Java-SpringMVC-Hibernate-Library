package bg.jwd.library.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import bg.jwd.library.constant.UserConstants;
import bg.jwd.library.security.UserDetailsImpl;

public final class UserUtils {

	private static final Logger logger = LoggerFactory.getLogger(UserUtils.class);

	private UserUtils() {
	}

	public static UserDetailsImpl getUser() {
		Object principal;

		try {
			principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (NullPointerException e) {
			logger.error("No logged user", e);
			return null;
		}

		if (principal == null || !(principal instanceof UserDetailsImpl)) {
			return null;
		}

		return (UserDetailsImpl) principal;
	}

	public static void addUserToModel(Model model) {
		UserDetails loggedUser = getUser();
		model.addAttribute(UserConstants.USER, loggedUser);
	}
}