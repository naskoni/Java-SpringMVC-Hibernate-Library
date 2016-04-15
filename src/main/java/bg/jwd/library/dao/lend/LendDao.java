package bg.jwd.library.dao.lend;

import java.sql.Date;
import java.util.Set;

import bg.jwd.library.entity.lend.Lend;

public interface LendDao {

	Lend findLend(long id);

	Set<Lend> findLends();

	Set<Lend> findLends(String searchedWord, String searchParam);

	Set<Lend> findLends(Date searchedWord, String searchParam);

	boolean addLend(Lend lend);

	boolean update(Lend lend);

}
