package pacs.servlet;

import com.sun.scenario.effect.impl.prism.PrImage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.util.HtmlUtils;
import pacs.bean.*;
import pacs.dao.*;
import pacs.util.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: PACS
 * @Date: 2019/11/26 18:18
 * @Author: Mr.Liu
 * @Description:
 */
@javax.servlet.annotation.WebServlet("/foreServlet")
public class ForeServlet extends BaseForeServlet {

    public String home(HttpServletRequest request, HttpServletResponse response, Page page) {
        List<Category> cs= new CategoryDAO().list();
        new DoctorDAO().fill(cs);
        for(Category category:cs) {
            List<Doctor> doctors = category.getDoctors();
            for(Doctor d : doctors){
                System.out.println(d.getName());
            }
        }
        System.out.println("回到主页面时，把医生信息加载进入参数域cs");
        //new DoctorDAO().fillByRow(cs);
        request.setAttribute("cs", cs);
        return "home.jsp";
    }

    //前端注册功能验证
    public String register(HttpServletRequest request, HttpServletResponse response, Page page) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        name = HtmlUtils.htmlEscape(name);//对编码进行HTML转义，比如说<div>，进行htmlEscape转码就变成了&lt;div&gt;
                                            //用户名文本会出现在网页欢迎栏里面，所以需要进行转义，不转的话，用户可以捣乱
        System.out.println(name);
        boolean exist = userDAO.isExist(name);

        if(exist){
            request.setAttribute("msg", "用户名已经被使用,不能使用");
            return "register.jsp";
        }

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        System.out.println(user.getName());
        System.out.println(user.getPassword());
        userDAO.add(user);

        return "@registerSuccess.jsp";
    }
    public String login(HttpServletRequest request, HttpServletResponse response, Page page) throws IOException {
        String name = request.getParameter("name");
        name = HtmlUtils.htmlEscape(name);
        String password = request.getParameter("password");
        System.out.println("获取了用户名"+name);
        System.out.println("获取了密码"+password);

        if("666666".equals(name) && "666666".equals(password)){
            return "listCategory.jsp";
        }
        if("888888".equals(name) && "888888".equals(password)){
            return "listorder.jsp";
        }
        if(name.startsWith("doctor")&&name.equals(password)){
            String userdid = StringUtils.substringAfterLast(name,"doctor" );//取出/fore之后的字符串，也就是方法
            System.out.println("登陆医生id为"+userdid);
//            request.getSession().setAttribute("user",user);
            System.out.println("将医生id上传到session域");
            request.setAttribute("userdid",userdid);
            return "listdoctororder.jsp";
        }

        User user = userDAO.get(name,password);

        if(null==user){
            request.setAttribute("msg", "账号密码错误");
            String wrong = "账号或密码错误";
            request.setAttribute("wrongmessage",wrong);
            return "login.jsp";
        }
        System.out.println("登陆成功");
        request.getSession().setAttribute("user", user);
        return "@forehome";
    }

    /*public String product(HttpServletRequest request, HttpServletResponse response, Page page) {
        int pid = Integer.parseInt(request.getParameter("pid"));
        Doctor p = doctorDAO.get(pid);//提取医生信息

        List<DoctorImage> productSingleImages = doctorImageDAO.list(p, DoctorImageDAO.type_single);
        List<DoctorImage> productDetailImages = doctorImageDAO.list(p, DoctorImageDAO.type_detail);
        p.setProductSingleImages(productSingleImages);
        p.setProductDetailImages(productDetailImages);

        List<PropertyValue> pvs = propertyValueDAO.list(p.getId());

        List<Review> reviews = reviewDAO.list(p.getId());

        productDAO.setSaleAndReviewNumber(p);

        request.setAttribute("reviews", reviews);

        request.setAttribute("p", p);
        request.setAttribute("pvs", pvs);
        return "product.jsp";
    }*/
    public String logout(HttpServletRequest request, HttpServletResponse response, Page page) {
        request.getSession().removeAttribute("user");
        return "@forehome";
    }

    public String category(HttpServletRequest request,HttpServletResponse response,Page page) {
        int cid = Integer.parseInt(request.getParameter("cid"));

        Category c = new CategoryDAO().get(cid);
        new DoctorDAO().fill(c);//获取对应科室的所有医生信息
        System.out.println("分类页面加载完成");
        request.setAttribute("category",c);
        return "category.jsp";

    }

    public String doctor(HttpServletRequest request,HttpServletResponse response,Page page) {
        System.out.println("Foreservlet进行doctor方法。。。");
        int pid = Integer.parseInt(request.getParameter("pid"));
        System.out.println("从参数域取出医生id为"+pid);
        Doctor p = doctorDAO.get(pid);
        System.out.println("获取了医生"+p.getName()+"的信息");
        System.out.println("医生的简介为"+p.getSubTile());
        List<DoctorImage> doctorImages = doctorImageDAO.list(p,"jpg");
        System.out.println("取出这个医生的单个图片集合");
        System.out.println("集合长度为"+doctorImages.size());
        if(null !=doctorImages){
            System.out.println("医生图片信息不为0");
        }
        p.setDoctorImages(doctorImages);
        System.out.println("将图片集合添加到对应的医生信息里");
        int number=0;
        for (DoctorImage dd:doctorImages){
            number=dd.getId();
            System.out.println("将医生的图片id"+number+"传入参数域");
            request.setAttribute("doctorimageid",number);
        }

        List<Review> reviews = reviewDAO.list(p.getId());  //取出医生评论
        System.out.println("取出医生评论"+reviews);
        int count = reviewDAO.getCount(p.getId());
        System.out.println("改医生一共有"+count+"条评论");
        for (Review review:reviews){
            System.out.println(review.getContent()+review.getCreateDate());
        }
        p.setReviewCount(count);
        request.setAttribute("reviews",reviews);
        System.out.println("医生评论加载完成");
        request.setAttribute("doctor",p);
        System.out.println("医生"+p.getName()+"信息加载完成");
        return "doctor.jsp";

    }

    public String buyone(HttpServletRequest request,HttpServletResponse response, Page page) {
        System.out.println("调用buyone方法。。。");
        int pid = Integer.parseInt(request.getParameter("pid"));//获取医生id

        Doctor doctor = doctorDAO.get(pid);
        System.out.println("获取了此次挂号的"+doctor.getName()+"的医生信息");

        int oiid=0;
        User user =(User) request.getSession().getAttribute("user");
        System.out.println("获取了此次挂号用户的信息"+user.getName());
        List<OrderItem> ois = orderItemDAO.listByUser(user.getId());

        OrderItem oi = new OrderItem();
        oi.setUser(user);
        oi.setDoctor(doctor);
        oi.setStatus(1);
        orderItemDAO.add(oi);//把支付项添加到数据库
        System.out.println("buyone方法成功将订单项添加到数据库");
        oiid=oi.getId();//订单项的id
        System.out.println("开始跳转到支付界面");
        return "@forebuy?oiid="+oiid;
    }

    public String buy(HttpServletRequest request,HttpServletResponse response,Page page) {
        System.out.println("已经进入到结算环节...");
        float totalmoney = 0;
        int oidd = Integer.parseInt(request.getParameter("oiid"));//取出订单项id
        System.out.println("从参数中成功取出订单项id为："+oidd);

        OrderItem oi = orderItemDAO.get(oidd);
        System.out.println("根据订单项id成功取出订单项信息");

        totalmoney +=oi.getDoctor().getPromotePrice()*1;//*1是因为数量为1
        System.out.println("成功获取到订单项中医生的挂号费用为:"+totalmoney);

        request.getSession().setAttribute("dingdanxiang",oi);
        System.out.println("订单项成功传输到结算页面");

        request.setAttribute("money",totalmoney);
        System.out.println("付款价钱成功传输到结算页面"+totalmoney);
        return "buy.jsp";
    }

    //生成订单，在数据库的order_表中插入数据
    public String createorder(HttpServletRequest request,HttpServletResponse response,Page page) {
        String money = request.getParameter("money");
        System.out.println("成功获取到付款金额"+money);
        System.out.println("成功进入到后台创建挂号单...");
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("从session中获取到了用户"+user.getName()+"的信息");

        String receiver = request.getParameter("receiver");
        System.out.println("从参数域中获取病姓名"+receiver);
        String mobile = request.getParameter("mobile");
        System.out.println("从参数域中获取联系电话"+"moblie");
        String userMessage = request.getParameter("userMessage");
        System.out.println("从参数域中获取病人被注信息"+userMessage);

        Order  order= new Order();
        System.out.println("创建一个空的订单");
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);//生成订单编码
        System.out.println("createrorder方法成功生成挂号单编号:"+orderCode);

        order.setOrderCode(orderCode);
        order.setMobile(mobile);
        order.setReceiver(receiver);
        order.setUserMessage(userMessage);
        order.setCreateDate(new Date());
        order.setPayDate(new Date());
        order.setUser(user);
        order.setStatus("nopayed");

        orderDAO.add(order);
        System.out.println("将订单信息成功写入到数据库中");
        System.out.println("挂号单数据成功添加导数据库");

        //将订单项的oid数据修改为订单id
        float total = 0;
        OrderItem oi = new OrderItem();
        oi = (OrderItem) request.getSession().getAttribute("dingdanxiang");
        System.out.println("从参数域获取订单项信息："+oi.getId());
        oi.setOrder(order);//在Crud环节中，会从order里面获取id来修改orderitem里的oid字符段
        System.out.println("将订单项与订单绑定，即订单项中的oid会变成订单的id:"+order.getId());
        orderItemDAO.update(oi);
        System.out.println("从订单项里获取医生信息来得到挂号费");
        Doctor d = new Doctor();
        d = oi.getDoctor();
        int totalmoney = (int) d.getPromotePrice();
        System.out.println("成功修改数据库中点的订单项oid="+order.getId());

        System.out.println("进行客户端跳转至支付界面");
        return "@forealipay?oid="+order.getId()+"&total="+totalmoney;//第一个参数使用？后面的使用&
    }


    public String alipay(HttpServletRequest request,HttpServletResponse response,Page page) {
        System.out.println("成功调用alipay方法。。。");
        System.out.println("准备跳转到alipay.jsp");
        return "alipay.jsp";
    }

    public  String payed(HttpServletRequest request,HttpServletResponse response,Page page) {
        int oid = Integer.parseInt(request.getParameter("oid"));

        System.out.println("成功从参数域获取order的id："+oid);
        Order order = orderDAO.get(oid);
        order.setStatus("payed");
        order.setPayDate(new Date());
        new OrderDAO().update(order);
        System.out.println("支付成功");
        request.setAttribute("order",order);
        System.out.println("将订单信息传到参数域");

        return "payed.jsp";

    }

    public String bought(HttpServletRequest request,HttpServletResponse response,Page page) {
        User user = (User) request.getSession().getAttribute("user");
        System.out.println("从session中取出user信息,id:"+user.getId());
        List<Order> os = orderDAO.list(user.getId(),"payed");
        System.out.println("提取出当前用户所有的已支付的订单");
        orderItemDAO.fill(os);
        System.out.println("将所有订单项填入订单");
        request.setAttribute("os",os);

        return "bought.jsp";
    }

}
