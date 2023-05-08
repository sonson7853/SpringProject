package com.kh.spring.common.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect // 공통관심사가 작성된 클래스임을 명시
	    // * 공통관심사 ? 특정 흐름사이에 끼여서 수행할 코드를 의미함
		// Aspect어노테이션이 붙은 클래스에는 실행할 코드(advcie)와 pointCut이 정의되어있어야함.
		// advice(끼여들어서 실제로 수행할 메서드나 코드)
		// @PointCut(advice가 끼여들어서 수행될 클래스, 메서드 위치등을 정의)이 작성되어있어야함.
@Component // 런타임시 필요한 위치에 코드를 끼워넣을 수 있도록 bean으로 등록시켜주기
public class Test {
	
	private Logger logger = LoggerFactory.getLogger(Test.class);
	
	// joinPoint : 클라이언트가 호출하는 모~든 비지니스메서드. advice가 적용될 수 있는 예비 후보
	//			   ex) 클래스의 인스턴스 생성시점. 메소드 호출시점, 예외발생 등 전부
	
	// PointCut : joinPoint들 중에서 "실제로" advice가 끼워들어서 실행될 지점을 선택
	
	/*
	 * PointCut 작성 방법
	 * 
	 * @PointCut("execution([접근제한자] 반환형 패키지 + 클래스명.메서드명([매개변수]))")
	 * PointCut 표현식 
	 * * : 모든 | 아무값
	 * .. : 하위 | 아래(하위패키지) | 0개이상의 매개변수
	 * 
	 * @Before : PointCut에 지정된 메서드가 실행되기 전에 advice수행을 명시하는 어노테이션임
	 * 
	 * com.kh.spring.Board패키지 아래에 있는 Impl로 끝나는 클래스의 모든 메서드에(매개변수와 상관없이) 포인트컷 지정
	 */
	
	@Before("execution(* com.kh.spring.board..*Impl*.*(..))")
	public void start() { // 모든 메소드에 서비스 수행전에 실행되는 메소드(advice)
		logger.info("=================== 서비스 시작 ====================");
		
	}
	
	// @After : PointCut에 지정된 메소드가 수행된 후, advice수행을 하라고 지시하는 어노테이션임
	@After("execution(* com.kh.spring.board..*Impl*.*(..))")
	public void end() {
		logger.info("==================== 서비스 종료 ====================");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
