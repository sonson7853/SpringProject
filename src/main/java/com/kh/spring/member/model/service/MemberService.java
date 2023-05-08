package com.kh.spring.member.model.service;

import java.util.ArrayList;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {
	
	/*
	 * Service Interface 사용하는 이유
	 * 
	 * 1. 프로젝트에 규칙성을 부여하기 위해서
	 * 
	 * 2. 클래스간의 결합도를 약하시키기 위해서 -> 유지보수성 강화
	 * 
	 * 3. Spring AOP를 위해서 필요
	 * 
	 */
	
	public abstract Member loginMember(Member inputMember);
	
	public abstract int insertMember(Member inputMember);
	
	public abstract ArrayList<Member> selectAll();
	
	void updateMemberChangePwd();
}
