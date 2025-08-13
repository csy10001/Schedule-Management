## ERD
<img width="1040" height="447" alt="image" src="https://github.com/user-attachments/assets/663a6d91-229b-4dc7-9e1d-8025d6cbbcda" />

## API 명세서
| 기능        | HTTP Method | URL               | 요청 바디 예시 (JSON)                                                      | 설명         |
| --------- | ----------- | ----------------- | -------------------------------------------------------------------- | ---------- |
| 회원가입      | POST        | `/signup`         | `{ "username": "testuser", "email": "123@abc.com", "password": "1234" }` | 신규 사용자 등록  |
| 로그인       | POST        | `/login`          | `{ "email": "123@abc.com", "password": "1234" }`                         | 로그인, 세션 생성 |
| 로그아웃      | POST        | `/logout`         | 없음                                                                   | 세션 무효화     |
| 일정 생성     | POST        | `/schedules`      | `{ "title": "야호1", "username": "testuser", "content": "야호1" }`     | 일정 등록      |
| 일정 전체 조회  | GET         | `/schedules`      | 없음                                                                   | 전체 일정 조회   |
| 일정 단건 조회  | GET         | `/schedules/{id}` | 없음                                                                   | ID로 일정 조회  |
| 일정 수정     | PUT         | `/schedules/{id}` | `{ "title": "야호2", "username": "testuser", "content": "야호2" }`   | 일정 수정      |
| 일정 삭제     | DELETE      | `/schedules/{id}` | 없음                                                                   | 일정 삭제      |
| 사용자 생성    | POST        | `/users`          | `{ "username": "testuser", "email": "123@abc.com", "password": "1234" }` | 사용자 등록     |
| 사용자 전체 조회 | GET         | `/users`          | 없음                                                                   | 전체 사용자 조회  |
| 사용자 단건 조회 | GET         | `/users/{id}`     | 없음                                                                   | ID로 사용자 조회 |
| 사용자 수정    | PUT         | `/users/{id}`     | `{ "username": "user2", "email": "321@cba.com", "password": "4321" }`    | 사용자 수정     |
| 사용자 삭제    | DELETE      | `/users/{id}`     | 없음                                                                   | 사용자 삭제     |
