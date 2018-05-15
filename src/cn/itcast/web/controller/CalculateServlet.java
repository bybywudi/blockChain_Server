package cn.itcast.web.controller;

import cn.itcast.service.impl.BusinessServiceImpl;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@WebServlet("/CalculateServlet")
public class CalculateServlet extends javax.servlet.http.HttpServlet {
    public static final int PROBLEMSIZE = 100;

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

        if(!service.findResIndex(index,mid)){
            service.addNewResBlock(index,ip,result,qid,mid,host);
        }
        //service.addNewResBlock(index,ip,result);
        int i=Integer.parseInt(index);
        for(;i<PROBLEMSIZE;i++){
            if(!service.findResIndex(Integer.toString(i),mid)){
                HttpURLConnection connection = null;
                try{
                    URL u = new URL("http://"+ip+":8080/block/CalculateServlet"+"?index="+Integer.toString(i)+"?qid="+qid+"?mid="+mid+"?host="+host);
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
        }

    }


}
