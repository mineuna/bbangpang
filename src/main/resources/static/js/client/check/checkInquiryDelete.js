window.onload = function() {
    const inquiryNo = localStorage.getItem('inquiryNo');

    // 히든 필드에 inquiryNo 값을 설정
    document.getElementById('inquiryNo').value = inquiryNo;

    // 폼의 액션을 동적으로 설정
    var form = document.getElementById('checkForm');
    form.action = "/inquiry/delete/" + inquiryNo;

}
