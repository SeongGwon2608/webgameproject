package com.naver.insa8029.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.naver.insa8029.dao.UserInfoDAO;
import com.naver.insa8029.domain.UserInfo;
import com.naver.insa8029.util.CryptoUtil;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoDAO userInfoDao;

	@Override
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response) {
		// 결과를 저장할 객체 생성
		Map<String, Object> map = new HashMap<String, Object>();
		
		//성공, 실패 여부를 확인할 데이터 생성
		map.put("result", false);
		
		//실패시 실패 이유를 저장하기 위한 데이터 생성
		map.put("emailcheck", false);
		map.put("nicknamecheck", false);

		// 파라미터 읽기
		String email = request.getParameter("email");
		String userpw = request.getParameter("userpw");
		String nickname = request.getParameter("nickname");
		
		//email 중복 검사 수행
		String emailResult = userInfoDao.emailCheck(email);
		if (emailResult == null) {
			map.put("emailcheck", true);
		} else {
			map.put("emailcheck", false);
		}

		//닉네임 중복 검사 수행
		String nicknameResult = userInfoDao.nicknameCheck(nickname);
		if (nicknameResult == null) {
			map.put("nicknamecheck", true);
		} else {
			map.put("nicknamecheck", false);
		}

		System.out.println(emailResult);
		System.out.println(nicknameResult);

		//email 중복검사와 nickname 중복검사를 통과한 경우만 데이터 삽입
		if (emailResult == null && nicknameResult == null) {
			//그림파일의 기본이름 설정
			String profileImage = "default.jpg";
			MultipartFile imageFile = request.getFile("image");
			
			//파일을 선택한 경우에만 파일을 서버에 복사
			if (imageFile.isEmpty() == false) {
				String uploadPath = request.getServletContext().getRealPath("/userinfo/profile");
				profileImage = UUID.randomUUID() + imageFile.getOriginalFilename();
				
				//실제 저장될 경로 만들기
				uploadPath = uploadPath + "/" + profileImage;
				
				//파일 객체 생성
				File file = new File(uploadPath);
				FileOutputStream fos = null;
				
				try {
					fos = new FileOutputStream(file);
					fos.write(imageFile.getBytes());
				} catch (Exception e) {
					System.err.println("전송 실패");
					System.out.println(e.getMessage());
				}
			}
			
			//암호화에 사용할 키를 생성
			//실무에서는 이 키를 데이터베이스에서 불러옴
			String key = "itggangpae";
			
			//저장할 데이터 생성
			UserInfo user = new UserInfo();
			try {
				//암호화하여 저장
				user.setEmail(CryptoUtil.encryptAES256(email, key));
				user.setUserpw(BCrypt.hashpw(userpw, BCrypt.gensalt()));
				user.setNickname(nickname);
				user.setProfileimage(profileImage);
				
				//데이터베이스에 저장
				int row = userInfoDao.join(user);
				
				//저장 성공시 map의 result에 true 저장
				if (row > 0) {
					map.put("result", true);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		}

		return map;
	}
}
