//현재 url 주소 복사
let nowUrl = window.location.href;

function copyUrl(){
    navigator.clipboard.writeText(nowUrl).then(res=>{
        alert("주소가 복사되었습니다");
    })
}