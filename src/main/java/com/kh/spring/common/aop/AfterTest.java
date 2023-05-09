package com.kh.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1) // advice의 실행순서를 결정 (클수록 먼저 시작됨)
public class AfterTest {
	
	private Logger logger = LoggerFactory.getLogger(AfterTest.class);
	
	@After("CommonPointcut.implPointcut()")
	public void serviceEnd(JoinPoint jp) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("end : " + jp.getTarget().getClass().getSimpleName() + " - "); //클래스명 알아오기
		sb.append(jp.getSignature().getName()); // 메서드명알아오기
		sb.append("===================================================\n");
		logger.info(sb.toString());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
