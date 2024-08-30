//현재 url 변수로 가져오기
let nowUrl = window.location.href;

function copyUrl(){
    navigator.clipboard.writeText(nowUrl).then(res=>{
        alert("주소가 복사되었습니다.");
    })
}

// 게시글 신고 팝업
function postReportPopup() {
    var url = "/report/add/post";
    var options = "width=450,height=400,left=400,top=200";
    var popup = window.open(url, "ReportPopup", options);
}

// 수정 버튼 클릭 시 비밀 번호 확인 화면으로 이동
function editPostCheck() {
    var postNo = document.getElementById('postNo').value;
    localStorage.setItem('postNo', postNo);

    var url = "/post/edit/check";
    window.location.href = url;
}

// 삭제 버튼 클릭 시 비밀 번호 확인 화면으로 이동
function deletePostCheck() {
    var postNo = document.getElementById('postNo').value;
    localStorage.setItem('postNo', postNo);

    var url = "/post/delete/check";
    window.location.href = url;
}