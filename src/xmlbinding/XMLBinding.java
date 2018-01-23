package xmlbinding;

import workers.Manager;
import workers.Tradesman;
import workers.Worker;
import workers.WorkerList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLBinding {
    public static void createSampleData(String path) {
        Tradesman worker1 = new Tradesman();
        worker1.setId(20);
        worker1.setName("Tomasz");
        worker1.setSurname("Kowalczyk");
        worker1.setSalary(2000);
        worker1.setPosition("Handlowiec");
        worker1.setPhoneNumber("4577894");
        worker1.setProvision(5);
        worker1.setProvisionLimit(5000);

        Tradesman worker2 = new Tradesman();
        worker2.setId(21);
        worker2.setName("Zenon");
        worker2.setSurname("Testowy");
        worker2.setSalary(3000);
        worker2.setPosition("Handlowiec");
        worker2.setPhoneNumber("4457894");
        worker2.setProvision(3);
        worker2.setProvisionLimit(4000);

        Manager worker3 = new Manager();
        worker3.setId(22);
        worker3.setName("Witold");
        worker3.setSurname("Grabowski");
        worker3.setSalary(6000);
        worker3.setPosition("Dyrektor");
        worker3.setPhoneNumber("224457894");
        worker3.setCostLimit(6000);
        worker3.setCard("Visa458");
        worker3.setBonus(3000);


        List<Worker> workers = new ArrayList<Worker>();
        workers.add(worker1);
        workers.add(worker2);
        workers.add(worker3);

        WorkerList workerList = new WorkerList();
        workerList.setWorkers(workers);

        createXml(workerList, path);
    }



    public static void createXml(WorkerList workerList, String path) {
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(WorkerList.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(workerList, file);
            jaxbMarshaller.marshal(workerList, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static WorkerList getDataFromXml (String path) {

        try {

            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(WorkerList.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            WorkerList workers = (WorkerList) jaxbUnmarshaller.unmarshal(file);
            System.out.println(workers);

            return workers;

        } catch (JAXBException e) {
            e.printStackTrace();
            return new WorkerList();
        }

    }
}

