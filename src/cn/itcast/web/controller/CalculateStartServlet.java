package cn.itcast.web.controller;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

@WebServlet("/CalculateStartServlet")
public class CalculateStartServlet extends javax.servlet.http.HttpServlet {
    public static String[] ips = {"47.95.194.16","39.107.85.11"};

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        int ipLenth = ips.length;
        for(int i=0;i<ipLenth;i++){
            HttpURLConnection connection = null;
            try{
                URL u = new URL("http://"+ips[i]+":8080/block/CalculateServlet"+"?index="+Integer.toString(i));
                connection = (HttpURLConnection)u.openConnection();
                connection.setConnectTimeout(200);
                //connection.setReadTimeout(2000);
                connection.setRequestMethod("GET");

            }catch(MalformedURLException e){
                //e.printStackTrace();
            }catch(IOException e){
                //e.printStackTrace();
            }finally{
                if(connection != null){
                    connection.disconnect();
                }
            }
        }

        return;

    }

    public static int doCalculate(int index){
        int res = 0;
        for(int i=10000 * (index-1) + 1;i<=10000 * index;i++){
            for(int j=2;j<i;j++){
                if(i%j == 0){
                    break;
                }
                if(j == i-1){
                    res++;
                }
            }
        }

        return res;
    }


    public static String getRealIp() throws SocketException {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP

        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        InetAddress ip = null;
        boolean finded = false;// 是否找到外网IP
        while (netInterfaces.hasMoreElements() && !finded) {
            NetworkInterface ni = netInterfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();
            while (address.hasMoreElements()) {
                ip = address.nextElement();
                if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                    netip = ip.getHostAddress();
                    finded = true;
                    break;
                } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                    localip = ip.getHostAddress();
                }
            }
        }

        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }
}
