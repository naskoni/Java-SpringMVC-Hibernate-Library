package bg.jwd.library.service.lend;

import java.util.Set;

import bg.jwd.library.entity.lend.Lend;

public interface LendService {

	Lend findLend(long id);

	Set<Lend> findLends();

	boolean addLend(Lend lend);

	boolean updateLend(Lend lend);

	Set<Lend> findLends(String searchedWord, String searchParam);
}
