package ms.mscom.persistence;

import java.util.List;
import java.util.Map;

import ms.mscom.domain.RelationUser;
import ms.mscom.domain.UserRequest;
import ms.mscom.domain.UserVO;

public interface MessengerDAO {

	public void register(UserVO vo)throws Exception;
	public List<UserVO> findFriends(String user_id)throws Exception;
	public void addRequest(Map<String, String> map)throws Exception;
	public List<UserRequest> getRequestList(String user_id)throws Exception;
	public void requestAccept(RelationUser ru)throws Exception;
	public void updateRequestList(RelationUser ru)throws Exception;
	public List<RelationUser> getFriendsList(String user_id)throws Exception;
}
