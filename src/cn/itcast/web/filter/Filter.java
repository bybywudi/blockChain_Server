package cn.itcast.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

//@WebFilter(filterName = "Filter")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    {
        try
        {
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            String method = httpRequest.getMethod().toLowerCase();
            if(method.equals("post"))
            {
                //如果是post，即表单方法，直接设置charset即可
                request.setCharacterEncoding("UTF-8");
            }
            else if(method.equals("get"))
            {
                //如果是get方法
                request.setCharacterEncoding("UTF-8");
                request = new HttpServletRequestWrapper((HttpServletRequest)request)
                {
                    public String getParameter(String str)
                    {
                        try
                        {
                            return new String(super.getParameter(str).getBytes("iso-8859-1"),"GBK");
                        }
                        catch(Exception e)
                        {
                            return null;
                        }

                    }
                };
            }

            chain.doFilter(request, response);
        }
        catch(Exception e){}
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
