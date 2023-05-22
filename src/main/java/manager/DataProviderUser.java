package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> loginData(){
        List<Object[]>list= new ArrayList<>();
        list.add(new Object[]{"00220719@gmail.com","OO220719!oo"});
        list.add(new Object[]{"sara@gmail.com","SarabeN12345$"});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginFile() throws IOException {
        List<Object[]>list= new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
        String line = reader.readLine();
        reader.readLine();
        while (line!=null){
            String[] all = line.split(",");
            list.add(new Object[]{new User().setEmail(all[0]).setPassword(all[1])});
            line= reader.readLine();
        }
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginSuccess1(){
        List<Object[]>list= new ArrayList<>();
        list.add(new Object[]{new User().setEmail("00220719@gmail.com").setPassword("OO220719!oo")});
        list.add(new Object[]{new User().setEmail("00220719@gmail.com").setPassword("OO220719!oo")});

        return list.iterator();
    }
}
