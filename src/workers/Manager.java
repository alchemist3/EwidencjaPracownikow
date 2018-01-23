package workers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Manager")
public class Manager extends Worker {
    private int bonus;
    private String card;
    private int costLimit;


    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }


    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }


    public int getCostLimit() {
        return costLimit;
    }

    public void setCostLimit(int costLimit) {
        this.costLimit = costLimit;
    }

    public Manager(int bonus, String card, int costLimit) {
        this.bonus = bonus;
        this.card = card;
        this.costLimit = costLimit;
        this.setPosition("Dyrektor");
    }

    public Manager() {
        this.setPosition("Dyrektor");
    }
}
