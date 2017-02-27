package ms.mscom.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ms.mscom.domain.RelationUser;
import ms.mscom.domain.UserRequest;
import ms.mscom.domain.UserVO;
import ms.mscom.service.MessengerService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	private MessengerService ms;
	@RequestMapping("/")
	public String home(){
		return "home";
	}
	
	@RequestMapping("/chat")
	public String chat() {
		return "chat";
	}
	
	@RequestMapping("/signUp")
	public String signUp() {
		return "signUp";
	}
	@RequestMapping("/register")
	public String register(UserVO vo)throws Exception{
		ms.insertUser(vo);
		return "home";
	}
	@RequestMapping("/find_friends")
	@ResponseBody
	public List<UserVO> findF(@RequestParam("user_id") String user_id)throws Exception{
		return ms.findFriends(user_id);
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(UserVO vo, HttpServletRequest request,Model model)throws Exception{
		HttpSession session = request.getSession();
		List<UserRequest> r_list = ms.getRequestList(vo.getUser_id());
		List<RelationUser> getF_list = ms.getFriendsList(vo.getUser_id());
		List<UserVO> f_list = new ArrayList<UserVO>();
		for(int i=0; i<getF_list.size();i++){
			UserVO uvo = new UserVO();
			if(vo.getUser_id().equals(getF_list.get(i).getUser_1())){
				uvo.setUser_id(getF_list.get(i).getUser_2());
			}else if(vo.getUser_id().equals(getF_list.get(i).getUser_2())){
				uvo.setUser_id(getF_list.get(i).getUser_1());
			}
			f_list.add(uvo);
		}
		model.addAttribute("r_list", r_list);
		model.addAttribute("f_list", f_list);
		model.addAttribute("user", vo);
		session.setAttribute("user", vo);
		return "home";
	}
	@RequestMapping("/f-request")
	public String f_friends(@RequestParam("user_id") String user_id,
			HttpServletRequest request)throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		UserVO sessionVo = (UserVO) session.getAttribute("user");
		map.put("request_id", sessionVo.getUser_id());
		map.put("response_id",user_id);
		ms.addRequest(map);
		return "home";
	}
	@RequestMapping("/r_accept")
	@ResponseBody
	public RelationUser r_accept(@RequestParam("request_id") String request_id,
			HttpServletRequest request)throws Exception{
		HttpSession session = request.getSession();
		UserVO sessionVo = (UserVO) session.getAttribute("user");
		RelationUser ru = new RelationUser();
		ru.setUser_1(sessionVo.getUser_id());
		ru.setUser_2(request_id);
		ms.requestAccept(ru);
		return ru;
	}
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request)throws Exception{
		HttpSession session = request.getSession();
		session.invalidate();
		return "home";
	}
}
