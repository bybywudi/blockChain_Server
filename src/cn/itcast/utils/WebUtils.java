package cn.itcast.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

public class WebUtils {
	public static <T> T request2Bean(HttpServletRequest request, Class<T> beanClass) {
		try {
			//1.创建要封装数据的bean
			T bean = beanClass.newInstance();

			//2.将数据封装进bean中
			Enumeration e = request.getParameterNames();
			while(e.hasMoreElements()) {
				String name = (String) e.nextElement();	//	username password emial birthday
				String value = request.getParameter(name);	//aaa  123  aaa@sina.com
				BeanUtils.setProperty(bean, name, value);
			}

			return bean;

		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*private String username;
	private String password;
	private String password2;
	private String email;
	private String birthday;
	private String nikename;
	private Map errors = new HashMap();

	private String id;
	private String username;
	private String password;
	private String email;
	private Date birthday;
	private String nikename;
	*/
	public static void copyBean(Object src, Object dest) {
		//注册日期转换器
		ConvertUtils.register(new Converter(){
			public Object convert(Class type, Object value) {

				if(value==null) {
					return null;
				}
				String str = (String) value;
				if(str.trim().equals("")) {
					return null;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return df.parse(str);
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		}, Date.class);

		try {
			BeanUtils.copyProperties(dest, src);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//产生全球唯一的id
	public static String generateID() {
		
		return UUID.randomUUID().toString();
		
	}
}

