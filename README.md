# java-blackjack

# 규칙
- 블랙잭게임서비스
  -[] 속성
    - 참가자
    - 카드덱
- []기능
    - []Blackjack : 처음 두 장의 카드 합 21 => 승
    - [] Bust : 카드 합 21 초과 => 패
    - [] Push : 플레이어, 딜러 카드 합이 같음 => 무승부
    - [] Hit : 플레이어의 카드 2장의 합이 21을 초과하지 않을 경우, 추가 카드를 요청
    - [] Stay : 플레이어가 추가 카드를 원하지 않음, 딜러는 카드 합 17 이상이면 추가 카드를 얻을 수 없음
    - [] entroll : 참가자 등록
    - [] 승/페 계산
      최대 인원 : 5명


# 구현해야 할 기능 목록
- [] 입력
    - [] 플레이어 이름
    - [] 플레이어(딜러 제외) 추가 카드 요청 여부

- [] 출력
    - [] 각 플레이어(딜러 포함)에게 2장의 카드 지급 알림
    - [] 각 플레이어(딜러 포함)가 가진 카드
    - [] 딜러의 추가 카드 지급 여부 결과
    - [] 각 플레이어(딜러 포함)의 총 카드의 합
    - [] 최종 승패 결과

- [] 게임 참여자
    - 속성
        - [] 이름
        - [x] 카드 목록

    - 기능
        - [] 모든 카드 합을 계산
        - [] 처음 두 장의 카드 합 21인지 확인
        - [] 모든 카드 합이 21을 초과하는지 확인
        - [] Ace 포함하는지 확인
        - [] 21을 기준으로 Ace 1 또는 11로 설정
        - [] 카드 추가 요청
        - [] 초기 카드 2장을 받는다
            - [] Error: 3장 이상의 카드를 발급받으면 에러

- [] 카드
    - 속성
        - [] 끝수
        - [] 타입
        - [] 점수

    - 기능
        - [] 카드의 값 반환
        - [] Ace인지 확인

- [x] 카드 팩
    - 속성
        - [] 52개의 카드

    - 기능
        - [] 52개의 카드 생성
        - [] 2개의 랜덤 카드 반환
        - [] 추가 카드 반환
        - [] ERROR: 반환할 수 있는 카드가 없을 경우
    