function commentReportPopup(postNo, commentNo) {
    var url = "/report/add/comment?postNo=" + postNo + "&commentNo=" + commentNo;
    var options = "width=450,height=400,left=400,top=200";
    var popup = window.open(url, "ReportPopup", options);
}