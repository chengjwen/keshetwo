import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class Delete extends Menu{
    private final JFrame jf=new JFrame("删除学生信息！");
    private final JButton jb=new JButton("删除");
    private final JTextField jt=new JTextField();
    private String s=null;
    private String sq=null;
    public Delete() throws Exception{
        super();
        JLabel jl=new JLabel("请输入需要删除学生的学号！");
        Font fo=new Font("Serief",Font.BOLD,12);
        jl.setFont(fo);
        jt.setBounds(15,15,150,30);
                       //可以通过setBounds对组件进行自定义大小和位置设置。第一个参数改组件在JFrame中的x坐边
                       //第二个参数改组件在JFrame中的y坐标
                      //第三个参数改组件在JFrame中的组件宽度
                      // 第四个参数改组件在JFrame中的组件高度
        jb.setBounds(175,15,60,30);
        jl.setBounds(10,80,80,30);
        jf.add(jt);
        jf.add(jb);
        jf.add(jl);
        jf.setSize(300,170);
          //setSize(int width, int height)：其实就是定义控件的大小，有两个参数，分别对应宽度和高度
        jf.setLocation(300,250);
          //setLocation(int x, int y)：将组件移到新位置，用x 和 y 参数来指定新位置的左上角
        jf.setVisible(true);
          //setVisible(boolean)方法是用来显示GUI组件的。
        jb.addActionListener((ActionEvent e)->{
            if(e.getSource()==jb){
                if("".equals(jt.getText().trim())){
                    JOptionPane.showMessageDialog(null,"输入的信息不可以为空，请重新输入！","错误提示！",
                            JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    jf.dispose();
                    try{
                        new Delete();
                    }catch(Exception ex){
                        System.out.println("异常");
                    }
                }else{
                    s=jt.getText().trim();
                    int t=Integer.parseInt(jt.getText().trim());
                    if(t<=0){
                        JOptionPane.showMessageDialog(null,"输入的学号不可以为0或负数，请重新输入！","错误提示！",
                                JOptionPane.INFORMATION_MESSAGE);
                        f.dispose();
                        jf.dispose();
                        try{
                            new Delete();
                        }catch(Exception ex){System.out.println("异常");}
                    }else{
                        sq="delete from stu where id="+t;
                        jf.dispose();
                        try{
                            show(SQL,1,sq);
                        }catch(Exception ex){System.out.println("异常");}
                    }
                }
            }
        });
    }
}