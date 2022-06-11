/**
 * @author Angel
 */
public class Adda extends Menu{
    private final JFrame jf=new JFrame("添加学生信息！");
    private final JTextField jt1=new JTextField();
    private final JTextField jt2=new JTextField();
    private final  JTextField jt3=new JTextField();
    private final JTextField jt4=new JTextField();
    private final JButton bt1=new JButton("提交");
    private final JButton bt2=new JButton("退出");
    private String na=null;
    private String sq=null;
    private float m,w,en,ever,su;
    public Adda()throws Exception{
        super();
        JLabel jl0=new JLabel("请输入新添加学生的信息：");
        JLabel jl1=new JLabel("姓  名:");
        JLabel jl2=new JLabel("数  学:");
        JLabel jl3=new JLabel("物  理:");
        JLabel jl4=new JLabel("英  语:");
        JLabel xin=new JLabel("");
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
        jf.setSize(400,380);
        jf.setLocation(300,200);
        jf.setVisible(true);
        bt1.addActionListener((ActionEvent e)->{
            if(e.getSource()==bt1){
                if("".equals(jt1.getText().trim())|| "".equals(jt2.getText().trim())|| "".equals(jt3.getText().trim())
                        || "".equals(jt4.getText().trim())){
                    JOptionPane.showMessageDialog(null,"输入的信息不可以为空，请重新输入！","错误提示！",
                            JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    jf.dispose();
                    try{
                        new Adda();
                    }catch(Exception ex){
                        System.out.println("异常");
                    }
                }else if(Float.parseFloat(jt2.getText())<0||Float.parseFloat(jt3.getText())<0||Float.parseFloat(jt4.getText())<0){
                    JOptionPane.showMessageDialog(null,"输入的分数不可以有负数，请重新输入！","错误提示！",
                            JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    jf.dispose();
                    try{
                        new Adda();
                    }catch(Exception ex){System.out.println("异常");}
                }else{
                    na=jt1.getText().trim();
                    m=Float.parseFloat(jt2.getText().trim());
                    w=Float.parseFloat(jt3.getText().trim());
                    en=Float.parseFloat(jt4.getText().trim());
                    su=m+w+en;
                    ever=toTwo((float)(su/3.0));
                    sq="insert into stu(name,math,wuli,english,everage,sum)"+"values('"+na+"',"+m+","+w+","+en+","+ever+","+su+")";
                    try{
                        jf.dispose();
                        show(SQL,1,sq);
                    }catch(Exception ex){System.out.println("异常");}
                }
            }
        });
        bt2.addActionListener((ActionEvent e)-> {
            if (e.getSource() == bt2) {
                jf.dispose();
            }
        });
    }
}
