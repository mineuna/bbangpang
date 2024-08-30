// select box
$(function() {
    $('select[name="categoryNo"]').on('change', function() {
        let arrType = getAgreeType();
        let optionType = $(this).parents('.selectBoard').find($('select[name="boardNo"]'));
        optionType.empty();

        if ($(this).val() in arrType) {
            for (let prop in arrType[$(this).val()]) {
                optionType.append('<option value="' + prop + '">' + arrType[$(this).val()][prop] + '</option>');
            }
        }
    });
});

function getAgreeType() {
    return {
        '1': {
            '1': '빵 뉴스',
            '2': '오픈 뉴스',
            '3': '실시간 현황 / 라인업'
        },
        '2': {
            '4': '빵 추천 질문',
            '5': '빵 정보 질문',
            '6': '빵 관련 팁 질문'
        },
        '3': {
            '7': '온라인',
            '8': '서울',
            '9': '인천 / 경기',
            '10': '강원',
            '11': '대전 / 충청',
            '12': '광주 / 전라',
            '13': '부산 / 대구 / 경상',
            '14': '제주'
        }

    };
}

// content 에디터 적용
$('#editor').summernote({
    height: 500,
    lang: "ko-KR",
    focus : true,
    toolbar: [
        ['fontname', ['fontname']],
        ['fontsize', ['fontsize']],
        ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
        ['color', ['forecolor','color']],
        ['table', ['table']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['height', ['height']],
        ['view', ['codeview','fullscreen', 'help']],
        ['insert',['picture']]
    ],
    fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋음체','바탕체'],
    fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72'],
    callbacks: {
        onImageUpload : function (files, editor, welEditable) {
            // 파일 업로드 (다중 업로드를 위해 반복문 사용)
            for (var i = 0; i < files.length; i++) {
                uploadImage(files[i], this);
            }
        }
    }
});

// 이미지 업로드
function uploadImage(file, editor) {
    data = new FormData();
    data.append("file", file);
    $.ajax({
        data : data,
        type : "POST",
        url : "/post/image",
        contentType : false,
        processData : false,
        success : function(data) {
            $(editor).summernote('insertImage', data.url);
        }
    });
}
