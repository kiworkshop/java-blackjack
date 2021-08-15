package blackjack.domain.result;

import java.util.Comparator;

public class RuleComparator implements Comparator<Rule> {
    @Override
    public int compare(Rule rule1, Rule rule2) {
        int order1 = rule1.getVerificationOrder();
        int order2 = rule2.getVerificationOrder();

        return Integer.compare(order1, order2);
    }
}
