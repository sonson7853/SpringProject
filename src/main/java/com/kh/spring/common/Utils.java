package com.kh.spring.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	//변경된 이름을 돌려주면서, 원보파일을 변경된 파일이름으로 서버에 저장시키는 메서드.
	static public String saveFile(MultipartFile upfile, String savePath) throws IllegalStateException, IOException {
		String originName = upfile.getOriginalFilename();
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		int random = (int)(Math.random() * 90000 + 10000);
		
		String ext = originName.substring(originName.lastIndexOf("."));
		
		String changeName = currentTime + random + ext;
		
		upfile.transferTo(new File(savePath+changeName));
		return changeName;
	}
}
