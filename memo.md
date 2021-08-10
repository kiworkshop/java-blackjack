domain

- Card, Deck, Player, Dealer, Game, Statistics
    - Game : 카드 분배 역할 (카드 분배 조건도 이 객체에서 판단)
    - Statistics : 카드분배가 완료된 후 점수계산 및 승패 결과 도출

View

- 플레이어 이름을 입력 받고 결과를 출력

Controller

- 게임 전체 흐름 제어 (입력, 게임실행, 출력)

### Enum

1. 게임컨트롤러 (카드분배- 질문)
    - 답(YES, NO)

2. 게임결과 (결과 승패를 판단)
    - 승(플레이어 점수, 딜러점수, 승(플), 패(딜러))
    - 패(플레이어 점수, 딜러점수, 패(플), 승(딜러))
    - 무승부(플레이어 점수, 딜러점수, 무(플,딜러))

domain

- Card, Deck, Player, Dealer, Score(계산), GameResult(승패도출)
- Answer(질문답), Results(승패무)

View

- inputview: 입력받는 것(질문에 대한 답을 받는 부분)
- outputview: 출력하는 것

Controller

- 카드를 분배(drawCard), 더 받을지 질문, 하나카드를 분배...
- 흐름제어

# Ace 카드 처리 로직

Player -> 21 한계치 Dealer -> 17 한계치

모든 게임스코어합은 -> 21이 게임의 종료 지점

Score 계산 && 카드를 더 뽑게할지말지

- 카드의 Score가 계산로직으로 21(Player) 17(Dealer) 되기전까지 카드를 더 뽑을지 질문

## 계산로직

1. 카드 목록에 포함된 에이스 카드의 숫자를 추출한다. -> n
2. n+1의 길이를 가지는 배열을 생성하고, 에이스 카드를 1로 계산한 카드합과 거기에 차례대로 10을 더한 값을 배열에 입력한다.
3. 배열의 숫자들 중 21을 초과하지 않으면서 가장 큰 값을 취한다.

ex) Ace, Ace, 8일 경우 int[3] = {10, 20, 30} -> 20을 숫자합으로 계산

### 게임종료

카드를 한 장 뽑을 때마다 모든 경우의 수를 계산. Ace를 1로, 혹은 11로 계산하는 경우의 수를 다 생각해서 모든 경우의 수 중 카드합의 최소값이 한계치를 넘는 경우에 카드를 그만 뽑도록.

Ace 7, 9

27을 취한다. 카드를 더 못뽑음.

17

Ace, 7, 9, 4

-> 21

## 카드 뽑기

<딜러>
처음에 2장의 카드를 받고 카드합이 16 이하이면 한 장을 더 뽑는다. 에이스 1장은 11로 계산하고 나머지 에이스는 1로 계산한다. -> 에이스가 포함되어 있을 경우 카드합에 10을 더해준다

<에이스가 포함되어 있지 않을 경우>

카드합이 16 이하이면 카드를 뽑음.

<에이스가 포함되어 있는 경우>

카드합에 10을 더한 값이 16 이하이면 카드를 뽑음.

## 카드 뽑기 및 에이스가 여러개 포함될 가능성이 있는 카드덱의 합 계산

// 카드값의 합(ace가 11)
int scoreOfAceAsEleven; // 에이스의 개수 int aceCount; // Ace카드값의 차이 int DiFFERENCE_OF_ACE_VALUE = 10;

    calcaluateResult(){
        while (canCountAceAsOne) {
            카드값의 합 scoreOfAceAsEleven = scoreOfAceAsOne(scoreOfAsAsEleven);
            aceCount--;
        }

		return scoreOfAceAsEleven;
    }

	boolean canCountAceAsOne(int 카드값의 합, 에이스의 개수){
		에이스가 하나라도 있음 > 0  && 카드 합이 21(상수선언)을 넘음(ace를 1로 취급가능)}
	}

	int scoreOfAceAsOne(int 카드값의 합){
	    (ace를 11로 취급한) scoreOfAceAsEleven 카드값의 합 - 10
	}

- 17 dealer = threshould

컨트롤러에서 딜러에게 boolean Drawble(){ return calculateResult() < 17 } drawCard()

- 21 player 컨트롤러에서 플레이어에게 더 받을건지 묻는 질문 boolean 플레이어의 답 Yes && Drawable() { return calculateResult() < 21 } drawCard()


