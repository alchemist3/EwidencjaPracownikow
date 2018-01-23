package server;

import xmlbinding.XMLBinding;

import java.io.*;
import java.net.*;
import java.rmi.*;


public class LoginServer {
    public static void main(String[] args) {

        XMLBinding.createSampleData("D:\\file.xml");



        try {
            server.ValidatorImpl aValidator = new server.ValidatorImpl();
            Naming.rebind("validator", aValidator);
            System.out.println("Login server open for business");

            ServerSocket ss = new ServerSocket(1234);

            while (true) {
                Socket s = ss.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
                String userName = dis.readUTF(); // wait for client to send
                String token = dis.readUTF();
                System.out.println(userName);
                System.out.println(token);


                if (aValidator.getMemberTokens().containsKey(userName) &&
                        aValidator.getMemberTokens().get(userName).equals(token)) {

                    File file;
                    FileInputStream fi = new FileInputStream(file = new File("D:\\file.xml"));
                    byte[] b = new byte[(int)file.length()];
                    fi.read(b, 0, b.length);

                    DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                    dos.writeInt(b.length);
                    dos.write(b);
                }
            }

        } catch (RemoteException re) {
            re.printStackTrace();
        } catch (MalformedURLException me) {
            System.out.println("MalformedURLException " + me);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
