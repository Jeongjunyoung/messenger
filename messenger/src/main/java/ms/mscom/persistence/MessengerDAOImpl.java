package ms.mscom.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ms.mscom.domain.RelationUser;
import ms.mscom.domain.UserRequest;
import ms.mscom.domain.UserVO;

@Repository
public class MessengerDAOImpl implements MessengerDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static String namespace = "ms.mscom.mapper.messenger";
	@Override
	public void register(UserVO vo) throws Exception {
		sqlSession.insert(namespace+".register", vo);
	}
	@Override
	public List<UserVO> findFriends(String user_id) throws Exception {
		return sqlSession.selectList(namespace+".findFriends", user_id);
	}
	@Override
	public void addRequest(Map<String, String> map) throws Exception {
		sqlSession.insert(namespace+".addRequest", map);
	}
	@Override
	public List<UserRequest> getRequestList(String user_id) throws Exception {
		return sqlSession.selectList(namespace+".request_list", user_id);
	}
	@Override
	public void requestAccept(RelationUser ru) throws Exception {
		sqlSession.insert(namespace+".acceptRequest", ru);
	}
	@Override
	public void updateRequestList(RelationUser ru) throws Exception {
		sqlSession.update(namespace+".requestList_accept", ru);
	}
	@Override
	public List<RelationUser> getFriendsList(String user_id) throws Exception {
		return sqlSession.selectList(namespace+".getFriendsList", user_id);
	}
}
