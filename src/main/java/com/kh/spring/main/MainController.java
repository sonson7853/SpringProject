package com.kh.spring.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 컨트롤러임을 명시 + servlet-context.xml에서 bean(스프링에서 관리하는 객체)으로 등록시켜주는 역할을 함
public class MainController {
	
	// 요청 url 매핑
	// main이라는 문자열만 전달
	@RequestMapping("/main")
	public String mainForward() {
		
		// index.jsp의 forward를 처리하는 함수가 mainForward
		// index.jsp에서 다시한번 main페이지로 포워딩시켜줌
		return "main"; // 단순 문자열작성시 무조건 forward가 실행됨	(WEB-INF/VIEWS + main + .jsp)	
		// servlet-context.xml 의 InternalResourceViewResolver 객체가
		// 알아서 경로와 .jsp 확장자명을 붙여줌
	}
	
}
