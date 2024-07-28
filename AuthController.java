package com.example.miniproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/miniproject")
public class AuthController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	//회원가입
	@PostMapping("/signup")
	public String signup( @RequestParam("userName") String userName,
		      @RequestParam("userIdCard") String userIdCard,
		      @RequestParam("phone") String phone,
		      @RequestParam("email") String email,
		      @RequestParam("address") String address,
		      @RequestParam("userId") String userId,
		      @RequestParam("password") String password,
		      Model m) {
		logger.info("회원가입 요청: userName={}, userIdCard={}, phone={}, email={}, address={}, userId={}",
                userName, userIdCard, phone, email, address, userId);

		try {
			 Login login = new Login();
		      login.setUserName(userName);
		      login.setUserIdCard(userIdCard);
		      login.setPhone(phone);
		      login.setEmail(email);
		      login.setAddress(address);
		      login.setuserId(userId);
		      login.setPassword(password);
		      
		      userService.insertUser(login);
		      logger.info("회원가입 성공: userName={}", userName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("회원가입 과정에서 문제 발생!!", e);
			m.addAttribute("error", "회원가입이 정상적으로 완료되지 않았습니다!!");
			return "miniproject/signup";
		}
		
		return "redirect:/miniproject/login";
	}
	
	//로그인
	@PostMapping("/login")
	public String login(@RequestParam("userId") String userId,
					    @RequestParam("password") String password,
					    Model m) {
		Login login = userService.authenticate(userId, password);
		userService.authenticate("userId", "password");
		if(login != null) {
			logger.info("로그인 성공: userId={}", userId);
			return "redirect:/miniproject/main";
		}else {
			 logger.error("로그인 실패: 존재하지 않는 아이디나 비밀번호");
			m.addAttribute("error","존재하지 않는 아이디나 비밀번호입니다");
			return "miniproject/login";
		}
	}
	
	@GetMapping("/login")
    public String showLogin() {
		logger.info("로그인 페이지 요청");
        return "miniproject/login";
    }

    @GetMapping("/signup")
    public String showSignup() {
    	 logger.info("회원가입 페이지 요청");
    	 return "miniproject/signup";
    }

    @GetMapping("/main")
    public String showMain() {
    	logger.info("메인 페이지 요청");
        return "miniproject/main";
    }

    @GetMapping("/bookingairline")
    public String showBookingAirline() {
    	logger.info("항공예약 페이지 요청");
        return "miniproject/bookingairline";
    }

    @GetMapping("/bookinghotal")
    public String showBookingHotal() {
    	logger.info("호텔예약 페이지 요청");
        return "miniproject/bookinghotal";
    }

}