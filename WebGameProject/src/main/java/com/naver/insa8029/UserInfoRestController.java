package com.naver.insa8029;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.naver.insa8029.service.UserInfoService;

@RestController
public class UserInfoRestController {
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value="user/join", method=RequestMethod.POST)
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response) {
		return userInfoService.join(request, response);
	}
}


