package ms.mscom.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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
}
