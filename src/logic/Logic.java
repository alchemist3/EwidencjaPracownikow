package logic;

import workers.Manager;
import workers.Tradesman;
import workers.Worker;
import workers.WorkerList;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logic {

    public static List<Worker> getWorkers(String[] loginData) {

        // workers into class
        List<Worker> workers = new ArrayList<>();

        try {
            // Getting data from database
            // 1. Get a connection to database
            Connection myConn = DriverManager.getConnection(loginData[0], loginData[1], loginData[2]);

            // 2. Creata a statement
            Statement myStmt = myConn.createStatement();

            // 3. Execute SQL query
            ResultSet rs = myStmt.executeQuery("select * from pracownicy");

            ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();

            while (rs.next()) {
                if (rs.getString("stanowisko").equals("Dyrektor")) {
                    Manager manager = new Manager();
                    manager.setId(rs.getInt("id"));
                    manager.setName(rs.getString("imie"));
                    manager.setSurname(rs.getString("nazwisko"));
                    manager.setSalary(rs.getInt("wynagrodzenie"));
                    manager.setPhoneNumber(rs.getString("telefon"));
                    manager.setBonus(rs.getInt("dodatek_sluzbowy"));
                    manager.setCard(rs.getString("karta_sluzbowa"));
                    manager.setCostLimit(rs.getInt("limit_kosztow"));

                    workers.add(manager);
                } else if (rs.getString("stanowisko").equals("Handlowiec")) {
                    Tradesman tradesman = new Tradesman();
                    tradesman.setId(rs.getInt("id"));
                    tradesman.setName(rs.getString("imie"));
                    tradesman.setSurname(rs.getString("nazwisko"));
                    tradesman.setSalary(rs.getInt("wynagrodzenie"));
                    tradesman.setPhoneNumber(rs.getString("telefon"));
                    tradesman.setProvision(rs.getInt("prowizja"));
                    tradesman.setProvisionLimit(rs.getInt("limit_prowizji"));

                    workers.add(tradesman);

                }


            }

        } catch (Exception exp) {
        }


        //return resultList;
        return workers;
    }

    public static boolean addWorker(String[] loginData, String[] inputData) {
        try {
            // Inserting data into database
            // 1. Get a connection to database
            Connection myConn = DriverManager.getConnection(loginData[0], loginData[1], loginData[2]);

            // 2. Prepare statement
            if (inputData[3].equals("Dyrektor")) {
                String sql = "insert into pracownicy "
                        + "(imie, nazwisko, wynagrodzenie, stanowisko, telefon, dodatek_sluzbowy, karta_sluzbowa, limit_kosztow)"
                        + " values(?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement myStmt = myConn.prepareStatement(sql);
                myStmt.setString(1, inputData[0]);
                myStmt.setString(2, inputData[1]);
                myStmt.setInt(3, Integer.parseInt(inputData[2]));
                myStmt.setString(4, inputData[3]);
                myStmt.setString(5, inputData[4]);
                myStmt.setInt(6, Integer.parseInt(inputData[5]));
                myStmt.setString(7, inputData[6]);
                myStmt.setInt(8, Integer.parseInt(inputData[7]));
                myStmt.executeUpdate();

            } else {
                String sql = "insert into pracownicy "
                        + "(imie, nazwisko, wynagrodzenie, stanowisko, telefon, prowizja, limit_prowizji)"
                        + " values(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement myStmt = myConn.prepareStatement(sql);
                myStmt.setString(1, inputData[0]);
                myStmt.setString(2, inputData[1]);
                myStmt.setInt(3, Integer.parseInt(inputData[2]));
                myStmt.setString(4, inputData[3]);
                myStmt.setString(5, inputData[4]);
                myStmt.setInt(6, Integer.parseInt(inputData[5]));
                myStmt.setInt(7, Integer.parseInt(inputData[6]));
                myStmt.executeUpdate();
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Map<String, String> getWorker(String[] loginData, int workerId) {

        Map<String, String> resultList = new HashMap<String, String>();

        try {
            // Getting data from database
            // 1. Get a connection to database
            Connection myConn = DriverManager.getConnection(loginData[0], loginData[1], loginData[2]);

            // 2. Creata a statement
            String selectSQL = "select * from pracownicy where id=?";
            PreparedStatement preparedStatement = myConn.prepareStatement(selectSQL);
            preparedStatement.setInt(1, workerId);

            // 3. Execute SQL query
            ResultSet rs = preparedStatement.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();

            rs.next();
            for (int i = 1; i <= columnCount; i++) {
                resultList.put(metaData.getColumnName(i), rs.getString(i));
            }
        } catch (Exception exp) {
        }
        return resultList;
    }

    public static boolean editWorker(String[] loginData, String[] inputData) {
        try {
            // Inserting data into database
            // 1. Get a connection to database
            Connection myConn = DriverManager.getConnection(loginData[0], loginData[1], loginData[2]);

            // 2. Prepare statement
            if (inputData[4].equals("Dyrektor")) {
                String sql = "update pracownicy "
                        + "set imie=?, nazwisko=?, wynagrodzenie=?, stanowisko=?, telefon=?,"
                        + "dodatek_sluzbowy=?, karta_sluzbowa=?, limit_kosztow=?,"
                        + "prowizja=NULL, limit_prowizji=NULL"
                        + " where id=?";
                PreparedStatement myStmt = myConn.prepareStatement(sql);
                myStmt.setString(1, inputData[1]);
                myStmt.setString(2, inputData[2]);
                myStmt.setInt(3, Integer.parseInt(inputData[3]));
                myStmt.setString(4, inputData[4]);
                myStmt.setString(5, inputData[5]);
                myStmt.setInt(6, Integer.parseInt(inputData[6]));
                myStmt.setString(7, inputData[7]);
                myStmt.setInt(8, Integer.parseInt(inputData[8]));
                myStmt.setInt(9, Integer.parseInt(inputData[0]));
                System.out.println("ok");

                myStmt.executeUpdate();

            } else {
                String sql = "update pracownicy "
                        + "set imie=?, nazwisko=?, wynagrodzenie=?, stanowisko=?, telefon=?,"
                        + "dodatek_sluzbowy=NULL, karta_sluzbowa=NULL, limit_kosztow=NULL,"
                        + "prowizja=?, limit_prowizji=?"
                        + " where id=?";
                PreparedStatement myStmt = myConn.prepareStatement(sql);
                myStmt.setString(1, inputData[1]);
                myStmt.setString(2, inputData[2]);
                myStmt.setInt(3, Integer.parseInt(inputData[3]));
                myStmt.setString(4, inputData[4]);
                myStmt.setString(5, inputData[5]);
                myStmt.setInt(6, Integer.parseInt(inputData[6]));
                myStmt.setInt(7, Integer.parseInt(inputData[7]));
                myStmt.setInt(8, Integer.parseInt(inputData[0]));

                myStmt.executeUpdate();
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean deleteWorker(String[] loginData, String workerId) {
        try {
            // 1. Get a connection to database
            Connection myConn = DriverManager.getConnection(loginData[0], loginData[1], loginData[2]);

            // 2. Prepare statement
            String sql = "delete from pracownicy where id=?";
            PreparedStatement myStmt = myConn.prepareStatement(sql);
            myStmt.setInt(1, Integer.parseInt(workerId));
            myStmt.executeUpdate();

            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean addWorkers(WorkerList workers, String[] loginData) {
        try {
            // Inserting data into database
            // 1. Get a connection to database
            Connection myConn = DriverManager.getConnection(loginData[0], loginData[1], loginData[2]);


            for (Worker worker : workers.getWorkers()) {
                if (worker instanceof Manager) {
                    String sql = "insert into pracownicy "
                            + "(imie, nazwisko, wynagrodzenie, stanowisko, telefon, dodatek_sluzbowy, karta_sluzbowa, limit_kosztow)"
                            + " values(?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement myStmt = myConn.prepareStatement(sql);
                    myStmt.setString(1, worker.getName());
                    myStmt.setString(2, worker.getSurname());
                    myStmt.setInt(3, worker.getSalary());
                    myStmt.setString(4, worker.getPosition());
                    myStmt.setString(5, worker.getPhoneNumber());
                    myStmt.setInt(6, ((Manager) worker).getBonus());
                    myStmt.setString(7, ((Manager) worker).getCard());
                    myStmt.setInt(8, ((Manager) worker).getCostLimit());
                    myStmt.executeUpdate();

                } else if (worker instanceof Tradesman) {

                    String sql = "insert into pracownicy "
                            + "(imie, nazwisko, wynagrodzenie, stanowisko, telefon, prowizja, limit_prowizji)"
                            + " values(?, ?, ?, ?, ?, ?, ?)";

                    PreparedStatement myStmt = myConn.prepareStatement(sql);
                    myStmt.setString(1, worker.getName());
                    myStmt.setString(2, worker.getSurname());
                    myStmt.setInt(3, worker.getSalary());
                    myStmt.setString(4, worker.getPosition());
                    myStmt.setString(5, worker.getPhoneNumber());
                    myStmt.setInt(6, ((Tradesman) worker).getProvision());
                    myStmt.setInt(7, ((Tradesman) worker).getProvisionLimit());
                    myStmt.executeUpdate();

                }

            }


            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
