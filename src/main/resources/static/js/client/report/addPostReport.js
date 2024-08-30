window.onload = function() {
    var postNo = window.opener.document.getElementById('postNo').value;

    // 히든 필드에 postNo 값을 설정
    document.getElementById('postNo').value = postNo;

    // 폼의 액션을 동적으로 설정
    var form = document.getElementById('reportForm');
    form.action = "/report/add/post/" + postNo;
}