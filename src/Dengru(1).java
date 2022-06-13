/**
创建Dengru类
*/
public class Dengru {
	/**
               定义窗体，并且窗体的标题设置为“学生管理系统”*/
    private final JFrame frame = new JFrame("学生成绩管理系统");
	//定义登录和重置按钮
    private final JButton submit = new JButton("登入");
    private final JButton reset = new JButton("重置");
	//定义标题
    private final JLabel infoLab = new JLabel("用户登录(root,java)");
	//定义填写栏
    private final JTextField nameText = new JTextField(10);
	//定义密码的填写栏
    private final JPasswordField passText = new JPasswordField() ;
	/**
创建Dengru类*/
    public Dengru() {
		//创建用户名和密码的面板标签
        JLabel nameLab = new JLabel("用户名:");
        JLabel passLab = new JLabel("密	 码:");
		//定义字体并且应用
        Font fnt = new Font("Serief",Font.ITALIC + Font.BOLD,12) ;
        infoLab.setFont(fnt) ;
		
		//监听下提交按钮
        submit.addActionListener((ActionEvent e)->{
				//判断按钮是否是提交按钮
                if(e.getSource()==submit){
					//获取输入的用户名和密码
                    String tName = nameText.getText() ;
                    String tPassWord = new String(passText.getPassword()) ;
					//对用户名和密码进行查询操作
                    LoginCheck log = new LoginCheck(tName,tPassWord) ;
					//对查询结果进行判断
					//结果为true
                    if(log.validate()){
                        try{
							//提示框提示，密码正确
                            JOptionPane.showMessageDialog(null,"密码正确，即将进入系统！","登入提示！",
                                    JOptionPane.INFORMATION_MESSAGE);
							//线程等待三秒
                            Thread.sleep(3000);
							//窗口关闭
                            frame.dispose();
							//执行Menu类
                            new Menu();
							//抓取异常
                        }catch(Exception ex){
						//处理异常
                            ex.printStackTrace();
                        }
                    }else{	//结果为false
                        try{
						 //提示框提示，密码正确
                            JOptionPane.showMessageDialog(null,"用户不存在或密码错误，请重新填写！","登入提示！",
                                    JOptionPane.INFORMATION_MESSAGE);
							//窗口关闭
                            frame.dispose();
							//重新执行Dengru类
                            new Dengru();
							//抓取异常
                        }catch(Exception ex){
							//控制台打印
                            System.out.println("异常");
                        }
                    }
                }
            });
		//监听重置按钮
        reset.addActionListener((ActionEvent e)->{
				//判断是否是重置按钮
                if(e.getSource()==reset){
					//将用户名和密码设置为空
                    nameText.setText("") ;
                    passText.setText("") ;
					//设置infoLab标签名为"用户登录(root,java)"
                    infoLab.setText("用户登录(root,java)");
                }
            });
		//给fram，add窗体的监听事件创建WindowAdapter的对象 中间是对一个函数也就是windows窗口关闭函数进行重写
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(1) ;
            }
        }) ;
		//设置窗体不按照默认布局
        frame.setLayout(null) ;
		//对各个标签的布局进行设置
        nameLab.setBounds(5,5,60,20) ;
        passLab.setBounds(5,30,60,20) ;
        infoLab.setBounds(5,65,220,30) ;
        nameText.setBounds(65,5,100,20) ;
        passText.setBounds(65,30,100,20) ;
        submit.setBounds(170,5,60,20) ;
        reset.setBounds(170,30,60,20) ;
		//将各个标签组件添加到窗体
        frame.add(nameLab) ;
        frame.add(passLab) ;
        frame.add(infoLab) ;
        frame.add(nameText) ;
        frame.add(passText) ;
        frame.add(submit) ;
        frame.add(reset) ;
		//设置窗体大小
        frame.setSize(280,130) ;
		//设置窗体北京颜色
        frame.setBackground(Color.WHITE) ;
		//设置窗体位置
        frame.setLocation(420,230) ;
		//设置窗体的显示或隐藏组件，属性为true
        frame.setVisible(true) ;

    }
}