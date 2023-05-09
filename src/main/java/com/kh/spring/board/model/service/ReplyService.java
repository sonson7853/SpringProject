package com.kh.spring.board.model.service;

import java.util.List;

import com.kh.spring.board.model.vo.Reply;

public interface ReplyService {
	
	//댓글등록
	public int insertReply(Reply reply);
	
	//댓글목록조회
	List<Reply> selectReplyList(int bno);
	
	//댓글수정
	int updateReply(Reply reply);
	
	//댓글삭제
	int deleteReply(int replyNo);
}
