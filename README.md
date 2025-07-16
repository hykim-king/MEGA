# MEGA
<식단 및 운동 관리 기록형 웹 서비스 프로젝트>


- **프로젝트명**: 헬메이트
- **주제**: 웹 기반 건강관리 웹 플랫폼
- **목표**: 사용자들의 운동,다이어트,균형잡힌 식단관리 등에 대한 정보를 자유롭게 공유할 수 있도록 합니다.

### 🔹 기간 및 일정

| 구분     | 기간           | 주요 작업                        |
|----------|----------------|----------------------------------|
| 기획     | 5/19 ~ 5/30    | 주제 선정, 기획서 작성 및 발표      |
| 설계     | 6/02 ~ 6/13    | ERD, 화면 및 테이블 설계           |
| 개발     | 6/16 ~ 7/07    | DB 구축, 기능별 구현               |
| 테스트   | 7/07 ~ 7/10    | 단위 및 통합 테스트 수행           |
| 최종발표 | 7/11           | 발표 및 최종 마무리                |
    
### 🔹 팀원 소개 및 역할

| 이름     | 담당 기능                                         | 주요 업무 |
|----------|--------------------------------------------------|----------|
| 이병현 (리더) | 공지사항, 자유게시판, 댓글, 신고/좋아요 등      | 백엔드·프론트 개발 및 DB 연동 |
| 양승현     | 운동일지, 운동조회, 음식일지, 음식조회, 음식추가 | 백엔드·프론트 개발 및 DB 연동 |
| 정유성     | 로그인, 아이디/비밀번호 찾기, 메인 페이지        | 백엔드·프론트 개발 및 DB 연동 |
| 임두나     | 회원가입                                         | 백엔드·프론트 개발 및 DB 연동 |


### 🔹 개발 환경

| 항목       | 내용 |
|------------|------|
| OS         | Windows 11 |
| 개발 도구  | STS, SQL Developer, Eclipse, VSCode, Figma |
| 형상 관리  | GitHub, Git Bash |

### 🔹 주요 기능 요약


#### ▪️ 메인페이지: 목표 및 다짐 설명, 로그인/회원가입 이동

#### ▪️ 회원가입: 아이디 중복 확인, 이메일 인증, 가입 완료 시 정보 저장 및 이동

#### ▪️ 로그인: 로그인/로그아웃, 아이디 및 비밀번호 찾기

#### ▪️ 공지사항: 게시물 작성, 조회, 수정, 삭제

#### ▪️ 자유게시판: 게시물 작성, 조회, 수정, 삭제

#### ▪️ 음식일지: 사용자 맞춤 음식 일지 추가/수정/삭제, 섭취 칼로리 조회

#### ▪️ 음식조회: 섭취량 기준 영양 정보 분석

#### ▪️ 음식추가: 사용자 직접 음식 정보 등록

#### ▪️ 운동일지: 운동 시간 및 칼로리 기반 일지 추가/수정/삭제

#### ▪️ 운동조회: 운동 종류별 소모 칼로리 계산

#### ▪️ 운동추가: 운동 정보 등록



## 테스트

### 프로젝트 수행결과(이미지)  

- 메인페이지  
![메인페이지](https://github.com/hykim-king/MEGA/blob/main/doc/%EB%A9%94%EC%9D%B8.png)  

- 로그인 및 아이디 찾기/ 비밀번호 찾기(암호화된 비밀번호 이메일로 전송)  
![로그인](https://github.com/hykim-king/MEGA/blob/main/doc/%EB%A1%9C%EA%B7%B8%EC%9D%B8%ED%99%88.png)
![아이디 찾기](https://github.com/hykim-king/MEGA/blob/main/doc/%EC%95%84%EC%9D%B4%EB%94%94%EC%B0%BE%EA%B8%B0.png)
![비밀번호 찾기](https://github.com/hykim-king/MEGA/blob/main/doc/%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8%EC%B0%BE%EA%B8%B0.png)

- 회원가입(아이디 중복 확인, 비밀번호 규칙 적용, 이메일 토큰 인증)  
![회원가입](https://github.com/hykim-king/MEGA/blob/main/doc/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85.png)

- 운동일지(개인별 운동 일지 등록, 수정, 삭제/ 총 소모 칼로리 및 운동 시간 조회)
![운동일지](https://github.com/hykim-king/MEGA/blob/main/doc/%EC%9A%B4%EB%8F%99%EC%9D%BC%EC%A7%80.png)
- 운동조회(운동 데이터 및 개인별 소모 칼로리 조회)
![운동조회](https://github.com/hykim-king/MEGA/blob/main/doc/%EC%9A%B4%EB%8F%99%EC%A1%B0%ED%9A%8C.png)
- 운동추가(운동 데이터에 없는 운동에 한에서 사용자가 자유롭게 운동 추가)  
![운동추가](https://github.com/hykim-king/MEGA/blob/main/doc/%EC%9A%B4%EB%8F%99%EC%B6%94%EA%B0%80.png)

- 음식일지(개인별 음식 일지 등록, 수정, 삭제/ 총 섭취 영양정보 조회) 
![음식일지](https://github.com/hykim-king/MEGA/blob/main/doc/%EC%9D%8C%EC%8B%9D%EC%9D%BC%EC%A7%80.png)
- 음식조회(음식 데이터 및 개인별 섭취 칼로리 조회)
![음식조회](https://github.com/hykim-king/MEGA/blob/main/doc/%EC%9D%8C%EC%8B%9D%EC%A1%B0%ED%9A%8C.png)
- 음식추가(음식 데이터에 없는 음식에 한해서 사용자가 자유롭게 음식 추가)
![음식추가](https://github.com/hykim-king/MEGA/blob/main/doc/%EC%9D%8C%EC%8B%9D%EC%B6%94%EA%B0%80.png)

- 공지사항(게시글 등록, 조회) 
![공지사항](https://github.com/hykim-king/MEGA/blob/main/doc/%EA%B3%B5%EC%A7%80%EC%82%AC%ED%95%AD.png)
- 공지사항(게시글 및 댓글 등록, 수정, 삭제/ 좋아요, 싫어요/ 신고)
![공지사항 상세](https://github.com/hykim-king/MEGA/blob/main/doc/%EA%B3%B5%EC%A7%80%EC%82%AC%ED%95%AD%EC%84%B8%EB%B6%80%EC%82%AC%ED%95%AD.png)
  
- 자유게시판(게시글 등록, 조회)   
![자유게시판](https://github.com/hykim-king/MEGA/blob/main/doc/%EC%9E%90%EC%9C%A0%EA%B2%8C%EC%8B%9C%ED%8C%90.png)
- 자유게시판(게시글 및 댓글 등록, 수정, 삭제/ 좋아요, 싫어요/ 신고)  
![자유게시판 상세](https://github.com/hykim-king/MEGA/blob/main/doc/%EC%9E%90%EC%9C%A0%EA%B2%8C%EC%8B%9C%ED%8C%90%EC%84%B8%EB%B6%80%EC%82%AC%ED%95%AD.png)


### 시연 영상
- 로그인  

https://github.com/user-attachments/assets/65b2fbc6-8902-4175-bc88-53696e775a8a

- 운동  

https://github.com/user-attachments/assets/65fccd12-26dd-4175-864a-52a70dfb9de1

- 음식  

https://github.com/user-attachments/assets/8b6d60bd-beae-4269-b4b3-52ed001c24ee

- 공지사항/자유게시판  
  테스트중  

- 회원가입  

https://github.com/user-attachments/assets/799fc0c3-3979-4dec-b38b-c0f84f2c1531

