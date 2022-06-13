import javax.swing.*;
import java.awt.event.ActionnEvent;
/**
*创建Adda类并且继承Menu类*/
public class Adda extends Menu{				
    private final JFrame jf=new JFrame("添加学生信息！");		//定义一个窗体，窗体的名称为添加学生信息！
    //定义信息填写栏
    private final JTextField jt1=new JTextField();
    private final JTextField jt2=new JTextField();
    private final  JTextField jt3=new JTextField();
    private final JTextField jt4=new JTextField();
   //定义提交和退出按钮
    private final JButton bt1=new JButton("提交");
    private final JButton bt2=new JButton("退出");
    private String na=null;					//定义一个na的字符串，并且直接赋值为null
    private String sq=null;					//定义一个sq的字符串，并且直接赋值为null
    private float m,w,en,ever,su;				//定义名为m，w，en，ever，su；的单精度变量
   /**
定义一个无参构造方法，并且抛出Exception异常
*/
    public Adda()throws Exception{	
       /**
      调用父类的构造方法*/			
        super();						
        //定义面板控件的标签栏的标题
        JLabel jl0=new JLabel("请输入新添加学生的信息：");		//创建具有指定文本“请输入新添加学生的信息： ”JLabel 实例
        JLabel jl1=new JLabel("姓  名:");				//创建具有指定文本“姓  名 ”JLabel 实例
        JLabel jl2=new JLabel("数  学:");				//创建具有指定文本“数  学: ”JLabel 实例
        JLabel jl3=new JLabel("物  理:");				//创建具有指定文本“物  理: ”JLabel 实例
        JLabel jl4=new JLabel("英  语:");				//创建具有指定文本“英  语: ”JLabel 实例
        JLabel xin=new JLabel("");				//创建具有指定文本“   ”JLabel 实例
       //定义各个标签的坐标位置
      //参数一，参数二（x,y）是其左上角坐标，也就是和左沿的距离，参数三是标签控件的宽度width，参数4是标签控件的高度height是组件本身的宽和高。
        jl0.setBounds(10,10,170,30);
        jl1.setBounds(10,50,60,30);
        jt1.setBounds(80,50,180,30);
        jl2.setBounds(10,90,60,30);
        jt2.setBounds(80,90,180,30);
        jl3.setBounds(10,130,60,30);
        jt3.setBounds(80,130,180,30);
        jl4.setBounds(10,170,60,30);
        jt4.setBounds(80,170,180,30);
        bt1.setBounds(20,230,60,30);
        bt2.setBounds(90,230,60,30);
        xin.setBounds(10,260,100,30);
       //将各个标签控件添加到窗体
        jf.add(jl0);
        jf.add(jl1);
        jf.add(jt1);
        jf.add(jl2);
        jf.add(jt2);
        jf.add(jl3);
        jf.add(jt3);
        jf.add(jl4);
        jf.add(jt4);
        jf.add(bt1);
        jf.add(bt2);
        jf.add(xin);
       //设置窗体大小
        jf.setSize(400,380);
       //设置组件的显示位置
        jf.setLocation(300,200);
       //设置显示或隐藏组件，属性为true
        jf.setVisible(true);
       //监听提交按钮按下
        bt1.addActionListener((ActionEvent e)->{
          //如果是提交按钮
            if(e.getSource()==bt1){
	//判断录入的信息是否为空
                if("".equals(jt1.getText().trim())|| "".equals(jt2.getText().trim())|| "".equals(jt3.getText().trim())
                        || "".equals(jt4.getText().trim())){
	   //信息为空提示框提示
                    JOptionPane.showMessageDialog(null,"输入的信息不可以为空，请重新输入！","错误提示！",
                            JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
	   //关闭窗体
                    jf.dispose();
                    try{
	       //再次启动Adda();
                        new Adda();
	     //抓取异常
                    }catch(Exception ex){
                        System.out.println("异常");
                    }
	//判断录入的份数是否小于0
                }else if(Float.parseFloat(jt2.getText())<0||Float.parseFloat(jt3.getText())<0||Float.parseFloat(jt4.getText())<0){
	    //提示框提示输入的分数不可以有负数，请重新输入
                    JOptionPane.showMessageDialog(null,"输入的分数不可以有负数，请重新输入！","错误提示！",
                            JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
	    //关闭窗体	
                    jf.dispose();
                    try{
	        //再次启动Adda类
                        new Adda();
	       //抓取异常，并处理
                    }catch(Exception ex){
	        //控制台打印
	         System.out.println("异常");
	   }
                }else{
	   //获取输入框中的参数 赋值给变量
                    na=jt1.getText().trim();
                    m=Float.parseFloat(jt2.getText().trim());
                    w=Float.parseFloat(jt3.getText().trim());
                    en=Float.parseFloat(jt4.getText().trim());
                    su=m+w+en;
                    ever=toTwo((float)(su/3.0));
	   //定义sql   sql的含义为向stu表中插入一条记录
                    sq="insert into stu(name,math,wuli,english,everage,sum)"+"values('"+na+"',"+m+","+w+","+en+","+ever+","+su+")";
                    try{
	        //关闭窗口
                        jf.dispose();
                        show(SQL,1,sq);
	    //抓取异常,并处理
                    }catch(Exception ex){
	       //控制器中打印异常
	         System.out.println("异常");
	    }
                }
            }
        });
       //监听退出按钮按下
        bt2.addActionListener((ActionEvent e)-> {
           //e.getSource是获取发起事件的控件对象，判断按下的按钮是不是退出按钮，如果是的话执行下面代码块
            if (e.getSource() == bt2) {
	//关闭窗体
                jf.dispose();
            }
        });
    }
}