package com.kh.spring.common.scheduling;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;

@Component
public class FileDeleteScheduler {
	
	private Logger logger = LoggerFactory.getLogger(FileDeleteScheduler.class);
	
	@Autowired
	private ServletContext application;
	
	@Autowired
	private BoardService service;
	
	// 1. BOARD_IMG 테이블 안에있는 이미지 목록들을 모두 조회.
	// 2. images/boardT 디렉토리안에있는 이미지들과 대조 후 
	// 3. 일치하지 않는 이미지파일들 삭제(db엔 없고, boardT안에만 존재하는경우)
	// 4. 우선 5초간격으로 테스트 후 정상적으로 작동한다면, 매달 1일 정시에 실행되는 스케쥴러로 만들기
	
	
	@Scheduled(cron = "0 0 0 1 * *") // 매달1일 정시 
	public void deleteFile() {
		logger.info("파일 삭제 시작");
		
		// 1) board_img테이블 안에있는 모든 파일 목록들 조회
		List<String> list = service.selectFileList();
		
		// 2) images/board 폴더 안에있는 모든 이미지 파일들 조회(file클래스 활용)
		File path = new File(application.getRealPath("/resources/images/boardT")); 
		File[] files = path.listFiles(); // path가 참조하고있는 폴더에 들어가서 모든파일을 file배열로 얻어옴
		List<File> fileList = Arrays.asList(files);
		
		logger.info(list.toString());
		logger.info(fileList.toString());
		
		if(!list.isEmpty()) {
			//server : C:\Users\bumi\Desktop\Spring-Workspace\SpringProject\src\main\webapp\resources\images\boardT\2023050212415322049.jpg
			//list : 2023050212415322049.jpg
			
			for(File serverFile : fileList) {
				String fileName = serverFile.getName(); // 파일명만 얻어오는 메서드
				
				// 방법 1
//				boolean isTrue = false;
//				for(String str:list) {
//					if(str .equals(fileName)) {
//						isTrue = true;
//					}
//				}
//				if(!isTrue) {
//					serverFile.delete();
//				}
				
				// 방법 2. indexOf or contains 이용
				// List.indexOf(value) : List에 value에 같은 값이 있으면 인덱스를 반환/ 없으면 -1을 반환
				if(list.indexOf(fileName) == -1) {
					// select해온 db목록에는 없는데 실제 웹서버에는 저장된 파일인경우(garbage data)
					logger.info(fileName+"파일 삭제함");
					serverFile.delete();
					
				}
			}
		}
		logger.info("xxx파일 삭제함");
		
		logger.info("서버파일 삭제작업 끝");
	}
	

}
