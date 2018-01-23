package server;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.security.SecureRandom;

public class ValidatorImpl extends UnicastRemoteObject implements server.Validator {
    Map memberMap;
    Map memberTokens;

    public ValidatorImpl() throws RemoteException {
        memberMap = new HashMap();
        memberTokens = new HashMap();
        memberMap.put("John", "password");
    }

    @Override
    public String validate(String aUserName, String aPassword) throws RemoteException {
        if (getMemberMap().containsKey(aUserName) &&
                getMemberMap().get(aUserName).equals(aPassword)) {

            SecureRandom random = new SecureRandom();
            byte bytes[] = new byte[20];
            random.nextBytes(bytes);
            String token = bytes.toString();

            memberTokens.put(aUserName, token);
            return token;
        }
        return "";
    }

    public Map getMemberMap() {
        return memberMap;
    }
    public Map getMemberTokens() {
        return memberTokens;
    }
}
