package com.kh.spring.board.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.member.model.vo.Member;

@Controller // controller역할을 한다고 명시함 또한 역할 주입
@RequestMapping("/board")
public class BoardController {
   
   @Autowired
   private BoardService boardService;
   
   /*
    * 게시글 목록 조회 서비스
    * 
    * @PathVariable : URL경로에 포함되어있는 값을 변수로 사용할 수 있게하는 역할
    * => 자동으로 request scope에 등록 된다. ==> jsp에서 ${value} el로 작성가능함.
    * 
    * PathVariable : 요청 자원을 식별하는 경우에 사용
    * 
    * QueryString : 정렬, 검색 등 필터링 옵션이 있을경우 사용
    */
   
   @GetMapping("/list/{boardCode}")
   public String boardList(@PathVariable("boardCode") String boardCode,
                     @RequestParam(value = "cpage" , defaultValue="1") int currentPage,
                     Model model,
                     @RequestParam Map<String, Object> paramMap) {
	   // 검색요청이 있었다면, paramMap안에는 keyword, condition이 들어가 있을것
	   
	   Map<String, Object> map = new HashMap();
	   
	   // 게시글 목록 조회 서비스 호출 시 작업 내용
	   // 1) 게시판 이름 조회
	   // 검색요청을 하지않은경우
	   if(paramMap.get("condition") == null) {
		   boardService.selectBoardList(currentPage, boardCode, map);
	   }else {
		   // 검색요청을 한 경우
		   // 검색조건을 추가한 상태로 게시글 셀렉트?
		   if(paramMap.get("condition") != null) {
			   boardService.selectSearchList(currentPage, boardCode, map);
		   }
		  
	   }
	   
       //model.addAttribute("list", list);
       //model.addAttribute("pi",pi);
	   model.addAttribute("map", map);
	   
      return "board/boardListView";
   }
   
   
   // 게시글 상세조회
   @GetMapping("/detail/{boardCode}/{boardNo}")
   public String boardDetail(@PathVariable("boardCode") String boardCode,
		   					 @PathVariable("boardNo") int boardNo,
		   					 @RequestParam(value="cpage", required = false, defaultValue="1") int cp,
		   					 Model model,
		   					 HttpSession session,
		   					 HttpServletRequest req,
		   					 HttpServletResponse resp) {
	   
	   // 게시글 상세조회 서비스 호출
	   Board detail = boardService.selectBoardDetail(boardNo);
	   
	   // 조회수 중복 증가 방지 코드(본인글은 본인이 클릭 시 조회수가 증가안되게) -> 쿠키활용
	   if(detail != null) {
		   
		   //상세조회 성공
		   Member loginUser = (Member)session.getAttribute("loginUser");
		   int memberNo = 0;
		   
		   if(loginUser != null) {
			   //로그인한 상태일때
			   memberNo = loginUser.getUserNo();
		   }
		   
		   if(Integer.parseInt(detail.getBoardWriter()) != memberNo) {
			// 글쓴이와 현재 상세보기요청을 한 클라이언트가 같지 않을 경우에만 조회수 증가서비스 호출.
			   Cookie cookie = null;
			   
			   Cookie[] cArr = req.getCookies(); //쿠키얻어오기
			   if(cArr != null && cArr.length > 0) { // 얻어온 쿠기가 있을경우
				   for(Cookie c : cArr) {
					   if(c.getName().equals("readBoardNo")) {
						   cookie = c;
					   }
				   }
			   }   
			   int result = 0;
			   if(cookie == null) { // 기존에 readBoardNo라는 쿠키가 없는경우
				   cookie = new Cookie("readBoardNo", boardNo+"");
				   result = boardService.updateReadCount(boardNo);
			   } else {
				   // readBoardNo가 이미 존재하는 경우 -> 쿠키에 저장된값 뒤쪽에 현재 조회된 게시글 번호를 추가
				   //								 단, 기존쿠키값중에 붕복되는 번호가 없어야함.
				   String temp [] = cookie.getValue().split("/"); //기존 value
				   // "readBoardNo" / "1/2/3/4/5/6/7/100"
				   
				   List<String> list = Arrays.asList(temp); // 배열 -> List로 변환시켜주는 함수
				   
				   // List.indexOf(Object) : 
				   // - List에서 Object와 일치하는 부분의 인덱스를 반환
				   // - 일치하는부분이 없다면 -1을 반환
				   if(list.indexOf(boardNo+"") == -1){ // 즉, 기존값에 같은 글번호가 없다면
					   cookie.setValue(cookie.getValue()+"/"+boardNo);
					   result = boardService.updateReadCount(boardNo); // 조회수증가서비스 호출
				   }
			   }
			   if(result > 0) {
				   cookie.setPath(req.getContextPath());
				   cookie.setMaxAge(60*60*1); // 쿠키. 1시간유지
				   resp.addCookie(cookie);
			   }
		   }
	   } 
	   model.addAttribute("b", detail);
	   return "board/boardDetailView";
   	}
   
   
}