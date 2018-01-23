package workers;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Worker")
@XmlSeeAlso({Manager.class, Tradesman.class})
public class Worker {
    protected int id;
    protected String name;
    protected String surname;
    protected int salary;
    protected String position;
    protected String phoneNumber;

    public Worker() {
    }

    public Worker(int id, String name, String surname, int salary, String position, String phoneNumber) {

        this.id = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.position = position;
        this.phoneNumber = phoneNumber;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
