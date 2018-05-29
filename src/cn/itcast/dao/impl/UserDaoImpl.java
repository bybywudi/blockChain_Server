package cn.itcast.dao.impl;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.Block;
import cn.itcast.domain.ProblemBlock;
import cn.itcast.domain.User;
import cn.itcast.utils.XmlUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
	
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.UserDao#add(cn.itcast.domain.User)
	 */
	@Override
	public void add(User user) {
		try {
			Document document = XmlUtils.getDocument();
			Element root = document.getRootElement();
			
			Element user_tag = root.addElement("user");
			user_tag.setAttributeValue("id", user.getId());
			user_tag.setAttributeValue("username", user.getUsername());
			user_tag.setAttributeValue("password", user.getPassword());
			user_tag.setAttributeValue("email", user.getEmail());
			user_tag.setAttributeValue("birthday", user.getBirthday()==null?"":user.getBirthday().toLocaleString());
			user_tag.setAttributeValue("nikename", user.getNikename());
			
			XmlUtils.write2UsersXml(document);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void addNewBolck(Block block,int nowid){
		try {
			Document document = XmlUtils.getBlocksDocument();
			Element root = document.getRootElement();

			Element user_tag = root.addElement("block");
			user_tag.setAttributeValue("id", block.getId().toString());
			user_tag.setAttributeValue("userId", block.getUserId());
			user_tag.setAttributeValue("information1", block.getInformation1());
			user_tag.setAttributeValue("information2", block.getInformation2());

			Element e = (Element) root.selectSingleNode("nowid");
			Attribute attr = e.attribute("id");
			attr.setValue(Integer.toString(nowid));

			XmlUtils.write2BlocksXml(document);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//获得用户总数
	public int getUsersTotalNumber(){
		try {
			Document document = XmlUtils.getBlocksDocument();
			Element root = document.getRootElement();
			Element e = (Element) root.selectSingleNode("nowid");
			Attribute attr = e.attribute("id");

			return Integer.parseInt(attr.getValue());

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//获得所有用户的IP存在list中
	public List<String> getUserIps(){
		try {
			Document document = XmlUtils.getBlocksDocument();

			int usersTotalNumber = getUsersTotalNumber();
			List<String> list = new ArrayList<String>();
			String localIp = getUserIp();
			for(int i=1;i<=usersTotalNumber;i++){
				Element e = (Element) document.selectSingleNode("//user[@id='"+Integer.toString(i)+"']");
				Attribute attr = e.attribute("ip");
				String ip = attr.getValue();
				if(!(ip.equals(localIp))){
					list.add(ip);
				}
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//获得任务ID
    public String getMid(){
        try {
            Document document = XmlUtils.getLocalDocument();
            Element root = document.getRootElement();

            Element e = (Element) root.selectSingleNode("nowid");
            Attribute attr = e.attribute("id");
            int mid = Integer.parseInt(e.attributeValue("id")) + 1;//任务ID自增
            attr.setValue(Integer.toString(mid));

            XmlUtils.write2LocalXml(document);

            return Integer.toString(mid);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	public void addUserCoin(int i,String uip){
		try {
			/*Document document = XmlUtils.getLocalDocument();
			Element e = (Element) document.selectSingleNode("//local[@attr='coin']");
			Attribute attr = e.attribute("id");
			int newCoin = Integer.parseInt(e.attributeValue("id")) + i;//任务ID自增
			attr.setValue(Integer.toString(newCoin));
			XmlUtils.write2LocalXml(document);*/

			Document document2 = XmlUtils.getBlocksDocument();
			//Element e2 = (Element) document2.selectSingleNode("//user[@id='1']");//user[@id='1']
			//e2 = (Element) document.selectSingleNode("//user[@id='"+user+"']");

			Element e2 = (Element) document2.selectSingleNode("//user[@ip='"+uip+"']");
			//System.out.println(uip);
			Attribute attr2 = e2.attribute("coin");
			int newCoin = Integer.parseInt(e2.attributeValue("coin")) + i;//任务ID自增
			attr2.setValue(Integer.toString(newCoin));
			XmlUtils.write2BlocksXml(document2);

			/*Attribute attr2 = e2.attribute("coin");
			attr2.setValue(Integer.toString(newCoin));
			XmlUtils.write2BlocksXml(document2);*/

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void addC(int i,String uip){
		try {

			Document document = XmlUtils.getBlocksDocument();
			Element e2 = (Element) document.selectSingleNode("//user[@ip='"+uip+"']");
			System.out.println(uip);
			Attribute attr2 = e2.attribute("coin");
			attr2.setValue(Integer.toString(1));
			XmlUtils.write2BlocksXml(document);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean startCal(String uip){
		try {
			Document document = XmlUtils.getBlocksDocument();
			Element e2 = (Element) document.selectSingleNode("//user[@ip='"+uip+"']");
			Attribute attr2 = e2.attribute("coin");
			int newCoin = Integer.parseInt(e2.attributeValue("coin")) - 100;
			if(newCoin >= 0){
				attr2.setValue(Integer.toString(newCoin));
				XmlUtils.write2BlocksXml(document);
				return true;
			}else{
				return false;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

    //获得用户IP
    public  String getUserIp() {
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

	public int blockHash(int id){
		return id + 1;
	}

	public boolean isHashRight(int id,String userId){
		try {
			Document document = XmlUtils.getBlocksDocument();
			Element root = document.getRootElement();
			Element e = (Element) root.selectSingleNode("nowid");
			/*String text=root.elementText("nowid");
			System.out.println(text);*/
			Attribute attr = e.attribute("id");
			int nowid = Integer.parseInt(attr.getValue());

			if(blockHash(nowid) == id){

				Block b = new Block();
				b.setId(id);
				b.setUserId(userId);
				addNewBolck(b,id);

				return true;
			}else{
				return false;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.UserDao#find(java.lang.String, java.lang.String)
	 */
	@Override
	public User find(String username,String password) {
		try {
			Document document = XmlUtils.getDocument();
			Element e = (Element) document.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");

			if(e==null) {
				return null;
			}
			User user = new User();
			String date = e.attributeValue("birthday");
			if(date==null || date.equals("")) {
				user.setBirthday(null);
			}else {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				user.setBirthday(df.parse(date));
			}

				user.setEmail(e.attributeValue("email"));
				user.setId(e.attributeValue("id"));
				user.setNikename(e.attributeValue("nikename"));
				user.setPassword(e.attributeValue("password"));
				user.setUsername(e.attributeValue("username"));
				
				return user;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//����ע����û��Ƿ������ݿ��д���
	/* (non-Javadoc)
	 * @see cn.itcast.dao.impl.UserDao#find(java.lang.String)
	 */
	@Override
	public boolean find(String username) {
		try {
			Document document = XmlUtils.getDocument();
			Element e = (Element) document.selectSingleNode("//user[@username='"+username+"']");
			if(e==null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//查找一个子问题的结果是否已经被计算出来
	public boolean findResIndex(String index,String mid) {
		try {
			Document document = XmlUtils.getResBlocksDocument();
			Element e = (Element) document.selectSingleNode("//res[@index='"+index+"' and @mid='"+mid+"']");
			if(e==null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//增加一个新的子问题结果区块
	public void addNewResBolck(ProblemBlock block, int nowid){
		try {
			Document document = XmlUtils.getResBlocksDocument();
			Element root = document.getRootElement();

			int preHash = block.getHash() - 1;
			Element user_tag = root.addElement("res");
			user_tag.setAttributeValue("hash", block.getHash().toString());
			user_tag.setAttributeValue("prehash", Integer.toString(preHash));
			user_tag.setAttributeValue("index", block.getIndex());
			user_tag.setAttributeValue("ip", block.getIp());
            user_tag.setAttributeValue("qid", block.getQid());
            user_tag.setAttributeValue("mid", block.getMid());
            user_tag.setAttributeValue("host", block.getHost());
			user_tag.setAttributeValue("res", block.getRes());

			Element e = (Element) root.selectSingleNode("nowid");
			Attribute attr = e.attribute("id");
			attr.setValue(Integer.toString(nowid));

			XmlUtils.write2ResXml(document);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*public void editAttribute(Element root,String nodeName){

		//获取指定名字的节点，无此节点的会报NullPointerException,时间问题不做此情况的判断与处理了
		Element node=root.element("user");
		Attribute attr=node.attribute("editor");//获取此节点指定的属性,无此节点的会报NullPointerException
		node.remove(attr);//删除此属性

		Attribute attrDate=node.attribute("date");//获取此节点的指定属性
		attrDate.setValue("更改");//更改此属性值

		node.addAttribute("add","增加");//添加的属性
	}*/
}
