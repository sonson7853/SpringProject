package com.kh.spring.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

@Service // 현재 클래스가 Service임을 명시 + bean으로 등록
public class MemberServiceImpl implements MemberService {
	
	//@Autowired // 순환의존성 문제 발생
	//private MemberController memberController;
	
	@Autowired // bean으로 등록된 객체중 같은 타입이 있을 경우 의존성(객체)를 주입해줌(DI)
	private MemberDao memberDao;
	
	@Override
	public Member loginMember(Member inputMember) {
		
		Member loginUser = memberDao.loginMember(inputMember);
		
		/*
		 * SQLSessionTemplate 객체를 bean으로 등록한 후 부터는 스프링컨테이너가 자원 사용후
		 * 자동으로 반납을 해주기 때문에 close()할 필요가 없다.
		 */
		return loginUser;
		
	}
	
	@Override
	public int insertMember(Member inputMember) {
		
		int result = memberDao.insertMember(inputMember);
		
		return result;
	}
	
	@Override
	public ArrayList<Member> selectAll(){
		
		ArrayList<Member> list = memberDao.selectAll();
		return list;
	}

	@Override
	public void updateMemberChangePwd() {
		
		memberDao.updateMemberChangePwd();
	}
}
