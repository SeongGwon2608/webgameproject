package com.naver.insa8029;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//웹사이트의 페이지 이동에만 사용하는 컨트롤러
@Controller
public class PageController {
	@RequestMapping(value = "user/join", method=RequestMethod.GET)
	public String join(){
		return "user/join";
	}
}
