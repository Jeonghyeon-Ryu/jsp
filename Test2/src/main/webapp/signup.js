var special_re = /[~!@#$%^&*()_+|<>?:{}]/ //특수문자
var re = /^[a-zA-Z0-9]{4,12}$/ // a~z,A~Z,0~9까지 사용가능하며 4~12자리 입력
var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
//mail주소 유효성 검사
var id;
var pw;
var repw;
var mail;
var id_num_1
var id_num_2
var arrNum1 = new Array(); // 주민번호 앞자리숫자 6개를 담을 배열
var arrNum2 = new Array(); // 주민번호 뒷자리숫자 1개를 담을 배열
var idFlag = 0; //id확인의 카운트를 세주는 변수
var pwFlag = 0; // password확인의 카운트를 세주는 변수
var mailFlag = 0;  //mail 주소 확인의 카운트를 세주는 변수
var numFlag = 0; //주민등록번호 확인의 카운트를 세주는 변수

//데이터 받아오기
function valiData() {
	id = document.getElementByName("id").value;
	pw = document.getElementByName("pw").value;
	repw = document.getElementByName("repw").value;
	mail = document.getElementByName("email").value;
	//alert(id);
	id_num_1 = document.getElementByName("birth").value;
	id_num_2 = document.getElementById("id_num_2").value;
}

function checkID() { //아이디 유효성 검사
	valiData();
	if (re.test(id) == true && !(special_re.test(id)) == true) { //re에 만족하고
		//찾는 문자열이, 들어있는지 아닌지 확인, 찾으려는 문자가 들어있으면, 결과는 "true"
		alert(id + " : 사용 가능한 아이디 입니다.");
		idFlag = 1; //idflag를 1로 변경
	} else {
		alert("[ID Error] 4~12자의 영문 대소문자,숫자만 입력해주세요.");
	}
}

//비밀번호 유효성 검사
function checkPw() {
	valiData();
	if (re.test(pw) == true && !(special_re.test(pw)) == true) { //re에 만족하고
		//찾는 문자열이, 들어있는지 아닌지 확인, 찾으려는 문자가 들어있으면, 결과는 "true"
	} else {
		if (pw == "") {
			alert("[Password Error] 비밀번호를 입력해주세요.");
		} else {
			alert("[Password Error] 4~12자의 영문 대소문자,숫자만 입력해주세요.");
			pw.value = "";
			//pw.focus();
		}
	}
}
//비밀번호 확인
function checkRePw() {
	valiData();
	if (pw != repw) {
		alert("[Password Error] 비밀번호가 다릅니다. 다시 확인해 주세요.");
	} else {
		if (repw == "") {  //비밀번호 칸이 비어있는 상태에서 비밀번호 확인은 할 수 없음
			alert("[Password Error] 비밀번호 확인을 하려면 비밀번호를 먼저 입력해주세요.");
		} else {
			alert("비밀번호가 일치합니다.");
			pwFlag = 1;
			repw.value = "";
			//pw.focus();
		}
	}
}

//메일 형식 확인
function checkMail() {
	valiData();
	if (re2.test(mail) != true) { //re에 만족하고
		//찾는 문자열이, 들어있는지 아닌지 확인, 찾으려는 문자가 들어있으면, 결과는 "true"
		alert("[Mail Error] 메일 형식을 확인해주세요.");
	} else {
		alert("사용 가능한 메일 주소 입니다.");
		mailFlag = 1;
		mail.value = "";
		//pw.focus();
	}
}

//주민등록번호 유효성 검사
function checkNum() {
	valiData();
	for (var i = 0; i < id_num_1.length; i++) {
		arrNum1[i] = id_num_1.charAt(i);
	} // 주민번호 앞자리를 배열에 순서대로 담는다.

	for (var i = 0; i < id_num_2.length; i++) {
		arrNum2[i] = id_num_2.charAt(i);
	} // 주민번호 뒷자리를 배열에 순서대로 담는다.
	var tempSum = 0;
	for (var i = 0; i < id_num_1.length; i++) {
		tempSum += arrNum1[i] * (2 + i);
	} // 주민번호 검사방법을 적용하여 앞 번호를 모두 계산하여 더함
	for (var i = 0; i < id_num_2.length - 1; i++) {
		if (i >= 2) {
			tempSum += arrNum2[i] * i;
		} else {
			tempSum += arrNum2[i] * (8 + i);
		}
	} // 같은방식으로 앞 번호 계산한것의 합에 뒷번호 계산한것을 모두 더함
	if ((11 - (tempSum % 11)) % 10 != arrNum2[6]) {
		alert("올바른 주민번호가 아닙니다.");
		id_num_1 = "";
		id_num_2 = "";
		id_num_1.focus();
	} else {
		numFlag = 1;
		if (arrNum2[0] == 1 || arrNum2[0] == 2) { // 주민등록번호 뒷자리 배열의 첫번째 값이 1이나 2면
			var y = parseInt(id_num_1.substring(0, 2));
			var m = parseInt(id_num_1.substring(2, 4));
			var d = parseInt(id_num_1.substring(4, 6));
			document.getElementById("years").value = 1900 + y; //1900년대 생이므로 1900에 y를 더해준다.
			document.getElementById("month").value = m;
			document.getElementById("day").value = d;
		} else { // 주민등록번호 뒷자리 배열의 첫번째 값이 1이나 2가 아니면 (3,4)
			var y = parseInt(id_num_1.substring(0, 2));
			var m = parseInt(id_num_1.substring(2, 4));
			var d = parseInt(id_num_1.substring(4, 6));
			document.getElementById("years").value = 2000 + y; //2000년대 생
			document.getElementById("month").value = m;
			document.getElementById("day").value = d;
		}
	}
} //checkNum함수 닫기

//주소 형식
function openZipSearch() {
	new daum.Postcode({
		oncomplete: function(data) {
			$('[name=zip]').val(data.zonecode); // 우편번호 (5자리)
			$('[name=addr1]').val(data.address);
			$('[name=addr2]').val(data.buildingName);
		}
	}).open();
}

function checkAll() {
	if (idFlag == 1 && pwFlag == 1 && mailFlag == 1 && numFlag == 1) {
		//유효성 검사를 했는지 확인해주는 각 플래그들이 1로 변경되어야먄 회원가입이 진행된다.
		return true; //참이면 true로 반환
	} else {
		if (idFlag != 1) {
			alert("ID확인을 진행해주세요.")
			return false;
		} else if (pwFlag != 1) {
			alert("Password 확인을 진행해주세요.")
			return false;
		} else if (mailFlag != 1) {
			alert("mail 주소 확인을 진행해주세요.")
			return false;
		} else if (numFlag != 1) {
			alert("주민등록번호 확인을 진행해주세요.")
			return false;
		}
	}
}