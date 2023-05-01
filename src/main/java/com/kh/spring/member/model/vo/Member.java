package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/*
 * lombok
 * - 자동 코드 생성 라이브러리
 * - 반복되는 getter, setter, toString, 생성자 메소드 작성등을 줄여주는 역할의 코드 라이브러리
 * 
 * lombok 설치방법
 * 1. 라이브러리 다운 후 pom.xml에 추가
 * 2. 다운로드된 jar파일을 찾아서 실행.(ide가 켜져있으면 안됨)
 * 3. ide 재실행
 * 
 * lombok 사용시 주의사항
 * - uName , bTitle과 같이 앞글자가 소문자외자인 필드명은 만들면 안됨
 * - 필드명 작성시 소문자 두글자 이상으로 시작해야함.
 * - el표기법 사용시 내부적으로 getter 메소드를 찾게 되는데, 이때 getuName(), getbTitle()이라는 이름으로
 * 		메소드를 호출하기 때문에
 * */

/*@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자
//특정 필드 빼려면 final키워드 사용
@Setter // setter메서드 자동 생성
@Getter // getter메서드 자동 생성
@ToString // toString 자동생성
@EqualsAndHashCode*/ // equals, hashcode자동생성
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	private int userNo;
	private String userId;
	private String userPwd;
	private String nickName;
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	private String profileImage;
	
	
}
