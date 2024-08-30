//현재 url 주소 복사
let nowUrl = window.location.href;

function copyUrl(){
    navigator.clipboard.writeText(nowUrl).then(res=>{
        alert("주소가 복사되었습니다");
    })
}

// 삭제 버튼 클릭 시 비밀 번호 확인 화면으로 이동
function deleteInquiryCheck() {
    var inquiryNo = document.getElementById('inquiryNo').value;
    localStorage.setItem('inquiryNo', inquiryNo);

    var url = "/inquiry/delete/check";
    window.location.href = url;
}