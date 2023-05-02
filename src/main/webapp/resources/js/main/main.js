/**
 * 
 */
 /* 회원정보 조회(비동기 통신) */

 document.getElementById('select1').addEventListener('click',function(){
 	
 	const input = document.getElementById('in1');
 	const div = document.getElementById('result1');
 	
 	$.ajax({
 		url : "member/selectOne",
 		data : {input : input.value},
 		type : "POST",
 		dataType : "JSON",
 		success : function(result){
 			console.log(result);
 			div.innerHTML = `
 				<div>유저아이디 : ${result.userId}</div>
 				<div>유저닉네임 : ${result.nickName}</div>
 				<div>핸드폰번호 : ${result.phone}</div>
 				<div>주소 : ${result.address}</div>	
 			`;
 		},
 		error : function(request){
 			console.log("에러발생");
 			console.log("에러코드 : " + request.status);
 		}
 	
 	});
 
 });
 
 /*일정 시간 마다 회원 목록 조회*/
 function selectAll(){
 	$.ajax({
 		url : "member/selectAll",
 		dataType : "json",
 		type : "POST",
 		success : function(list){
 			// list == js배열
 			const memberList = document.getElementById("memberList");
 			memberList.innerHTML = "";
 			
 			// 반복문을 활용하여 데이터 추가
 			for(let item of list){
 				const tr = document.createElement('tr');
 				const td1 = document.createElement('td');
 				td1.innerText = item.userNo;
 				
 				const td2 = document.createElement('td');
 				td2.innerText = item.userId;
 				
 				const td3 = document.createElement('td');
 				td3.innerText = item.nickName; 	
 				
 				tr.append(td1, td2, td3);
 				
 				memberList.append(tr);		
 			}
 		},
 		error : function(request){
 			console.log("에러발생");
 			console.log("에러코드 : " + request.status);
 		} 	
 	});
 }
 selectAll();
 
 window.setInterval(selectAll, 10000);