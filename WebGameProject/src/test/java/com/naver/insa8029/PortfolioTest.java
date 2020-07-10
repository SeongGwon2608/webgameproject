package com.naver.insa8029;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


//Spring에서 JUnit4 라이브러리를 사용하기 위한 설정
@RunWith(SpringJUnit4ClassRunner.class)
//location에 설정된 파일들을 실행시켜서 메모리에 로드하기 위한 설정
//프레임워크마다 설정파일의 경로가 가들므로 web.xml에 설정된 내용을 확인하고 작성
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class PortfolioTest {
	//데이터베이스 연결을 확인할 때 주입
	@Autowired
	private DataSource dataSource;
	@Autowired
	private SqlSession sqlSession;
	
	//연결테스트를 위한 메소드
	//20.07.10 14:23 정상 확인
	@Test
	public void connectTest() {
		try {
			System.out.println(dataSource.getConnection());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
    @Test
    public void mybatisEmailCheckTest() throws Exception{
    	//존재하므로 email이 리턴
    	System.out.println(sqlSession.selectOne("userinfo.emailcheck", "ggangpae1@gmail.com") +"");
    	//존재하지 않으므로 null 리턴
    	System.out.println(sqlSession.selectOne("userinfo.emailcheck", "ggangpae2@gmail.com") + "");
    }
    
    @Test
    public void mybatisNicCheckTest() throws Exception{
    	//존재하므로 nickname이 리턴
    	System.out.println(sqlSession.selectOne("userinfo.nicknamecheck", "군계") +"");
    	//존재하지 않으므로 null 리턴
    	System.out.println(sqlSession.selectOne("userinfo.nicknamecheck", "싸움닭") + "");
    }
    
//    @Test
//    public void mybatisInsertTest() throws Exception{
//    	//성공하면 1리턴
//    	UserInfo userInfo = new UserInfo();
//    	userInfo.setEmail("ggangpae2@gmail.com");
//    	userInfo.setUserpw("1234");
//    	userInfo.setNickname("싸움닭");
// //   	userInfo.setImage("default.jpg");
////    	System.out.println(sqlSession.insert("userinfo.join", userInfo) + "");
//    }
    
    

}

 
