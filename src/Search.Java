/**
*学生信息的查找
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
*Search类为Menu类的子类，用于查找学生信息
*/
public class Search extends Menu{
    private final JFrame jf=new JFrame("查找学生信息！");   //创建标题为“查找学生信息！”的窗口，创建按钮和文本框
    private  final JButton jb=new JButton("查询");
    private  final JTextField jt=new JTextField();
    private  String s=null;
    private  String sq=null;
/**
*构造方法
*/
    public Search() throws Exception{
        super();
/**
*创建标签，设计字体显示效果，设置组件当前字体、大小和位置，组件添加到窗体中，设置窗体的大小，位置，显示窗体
*/
        JLabel jl=new JLabel("请输入需要查询学生的学号或姓名！");
        Font fo=new Font("Serief",Font.BOLD,12);
        jl.setFont(fo);
        jt.setBounds(15,15,150,30);
        jb.setBounds(175,15,60,30);
        jl.setBounds(10,80,180,30);
        jf.add(jt);
        jf.add(jb);
        jf.add(jl);
        jf.setSize(300,170);
        jf.setLocation(300,250);
        jf.setVisible(true);
        jb.addActionListener((ActionEvent e)->{  // 添加监听器
                if(e.getSource()==jb){
                    if("".equals(jt.getText().trim())){
                        JOptionPane.showMessageDialog(null,"输入的信息不可以为空，请重新输入！","错误提示！",
                                JOptionPane.INFORMATION_MESSAGE);      //文本框中输入信息为空时弹出一个消息对话框
                        f.dispose();
                        jf.dispose();
                        try{
                            new Search();
                        }catch(Exception ex){
                            System.out.println("异常");    
                        }     //捕获异常
                    }else{
                        s=jt.getText().trim();  //获取填入的数据
                        try{
                            int t=Integer.parseInt(jt.getText().trim());    // 转为integer类型
                            if(t<=0){
                                JOptionPane.showMessageDialog(null,"输入的学号不可以为0或负数，请重新输入！","错误提示！",
                                        JOptionPane.INFORMATION_MESSAGE);        // 判断是否小于0  提示框提示用户
                                f.dispose();
                                jf.dispose();
                                try{
                                    new Search();
                                }catch(Exception ex){System.out.println("异常");}
                            }else{
                                sq="select id,name,math,wuli,english,everage,sum from stu where id like '%"+t+"%'";
                                jf.dispose();
                                try{
                                    show(sq,0,"　");
                                }catch(Exception ex){System.out.println("异常");}
                            }
                        }catch(Exception ex){
                            sq="select id,name,math,wuli,english,everage,sum from stu where name like '%"+s+"%'";
                            jf.dispose();
                            try{
                                show(sq,0,"　");
                            }catch(Exception ex1){System.out.println("异常");}
                        }
                    }
                }
            });
    }
}