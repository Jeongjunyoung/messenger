package ms.mscom.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ms.mscom.domain.RelationUser;
import ms.mscom.domain.UserRequest;
import ms.mscom.domain.UserVO;
import ms.mscom.persistence.MessengerDAO;

@Service
public class MessengerServiceImpl implements MessengerService {
	
	@Inject
	private MessengerDAO dao;
	
	@Override
	public void insertUser(UserVO vo) throws Exception {
		dao.register(vo);
	}
	@Override
	public List<UserVO> findFriends(String user_id) throws Exception {
		String value = "%"+user_id+"%";
		return dao.findFriends(value);
	}
	@Override
	public void addRequest(Map<String, String> map) throws Exception {
		dao.addRequest(map);
	}
	@Override
	public List<UserRequest> getRequestList(String user_id) throws Exception {
		return dao.getRequestList(user_id);
	}
	@Override
	public void requestAccept(RelationUser ru) throws Exception {
		dao.requestAccept(ru);
		dao.updateRequestList(ru);
	}
	@Override
	public List<RelationUser> getFriendsList(String user_id) throws Exception {
		return dao.getFriendsList(user_id);
	}
}
