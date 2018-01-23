package workers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "workers")



public class WorkerList {

    private List<Worker> workers;



    @XmlElements({
            @XmlElement(name="manager", type=Manager.class),
            @XmlElement(name="tradesman", type=Tradesman.class)
    })
    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }


}
