package cn.itcast.web.controller;

import cn.itcast.service.impl.BusinessServiceImpl;
import cn.itcast.utils.XmlUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/TestCalculateStartServlet")
public class TestCalculateStartServlet extends javax.servlet.http.HttpServlet {

    class Thread_Http_Get extends Thread{
        private String httpurl;

        public Thread_Http_Get(String httpurl){
            this.httpurl = httpurl;
        }

        public void http_Get(){
            try {

                HttpURLConnection connection = null;
                URL url = new URL(httpurl);
                connection = (HttpURLConnection)url.openConnection();
                connection.connect();
                System.out.println(connection.getResponseCode());
                connection.disconnect();
            }catch(MalformedURLException e){
                //e.printStackTrace();
            }catch(IOException e){
                //e.printStackTrace();
            }
        }

        public void run() {
            http_Get();
        }

    }

    //public static String[] ips = {"47.95.194.16","39.107.83.2","39.106.194.129"};
    public String localIp = getUserIp();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        BusinessServiceImpl service = new BusinessServiceImpl();

        List<String> list = service.getUserIps();
        String[] ips = new String[list.size()];
        list.toArray(ips);

        int ipLenth = ips.length;
        int j = 0;

        String qid = request.getParameter("qid");
        String mid = service.getMid();
        String host = service.getUserIp();

        PrintWriter out = response.getWriter();
        out.print(ips[0]);
        out.print(ips[1]);
        out.print(host);
        out.write(ips[0]);
        out.write(ips[1]);
        out.write(host);
        System.out.println(ips[0]);
        System.out.println(ips[1]);
        System.out.println(host);
       /* for(int i=0;i<ipLenth;i++){
            Thread_Http_Get t = new Thread_Http_Get("http://"+ips[i]+":8088/block/CalculateServlet"+"?index="+Integer.toString(i)+"&qid="+qid+"&mid="+mid+"&host="+host);
            t.start();
        }*/

        return;
        /*for(int i=0;i<ipLenth;i++){
            HttpURLConnection connection = null;
            try{
                URL u = new URL("http://"+ips[i]+":8080/block/CalculateServlet"+"?index="+Integer.toString(j));
                connection = (HttpURLConnection)u.openConnection();
                connection.setConnectTimeout(2);
                //connection.setReadTimeout(2000);
                connection.setRequestMethod("GET");
                j = j+50;
                System.out.println(connection.getResponseCode());
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
*/
        /*HttpURLConnection connection1 = null;
        HttpURLConnection connection2 = null;
        try{
            URL u1 = new URL("http://"+ips[0]+":8080/block/CalculateServlet"+"?index="+Integer.toString(j));
            connection1 = (HttpURLConnection)u1.openConnection();
            connection1.setConnectTimeout(200);
            //connection.setReadTimeout(2000);
            connection1.setRequestMethod("GET");

            j = j+50;
            URL u2 = new URL("http://"+ips[1]+":8080/block/CalculateServlet"+"?index="+Integer.toString(j));
            connection2 = (HttpURLConnection)u2.openConnection();
            connection2.setConnectTimeout(200);
            connection2.setRequestMethod("GET");

            System.out.println(connection2.getResponseCode());
            System.out.println(connection1.getResponseCode());
        }catch(MalformedURLException e){
            //e.printStackTrace();
        }catch(IOException e){
            //e.printStackTrace();
        }finally{
            if(connection1 != null){
                connection1.disconnect();
            }
            if(connection2 != null){
                connection2.disconnect();
            }
        }*/


    }

 /*   public static int doCalculate(int index){
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
    }*/

    public String getUserIp() {
        try {
            Document document = XmlUtils.getLocalDocument();
            Element e = (Element) document.selectSingleNode("//local[@attr='localip']");
            if(e==null) {
                return null;
            }
            return e.attributeValue("id");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
