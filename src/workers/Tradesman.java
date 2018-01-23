package workers;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Tradesman")
public class Tradesman extends Worker {
    private int provision;
    private int provisionLimit;

    public int getProvision() {
        return provision;
    }

    public void setProvision(int provision) {
        this.provision = provision;
    }

    public int getProvisionLimit() {
        return provisionLimit;
    }

    public void setProvisionLimit(int provisionLimit) {
        this.provisionLimit = provisionLimit;
    }

    public Tradesman(int provision, int provisionLimit) {
        this.provision = provision;
        this.provisionLimit = provisionLimit;
        this.setPosition("Handlowiec");
    }

    public Tradesman(){
        this.setPosition("Handlowiec");

    }
}
