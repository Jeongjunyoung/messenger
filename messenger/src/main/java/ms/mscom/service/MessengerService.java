package ms.mscom.service;

import java.util.List;

import ms.mscom.domain.UserVO;

public interface MessengerService {
	public void insertUser(UserVO vo)throws Exception;
	public List<UserVO> findFriends(String user_id)throws Exception;
}
