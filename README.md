# 🍞 bbangpang

## 🥧 소개
- 디저트를 좋아하는 사람들이 각자의 경험과 정보를 공유하는 커뮤니티 

## 🥐 개발 환경
- <img src="https://img.shields.io/badge/Windows%2011-%230079d5.svg?style=for-the-badge&logo=Windows%2011&logoColor=white"> <img src="https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white"> <img src="https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white">
- <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"> <img src="https://img.shields.io/badge/springboot-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/jpa-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white">

  <img src="https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E"> <img src="https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white"> <img src="https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white"> <img src="https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white">

  <img src="https://img.shields.io/badge/AWS RDS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white"> <img src="https://img.shields.io/badge/AWS S3-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white">

## 🥖 개발 기간
- 2024.07 - 2024.08 (1개월)


## 🫓 데이터베이스
![breadb erd2](https://github.com/user-attachments/assets/ef93a571-7e43-4ae8-8d80-c88714458004)


## 🍪 프로젝트 기능

#### 카테고리 / 게시판
- 카테고리 / 게시판 목록 조회
  - 카테고리 클릭 시 하위 게시판 목록 출력

- 카테고리 / 게시판 등록 *(관리자)*
  - 게시판 등록 시 카테고리 선택 후 게시판 등록

- 카테고리 / 게시판 삭제 *(관리자)*
 
#### 게시글 
- 게시글 목록 조회, 게시글 상세 보기
  - 카테고리, 게시판 선택 시 카테고리별, 게시판별 게시글 목록 조회
  - 게시글 내용의 첫번째 이미지를 썸네일로 출력 (이미지 없을 경우 기본 이미지 출력)

- 게시글 등록
  - 스타일 적용 및 이미지 첨부 가능 (summernote 에디터 사용)
    
- 게시글 수정, 삭제
  - 게시글 비밀 번호 일치여부 확인 후 일치하는 경우에만 수정 화면으로 이동 / 게시글 삭제
  - 게시글 제목, 내용 수정 시 등록일이 수정일로 변경되어 출력
  - *관리자*는 비밀 번호 확인 없이 게시글 삭제 가능

- 게시글 검색

#### 댓글
- 댓글 목록 조회

- 댓글 등록

- 댓글 삭제
  - 댓글 비밀 번호 일치여부 확인 후 일치하는 경우에만 댓글 삭제
  - *관리자*는 비밀 번호 확인 없이 댓글 삭제 가능

- 댓글 검색 *(관리자)*

#### 공지
- 공지 목록 조회, 공지 상세 보기
  
- 공지 등록 *(관리자)*

- 공지 수정, 삭제 *(관리자)*
  - 공지 제목, 내용 수정 시 등록일이 수정일로 변경되어 출력

- 공지 검색

#### 문의
- 문의 목록 조회, 문의 상세 보기

- 문의 등록
 
- 문의 삭제
  - 문의 비밀 번호 일치여부 확인 후 일치하는 경우에만 문의 삭제
  - *관리자*는 비밀 번호 확인 없이 문의 삭제 가능

- 문의 검색

#### 신고
- 신고 목록 조회 *(관리자)*
- 게시글, 댓글 신고


## 🍰 프로젝트 화면
### 사용자 페이지
- 사용자 페이지   
![사용자 화면](https://github.com/user-attachments/assets/d0137339-6469-4076-acda-e40398dbb494)

- 게시글 목록 (카테고리별, 게시판별 목록)  
![게시글 목록](https://github.com/user-attachments/assets/6e3574b3-b936-445b-a082-5227197fb819)

- 게시글 검색   
![게시글 검색](https://github.com/user-attachments/assets/fb42b95a-c29d-47e6-a761-adaec7f6a29f)

- 게시글 작성    
![게시글 작성](https://github.com/user-attachments/assets/4e26e351-00d9-4123-b8b7-08a9dd39dec1)

-  게시글 수정, 삭제
<img src="https://github.com/user-attachments/assets/a2abfd4d-0ce2-4aa3-83a6-110a819e2685" width="450" height="300">
<img src="https://github.com/user-attachments/assets/5eefee7e-b35e-4438-bb51-5233efc2260d" width="450" height="300">

- 게시글 신고
<img src="https://github.com/user-attachments/assets/c613c18f-9bf4-44a6-b329-ec633e062400" width="300" height="300">

- 댓글 작성, 삭제    
![댓글 작성 삭제](https://github.com/user-attachments/assets/319ac1ec-0aac-4647-8c28-fa4ccf981aa9)

### 관리자 페이지
- 관리자 페이지   
![관리자 페이지](https://github.com/user-attachments/assets/6796812a-1fee-46ff-ae57-bbc0f61767d4)

- 카테고리, 게시판 추가
<img src="https://github.com/user-attachments/assets/f4495b8f-e01a-406d-acab-70be5bd4b654" width="450" height="300">
<img src="https://github.com/user-attachments/assets/7088f609-34aa-46e5-be0b-9b9ee0f46166" width="450" height="300">

- 카테고리 삭제
<img src="https://github.com/user-attachments/assets/7c6f09a0-be76-4970-aad3-f6864a3fbf81" width="450" height="300">
