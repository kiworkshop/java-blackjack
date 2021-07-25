# java-blackjack

- [ ] 입력
    - [ ] 게임에 참여할 플레이어를 입력받는다.
    - [ ] 카드를 추가로 받을지 여부를 입력 받는다.
      - [ ] 입력 값이 (y / n) 이외의 값인 경우 예외가 발생한다.
    - [ ] 입력 값이 null 이거나 empty인 경우 예외가 발생한다.
    
- [ ] 출력
    - [ ] 초기 카드 배분 안내 메시지를 출력한다.
    - [ ] 딜러와 플레이어의 초기 카드를 출력한다.
        - [ ] 딜러는 첫 번째 1장, 플레이어는 2장 모두 출력한다.
    - [ ] 플레이어에게 추가 카드 배분 안내 메시지를 출력한다.
        - [ ] 카드를 추가로 받은 경우, 플레이어의 전체 카드를 출력한다.
        - [ ] 카드를 추가로 한 번도 받지 않은 경우, 플레이어의 전체 카드를 출력한다.
    - [ ] 딜러의 카드 합에 따라, 추가 카드 배분 여부 안내 메시지를 출력한다.
    - [ ] 딜러와 전체 플레이어의 최종 카드와 합을 출력한다.
    - [ ] 최종 승패를 출력한다.
  
- [x] 카드 무늬
    - [x] 스페이스, 다이아몬드, 하트, 클럽 4 가지의 무늬를 갖는다.
    
- [ ] 카드
    - [x] 무늬, 값, 시그니처를 갖는다.
      
    - [ ] 에이스 카드
        - [x] 1 또는 11을 의미하는 A 카드를 갖는다.
        - [ ] 카드 값을 결정해서 반환한다.

- [ ] 카드 모음
    - [ ] 카드 여러 장을 갖는다.
    - [ ] 카드 합을 계산한다.
    - [ ] 카드 순서를 갖는다.

- [ ] 덱
    - [x] 무늬별로 숫자, 메이저, 에이스 카드를 총 52장의 카드를 갖는다. 
    - [ ] 랜덤으로 중복되지 않는 숫자, 메이저, 에이스 카드를 반환한다.

- [ ] 딜러
    - [ ] 카드 모음을 갖는다.
    - [ ] 카드 합이 16 이하인 경우 한 장의 카드를 추가한다.
  
- [ ] 플레이어
    - [ ] 이름을 갖는다.
    - [ ] 카드 모음을 갖는다.
    - [ ] 추가 여부에 따라 한 장의 카드를 추가한다.
  
- [ ] 블랙잭 게임
    - [ ] 게임 딜러와 플레이어를 갖는다.
    - [ ] 게임 참여자에게 초기 카드 배분한다.
    - [ ] 게임 참여자마다 추가 카드 배분을 진행한다.

- [ ] 게임 승패
    - [ ] 딜러와 플레이어의 카드 합을 비교한다.
        - [ ] 카드의 합이 21과 더 가까운 참여자를 반환한다.
    - [ ] 모든 플레이어와 비교하여 딜러의 승/패 횟수를 반환한다.


