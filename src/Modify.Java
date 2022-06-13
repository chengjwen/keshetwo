/**
*学生信息的修改
*/
import javax.swing.*;
import java.awt.Menu;
import java.awt.event.ActionEvent;
/**
*Modify类为Menu类的子类，用于修改学生信息
*/
public class Modify extends Menu{
    private final JFrame jf=new JFrame("修改学生信息！");    //创建标题为“修改学生信息！”的窗口
    private final JTextField jt5=new JTextField();     //创建文本框，允许用户在文本框中输入单行文本
    private final JButton bt1=new JButton("提交");
    private final JButton bt2=new JButton("退出");     //创建两个按钮，允许用户单击按钮
    private int id;
    private String na=null;
    private String sq=null;
    private float m,w,en,ever,su;
/**
*构造方法
*/
    public Modify()throws Exception{
        super();
/**
*创建标签为用户提供信息提示，再创建文本框 用户在文本框中输入对应的信息
*/
         JLabel jl0=new JLabel("请输入信息：");
         JLabel jl1=new JLabel("对应学号:");
         JTextField jt1=new JTextField();
         JLabel jl2=new JLabel("姓  名:");
         JTextField jt2=new JTextField();
         JLabel jl3=new JLabel("数  学:");
         JTextField jt3=new JTextField();
         JLabel jl4=new JLabel("物  理:");
         JTextField jt4=new JTextField();
         JLabel jl5=new JLabel("英  语:");
         JLabel xin=new JLabel("");
/**
*对上面添加的标签和文本框分别设置大小和位置
*/
        jl0.setBounds(10,10,120,30);
        jl1.setBounds(10,50,60,30);
        jt1.setBounds(80,50,180,30);
        jl2.setBounds(10,90,60,30);
        jt2.setBounds(80,90,180,30);
        jl3.setBounds(10,130,60,30);
        jt3.setBounds(80,130,180,30);
        jl4.setBounds(10,170,60,30);
        jt4.setBounds(80,170,180,30);
        jl5.setBounds(10,210,60,30);
        jt5.setBounds(80,210,180,30);
        bt1.setBounds(20,250,60,30);
        bt2.setBounds(90,250,60,30);
        xin.setBounds(10,300,100,30);
/**
*将上面创建的组件添加到窗口中
*/
        jf.add(jl0);
        jf.add(jl1);
        jf.add(jt1);
        jf.add(jl2);
        jf.add(jt2);
        jf.add(jl3);
        jf.add(jt3);
        jf.add(jl4);
        jf.add(jt4);
        jf.add(jl5);
        jf.add(jt5);
        jf.add(bt1);
        jf.add(bt2);
        jf.add(xin);
/**
*设置窗体的大小，位置，显示窗体
*/
        jf.setSize(400,400);
        jf.setLocation(300,200);
        jf.setVisible(true);
/**
*给bt1按钮添加事件监听器
*/
        bt1.addActionListener((ActionEvent e)->{     
                if(e.getSource()==bt1){    //如果事件源为bt1对象时 
                    if("".equals(jt1.getText().trim())|| "".equals(jt2.getText().trim())|| "".equals(jt3.getText().trim())
                            || "".equals(jt4.getText().trim())|| "".equals(jt5.getText().trim())){        //获取输入框的内容并且去空
                        JOptionPane.showMessageDialog(null,"输入的信息不可以为空，请重新输入！","错误提示！",
                                JOptionPane.INFORMATION_MESSAGE);         //为空时提示用户重新输入,展示为提示框
                        f.dispose();   //撤销当前窗口，并释放资源
                        jf.dispose();
                        try{
                            new Modify();
                        }catch(Exception ex){
                            System.out.println("异常");
                        }     //捕获异常提示异常
                    }
/**
*如果文本框中输入的信息有负数
*/
                  else if(Integer.parseInt(jt1.getText())<0||Float.parseFloat(jt3.getText())<0||Float.parseFloat(jt4.getText())<0||Float.parseFloat(jt5.getText())<0){
                        JOptionPane.showMessageDialog(null,"输入的信息不可以有负数，请重新输入！","错误提示！",
                                JOptionPane.INFORMATION_MESSAGE);     //提示框提示信息
                        f.dispose();
                        jf.dispose();
                        try{
                            new Modify();   // 捕获异常,防止发生异常,创建新的修改对象
                        }catch(Exception ex){System.out.println("异常");}
                    }            
                    else{
                        id=Integer.parseInt(jt1.getText().trim());    // 获取数据,并且去空
                        na=jt2.getText().trim();
                        m=Float.parseFloat(jt3.getText().trim());
                        w=Float.parseFloat(jt4.getText().trim());
                        en=Float.parseFloat(jt5.getText().trim());
                        su=m+w+en;
                        ever=toTwo((float)(su/3.0));
                        sq="update stu set name='"+na+"',math="+m+",wuli="+w+",english="+en+",everage="+ever+",sum="+su+" where id="+id;
                        try{
                            jf.dispose();
                            show(SQL,1,sq);   // 执行sql
                        }catch(Exception ex){System.out.println("异常");}       // 捕获异常
                    }
                }
            }) ;
/**
*添加bt2监听器,如果事件源为bt2时，关闭jf窗口并释放资源
*/
        bt2.addActionListener((ActionEvent e)-> {
            if (e.getSource() == bt2) {
                jf.dispose();
            }
        });
    }
}