package com.kh.spring.board.model.vo;

import java.util.ArrayList;


import lombok.Data;


@Data
public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardCd;
	private String boardWriter;
	private int count;
	private String createDate; // 원하는 형태의 날짜로 얻어오기 위해 문자열로 바꿈
	private String status;
	
	private String originName;
	private String changeName;
	private String nickName;
	
	private String thumbnail;
	
	private String profileImage;
	
	private ArrayList<BoardImg> imgList;
}
