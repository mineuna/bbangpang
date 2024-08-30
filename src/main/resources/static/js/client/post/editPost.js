// select box
$(function () {
    const selectedCategoryNo = $('input[name="selectedCategoryNo"]').val();
    const selectedBoardNo = $('input[name="selectedBoardNo"]').val();

    $('select[name="categoryNo"]').val(selectedCategoryNo);

    function populateBoardNo(categoryNo) {
        let arrType = getAgreeType();
        let optionType = $('select[name="boardNo"]');
        optionType.empty();

        if (categoryNo in arrType) {
            for (let prop in arrType[categoryNo]) {
                let selected = (prop == selectedBoardNo) ? 'selected' : '';
                optionType.append('<option value="' + prop + '" ' + selected + '>' + arrType[categoryNo][prop] + '</option>');
            }
        } else {
            optionType.append('<option value="">게시판 선택</option>');
        }
    }

    $('select[name="categoryNo"]').on('change', function () {
        populateBoardNo($(this).val());
    });

    $('select[name="categoryNo"]').trigger('change');
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
    fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']
});