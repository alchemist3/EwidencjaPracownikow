package client;

import server.Validator;
import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.*;

public class ValidatorClient {
    public static void run(String userName, String password, String serverName, String serverPort) throws Exception{


//        String userName = "John";
//        String password = "password";


        String token = "";

        try {
            Object remote = Naming.lookup("rmi://localhost/validator");
            Validator reply = (Validator) remote;
            token = reply.validate(userName, password);

            System.out.println(token);
        } catch (MalformedURLException me) {
            System.out.println("URL not valid");
        } catch (NotBoundException nbe) {
            System.out.println("Could not find requested object on the server");
        } catch (RemoteException re) {
            re.printStackTrace();
        }



        try {
            Socket s = new Socket(serverName, Integer.parseInt(serverPort)); // wait for server to accept
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(userName);
            dos.writeUTF(token);
            dos.flush();


            DataInputStream dis = new DataInputStream(s.getInputStream());


            int length = dis.readInt();                    // read length of incoming message
            byte[] data = new byte[length];
            if (length > 0) {
                dis.readFully(data, 0, data.length); // read the message
            }

            FileOutputStream fos = new FileOutputStream("D:\\response.xml");
            fos.write(data, 0, data.length);
            System.out.println("zapisano");


        } catch (Exception e) {
            System.out.println(e);
        }

        if (token.equals("")){
            throw new Exception();
        }
    }
}
