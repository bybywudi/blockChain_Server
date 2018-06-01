package cn.itcast.web.controller;

import cn.itcast.service.impl.BusinessServiceImpl;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@WebServlet("/CalculateServlet")
public class CalculateServlet extends javax.servlet.http.HttpServlet {
    public static final int PROBLEMSIZE = 100;

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
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        String index = request.getParameter("index");
        String result = request.getParameter("result");
        String ip = request.getParameter("ip");
        String qid = request.getParameter("qid");
        String mid = request.getParameter("mid");
        String host = request.getParameter("host");

        BusinessServiceImpl service = new BusinessServiceImpl();
        int userTotalNum = service.getUsersTotalNumber() - 1;
        if(!service.findResIndex(index,mid)){
            service.addNewResBlock(index,ip,result,qid,mid,host);

            List<String> list = service.getUserIps();
            String[] ips = new String[list.size()];
            list.toArray(ips);
            int ipLenth = ips.length;
            for(int i=0;i<ipLenth;i++){
                CalculateServlet.Thread_Http_Get t = new CalculateServlet.Thread_Http_Get("http://"+ips[i]+":8080/block/BlockUpdateServlet"+"?index="+index+"&qid="+qid+"&mid="+mid+"&host="+host+"&result="+result+"&ip="+ip);
                t.start();
            }

            service.addUserCoin(1,ip);
        }
        //service.addNewResBlock(index,ip,result);
        int i=Integer.parseInt(index);
        if((i+userTotalNum) > PROBLEMSIZE){
            i = i+userTotalNum+1-PROBLEMSIZE;
        }
        int loopNum = 0;//控制循环次数的变量
        for(;i<PROBLEMSIZE;i=i+userTotalNum){
            if(!service.findResIndex(Integer.toString(i),mid)){
                HttpURLConnection connection = null;
                try{
                    URL u = new URL("http://"+ip+":8080/block/CalculateServlet"+"?index="+Integer.toString(i)+"&qid="+qid+"&mid="+mid+"&host="+host);
                    connection = (HttpURLConnection)u.openConnection();
                    connection.setConnectTimeout(200);
                    //connection.setReadTimeout(2000);
                    connection.setRequestMethod("GET");
                    System.out.println(connection.getResponseCode());
                    System.out.println(u.toString());
                }catch(MalformedURLException e){
                    //e.printStackTrace();
                }catch(IOException e){
                    //e.printStackTrace();
                }finally{
                    if(connection != null){
                        connection.disconnect();
                    }
                    return;
                }
            }
            if((i+userTotalNum) >= PROBLEMSIZE){
                i = i+userTotalNum+1-PROBLEMSIZE;
                loopNum++;
                if(loopNum >= userTotalNum){
                    return;
                }
            }
        }

    }


}
