# java-blackjack

# 규칙
Blackjack : 처음 두 장의 카드 합 21 => 승   
Bust : 카드 합 21 초과 => 패   
Push : 플레이어, 딜러 카드 합이 같음 => 무승부   
Hit : 플레이어의 카드 2장의 합이 21을 초과하지 않을 경우, 추가 카드를 요청   
Stay : 플레이어가 추가 카드를 원하지 않음, 딜러는 카드 합 17 이상이면 추가 카드를 얻을 수 없음

# 구현해야 할 기능 목록
- [x] 입력
    - [x] 플레이어 이름
    - [] 플레이어(딜러 제외) 추가 카드 요청 여부

- [] 출력
    - [x] 각 플레이어(딜러 포함)에게 2장의 카드 지급 알림
    - [x] 각 플레이어(딜러 포함)가 가진 카드
    - [] 딜러의 추가 카드 지급 여부 결과
    - [] 각 플레이어(딜러 포함)의 총 카드의 합
    - [] 최종 승패 결과

- [x] 상태 (State)
    - 기능
        - [x] 추가 카드 발급
        - [x] 추가 카드 발급 거부
        - [x] 모든 카드 합 반환
        - [x] 참여 종료 여부 반환
        - [x] 발급받은 카드 목록 반환
        - [x] 결과 요청

- [x] 시작 상태 (Init)
    - 기능
        - [x] 발급받은 카드 목록 반환
        - [x] 모든 카드 합 반환

- [x] 참여 상태 (Running)
    - 기능
        - [x] 참여 종료 여부 거짓으로 반환
        - [x] ERROR : 결과 요청할 경우

    - [x] 카드 추가 받은 상태 (Hit)
        - 기능
            - [x] 추가 카드 발급
                - [x] 카드의 합 21을 초과할 경우 Burst 상태 반환
                - [x] 카드의 합 21 이하일 경우 Hit 상태 반환
            - [x] Stay 상태 반환

- [x] 종료 상태 (Finished)
    - 기능
        - [x] 참여 종료 여부 참으로 반환
        - [x] ERROR : 추가 카드 발급 시
        - [x] ERROR : 추가 카드 발급 거부 시

    - [x] 처음 카드 두 장의 합이 21인 상태 (Blackjack)
        - 기능
            - [x] 승리 결과 (1) 반환
    - [x] 모든 카드의 합이 21인 상태 (Burst)
        - 기능
            - [x] 패배 결과 (-1) 반환

    - [x] 카드 발급 거부 후 참여가 종료된 상태 (Stay)
        - 기능
            - [x] 결과 반환
                - [x] 자신의 합이 더 클 경우 승리 결과 (1) 반환
                - [x] 자신의 합이 더 작을 경우 패배 결과 (-1) 반환
                - [x] 자신의 합과 같을 경우 무승부 결과 (0) 반환

- [x] 사람 (Person)
    - 기능
        - [x] 초기 상태 주입
        - [x] 카드 추가 발급
        - [x] 카드 추가 발급 거부
        - [x] 카드 목록 반환
        - [x] 카드 합 반환
        - [x] 이름 반환
        - [x] 종료 여부 반환

- [x] 게임 참여자 (Gamer)
    - 기능
        - [x] 카드 추가 발급 거부
        - [x] 카드 목록 반환
        - [x] 카드 합 반환
        - [x] 이름 반환
        - [x] 종료 여부 반환

- [x] 플레이어 (Player)
    - 속성
        - [x] 이름
        - [x] 카드 목록

    - 기능
        - [x] 초기 상태 주입
            - [x] 카드 2장의 합 21일 경우 Blackjack 상태
            - [x] 카드 2장의 합 21 미만일 경우 Hit 상태
        - [x] 카드 추가 발급

- [x] 딜러 (Dealer)
    - 속성
        - [x] 이름
        - [x] 카드 목록

    - 기능
        - [x] 초기 상태 주입
            - [x] 카드 2장의 합 21일 경우 Blackjack 상태
            - [x] 카드 2장의 합 16 이하일 경우 Hit 상태
            - [x] 카드 2장의 합 16 초과일 경우 Stay 상태
        - [x] 카드 추가 발급
            - [x] 17 이상 21 이하일 경우, Stay 상태로 변경

- [x] 카드
    - 속성
        - [x] 점수
        - [x] 무늬

    - 기능
        - [x] 카드의 값 반환
        - [x] 카드의 값 이름 반환
        - [x] ACE인지 확인

- [x] 점수 enum
    - 속성
        - [x] 점수 이름(끗수)
        - [x] 점수

    - 기능
        - [x] 점수 이름 반환
        - [x] 점수 반환
        - [x] ACE인지 확인

- [x] 무늬 enum
    - 속성
        - [x] 무늬

    - 기능
        - [x] 무늬 이름 반환

- [x] 결과 enum
    - 속성
        - [x] 포인트

    - 기능
        - [x] 포인트 반환

- [x] 현재까지 받은 카드 목록
    - 속성
        - [x] 현재까지 받은 카드 목록

    - 기능
        - [x] Ace 포함하는지 확인
        - [x] 처음 두 장의 카드 합 21인지 확인
        - [x] 모든 카드 합이 21을 초과하는지 확인
        - [x] 21을 기준으로 Ace 1 또는 11로 설정
        - [x] 카드 목록 반환
        - [x] 인자로 받은 카드 추가
        - [x] 인자로 받은 카드보다 합이 큰 지 확인
        - [x] 인자로 받은 카드보다 합이 작은 지 확인

- [x] 카드 덱
    - 속성
        - [x] 카드 52장

    - 기능
        - [x] 52장 카드 생성
        - [x] 처음 2장 카드 반환
        - [x] 추가 카드 반환

- [] 게임 시스템
    - 속성
        - [x] 딜러
        - [x] 플레이어 목록

    - 기능
        - [x] 플레이어들의 이름 반환
        - [x] 플레이어들의 모든 카드 반환
        - [x] 딜러의 모든 카드 반환
        - [x] 모든 플레이어가 참여 종료했는지 확인
        - [x] 참여 종료 상태가 아닌 플레이어 중 먼저 입력받은 플레이어 이름 반환
        - [x] 입력받은 이름의 플레이어 추가 카드 발급
            - [x] ERROR : y 또는 n이 아닌 응답을 전달받을 경우
        - [x] 딜러 추가 카드 발급
        - [] 참여자 점수 비교
