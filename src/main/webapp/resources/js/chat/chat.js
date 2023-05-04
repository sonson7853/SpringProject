/**
 * 
 */
 // 페이지 로딩완료시 채팅창스크롤을 제일 밑으로 내리기 (즉기 실행 함수 => 속도가빠름, 변수명 중복문제해결)
 (
 	function(){
 		const display = document.getElementsByClassName("display-chatting")[0];
 		if(display != null){
 			display.scrollTop = display.scrollHeight;
 		}
 	}
 	
 	
 )();
 
 // 채팅 메세지 보내기
 document.getElementById("send").addEventListener("click", sendMessage);
 
 
 //채팅 보내기 함수
 function sendMessage(){
 
 	// 채팅이 입력되는 textarea
 	const inputChatting = document.getElementById("inputChatting");
 	
 	if(inputChatting.value.trim().length == 0){
 		// 입력이 되지 않은 경우
 		alert("뭐라도 입력하셈");
 		
 		inputChatting.value = ""; //공백문자 삭제
 		inputChatting.focus();
 	} else{
 		// 문자가 입력이 된 경우
 		
 		// 메세지 입력시 필요한 데이터를 js객체로 생성
 		const chatMessage = {
 			"userNo" : userNo,
 			"nickName" : nickName,
 			"chatRoomNo" : chatRoomNo,
 			"message" : inputChatting.value
 		};
 		
 		// JSON.parse(문자열) : JSON -> JS Object
 		// JSON.stringify(객체) : JS Object -> JSON
 		
 		// chattingSock(웹소켓 객체를)를 이용해서 메세지 보내기
 		// chattingSock.send(값) : 웹소켓 핸들러로 값을 보냄.
 		
 		chattingSock.send(JSON.stringify(chatMessage));
 		
 		inputChatting.value = "";
 	}
 }
 
 
 // 웹소켓 핸들러에서 sendMessage라는 함수가 호출되었을때를 캐치하는 이벤트핸들러
 chattingSock.onmessage = function(e){
 	// 매개변수 e : 발생한 이벤트에 대한 정보를 담고있는 객체 (click,append,mouseover,...)
 	// e.data : 전달된 메세지가 감뎌있음 (JSON객체) ==> meesage.getPayload()와 동일
 	
 	// 전달받은 메세지를 JS객체로 변환해야함
 	const chatMessage = JSON.parse(e.data); // JSON -> JS Object로 변경해줌
 	console.log(chatMessage);
 	
 	const li = document.createElement("li");
 	
 	const p = document.createElement("p");
 	p.classList.add("chat");
 	p.innerHTML = chatMessage.message.replace(/\n/ , "<br>");
 	//내용
 	
 	const span = document.createElement("span");
 	span.classList.add("chatDate");
 	span.innerText = currentTime(); // 현재 날짜 정보
 	
 	// 내가 쓴 채팅 : span -> p	
 	// 남이 쓴 채팅 : p -> span
 	if( userNo == chatMessage.userNo) {
 		//내가씀
 		li.append(span, p);
 		li.classList.add("myChat");
 	} else{
 		//남이씀
 		li.innerHTML = "<b>" + chatMessage.nickName + "</b><br>";
 		li.append(p,span);
 	}
 	
 	// 채팅창
 	const display = document.getElementsByClassName("display-chatting")[0];
 	
 	// 채팅창에 채팅 추가
 	display.append(li);
 	
 	// 채티창 제일 밑으로 내리는 코드추가
 	display.scrollTop = display.scrollHeight; // scrollTop : 스크롤 이동시켜주는 속성
 											  // scrollHeight : 스크롤되는 요소의 전체 높이
 }
 
 // 현재 시간 출력 함수
 // 2023년 05월 04일 12:29:33 
 
 function currentTime(){
 	const now = new Date();
 	const time = now.getFullYear()+"년 "+addZero(now.getMonth()+1)+"월 "+addZero(now.getDate())+"일 "
 			     +addZero(now.getHours())+":"+ addZero(now.getMinutes()) + ":" + addZero(now.getSeconds());
 	
 	return time;		     
 }
 
 // 10보다 작은 숫자일 경우 앞에 0을 붙여주는 함수
function addZero(number){
	return number < 10 ? "0"+number : number;	
} 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 