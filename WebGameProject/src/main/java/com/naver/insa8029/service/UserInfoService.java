package com.naver.insa8029.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UserInfoService {
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response);

}
