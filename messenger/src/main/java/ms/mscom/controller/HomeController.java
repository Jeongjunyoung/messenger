package ms.mscom.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
