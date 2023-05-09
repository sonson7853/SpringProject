package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.kh.spring.member.model.vo.Member;

@Component
@Aspect
@Order(5)
public class AfterReturningTest {
	
	private Logger logger = LoggerFactory.getLogger(AfterReturningTest.class);
	
	//@AfterReturning : 메소드 실행 이후에 반환값을 얻어노는 기능의 어노테이션임
	// returning : 반환값을 저장할 매개변수명을 지정하는 속성
	
	@AfterReturning(pointcut = "CommonPointcut.implPointcut()", returning = "returnObj")
	public void returnValue(JoinPoint jp, Object returnObj) {
		
		if(returnObj instanceof Member) {
			Member loginMember = (Member)returnObj;
			loginMember.setNickName("조작된닉네임");
		}
		
		logger.info("return value : {}", returnObj);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
