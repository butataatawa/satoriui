package net.atomshare.satori.gui;
import javax.net.ssl.HttpsURLConnection;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.*;
public class ContestList {
	String login = "farqd";
    String password = "korwinkrul";
    String encoding;
	  public ArrayList<String> listContests()
	    {
		  ArrayList<String> list=new ArrayList<String>();
	        JSONArray clist = new JSONArray();
	        

	        try {
	            URL url = new URL ("https://satori.atomshare.net/contests"); // "http://ip:port/login"
	            String encoding = Base64.encodeBase64String(  (login+":"+password).getBytes(Charset.forName("UTF-8")));

	            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");
	            connection.setDoOutput(true);
	            connection.setRequestProperty  ("Authorization", "Basic " + encoding);
	            InputStream content = (InputStream)connection.getInputStream();
	            BufferedReader in   =
	                    new BufferedReader (new InputStreamReader (content));
	            String line;

	            line=in.readLine();
	            clist= (JSONArray) JSONValue.parse(line);

	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	        for(int i=0 ;i<clist.size(); i++)
	        {
	            JSONObject K = (JSONObject) clist.get(i);
	            if(K.get("contestant") != null) {
	                JSONObject cont = (JSONObject) K.get("contest");
	                if((Boolean)cont.get("archived")==false)
	                list.add(cont.get("name"));
	            }
	        }
	        return list;
	    }
}
