package com.cbt.utilities;

import java.net.HttpURLConnection;
import java.net.URL;

public class VerifyLinks {


    public static void verifyLinkValid(String link)
    {
        try
        {
            URL url = new URL(link);
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(2000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode()==200){
                System.out.println(link+" - "+httpURLConnect.getResponseMessage());
            }
            if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND){
                System.out.println(link+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
            }
        } catch (Exception e) {

        }
    }

}
