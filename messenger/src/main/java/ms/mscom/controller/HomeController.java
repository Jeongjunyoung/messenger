package ms.mscom.controller;

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
		List<UserVO> list = null;
		model.addAttribute("list", list);
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
}
