package ms.mscom.persistence;

import java.util.List;
import java.util.Map;

import ms.mscom.domain.UserVO;

public interface MessengerDAO {

	public void register(UserVO vo)throws Exception;
	public List<UserVO> findFriends(String user_id)throws Exception;
	public void addRequest(Map<String, String> map)throws Exception;
}
