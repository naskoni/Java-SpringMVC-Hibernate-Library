package bg.jwd.library.service.lend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.jwd.library.constant.CommonConstants;
import bg.jwd.library.dao.lend.LendDao;
import bg.jwd.library.entity.lend.Lend;
import bg.jwd.library.util.ParseUtils;

@Service
public final class LendServiceImpl implements LendService {

	@Autowired
	private LendDao lendDao;

	@Override
	public Lend findLend(long id) {
		return lendDao.findLend(id);
	}

	@Override
	public Set<Lend> findLends() {
		return lendDao.findLends();
	}

	@Override
	public boolean addLend(Lend lend) {
		return lendDao.addLend(lend);
	}

	@Override
	public boolean updateLend(Lend lend) {
		return lendDao.update(lend);
	}

	@Override
	public Set<Lend> findLends(String searchedWord, String searchParam) {
		if (searchedWord.isEmpty() || CommonConstants.ASTERISK.equals(searchedWord)) {
			return lendDao.findLends();
		}

		if ("book".equals(searchParam) || "client".equals(searchParam)) {

		}

		if ((CommonConstants.LENDING_DATE.equals(searchParam) || CommonConstants.RETURN_DATE.equals(searchParam))
				&& ParseUtils.tryParseDate(searchedWord)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date parsed = sdf.parse(searchedWord);
				java.sql.Date searchedDate = new java.sql.Date(parsed.getTime());
				return lendDao.findLends(searchedDate, searchParam);
			} catch (ParseException e) {
				// already caught in ParseUtils
			}
		}

		return lendDao.findLends(searchedWord, searchParam);
	}
}
