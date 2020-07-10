package com.naver.insa8029.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.insa8029.domain.UserInfo;


@Repository
public class UserInfoDAO {
	@Autowired
	private SqlSession sqlSession;
	
	//이메일 중복체크 메소드
	public String emailCheck(String email) {
		return sqlSession.selectOne("userinfo.emailcheck", email) ;
	}
	
	//닉네임 중복 체크 메소드
	public String nicknameCheck(String nickname) {
		return sqlSession.selectOne("userinfo.nicknamecheck", nickname) ;
	}
	
	//회원가입 처리 메소드
	public int join(UserInfo userInfo) {
		return sqlSession.insert("userinfo.join", userInfo) ;
	}



}
