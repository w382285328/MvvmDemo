package com.huantai.qhytims.tools;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;


import com.huantai.qhytims.application.MyApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PingUtil {
    /**
     * 根据当前网关能否ping通判断是否在打卡范围内
     *
     * @return
     */
    public static boolean isPing(String ipString) {
        // TODO Auto-generated method stub
        boolean value = false;
        WifiManager wifiManager = (WifiManager) MyApplication.getInstance().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        DhcpInfo info=wifiManager.getDhcpInfo();

        int gateway=info.gateway;

        String ip=intToIp(gateway);
        if(ip.equals(ipString)){
            value = true;
            return value;
        }
        Process p = null;
        BufferedReader in = null;
        try {
            p = Runtime.getRuntime().exec("ping -c 1 -w 1 "+ ipString);//ping -c 2 -w 3  + ipString
            InputStream input = p.getInputStream();
            in = new BufferedReader(new InputStreamReader(input));
            StringBuffer stringBuffer = new StringBuffer();
            String content = "";
            while ((content = in.readLine()) != null) {
                stringBuffer.append(content);
            }
            // PINGք״̬
            int status = p.waitFor();
            if (status == 0) {
                value = true;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            if (p != null) {
                p.destroy();
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }

    public static String intToIp(int addr) {
        return ((addr & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF) + "." +
                ((addr >>>= 8) & 0xFF));
    }



}
