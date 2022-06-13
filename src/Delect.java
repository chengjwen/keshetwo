import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
*创建Delete类并且继承Menu父类
*/
class Delete extends Menu{
	//创建窗体，并且设置窗体标题为删除学生信息
    private final JFrame jf=new JFrame("删除学生信息！");
	//创建删除按钮
    private final JButton jb=new JButton("删除");
	//定义填写信息组件
    private final JTextField jt=new JTextField();
	//创建字符串s并且复制为null
    private String s=null;
	//创建字符串sq并且复制为null
    private String sq=null;
	/**
创建无参构造函数抛出异常*/
    public Delete() throws Exception{
/**
*调用父类构造
*/
        super();
		//创建标签
        JLabel jl=new JLabel("请输入需要删除学生的学号！");
		//设置字体
        Font fo=new Font("Serief",Font.BOLD,12);
        jl.setFont(fo);
		//设置组件位置
        jt.setBounds(15,15,150,30);
                       /**
                      *可以通过setBounds对组件进行自定义大小和位置设置。第一个参数改组件在JFrame中的x坐边
                      *第二个参数改组件在JFrame中的y坐标
                      *第三个参数改组件在JFrame中的组件宽度
                      *第四个参数改组件在JFrame中的组件高度*/
        jb.setBounds(175,15,60,30);
        jl.setBounds(10,80,80,30);
		//将组件位置信息添加到窗体
        jf.add(jt);
        jf.add(jb);
        jf.add(jl);
		//设置窗体大小
        jf.setSize(300,170);
		//设置窗体位置
        jf.setLocation(300,250);
		//设置窗体的显示或隐藏组件，属性为true
        jf.setVisible(true);
          /**
          *setSize(int width, int height)：其实就是定义控件的大小，有两个参数，分别对应宽度和高度*/
          *setLocation(int x, int y)：将组件移到新位置，用x 和 y 参数来指定新位置的左上角
       setVisible(boolean)方法是用来显示GUI组件的。*/
	   
	   //监听删除按钮
        jb.addActionListener((ActionEvent e)->{
		//判断按钮是否为删除按钮
            if(e.getSource()==jb){
				//判断输入框是否为空
                if("".equals(jt.getText().trim())){
					//提示框提示输
                    JOptionPane.showMessageDialog(null,"输入的信息不可以为空，请重新输入！","错误提示！",
                            JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
					//关闭对话框
                    jf.dispose();
                  /**
                    *使用dispose()方法关闭窗体*/
                    try{
					//再次执行删除类
                        new Delete();
						//抓取异常
                    }catch(Exception ex){
					//控制器打印
                        System.out.println("异常");
                    }
                 /**
                 try catch*/
                }else{
					//获取录入的信息，并且去除空格
                    s=jt.getText().trim();
                    int t=Integer.parseInt(jt.getText().trim());
					//判断输入的学号是否为负数
                    if(t<=0){
					//提示框提示
                        JOptionPane.showMessageDialog(null,"输入的学号不可以为0或负数，请重新输入！","错误提示！",
                                JOptionPane.INFORMATION_MESSAGE);
                        f.dispose();
						//关闭窗体
                        jf.dispose();
                        try{
							//再次启动delete类
                            new Delete();
							//抓取异常
                        }catch(Exception ex){
						//控制台打印
						System.out.println("异常");
						}
						//学号不为负数执行代码块
                    }else{
						//定义sql
                        sq="delete from stu where id="+t;
						//关闭窗体
                        jf.dispose();
                        try{
						//进行查询操作
                            show(SQL,1,sq);
							//对异常进行抓取
                        }catch(Exception ex){System.out.println("异常");}
                    }
                }
            }
        });
    }
}