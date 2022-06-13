import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 * @author Angel
 */

/**
 * 实现系统GUi界面，菜单类和菜单功能(保存文件，排序，显示），实现动作监听器接口，继承AbstractTableModel类;
 *
 */
public class Menu extends AbstractTableModel implements ActionListener{
    public static String []title={"学号","姓名","数学","物理","英语","平均分","总分"};
    public  static Object [][]inf={{"","","","","","",""},
            {"","","","","","",""},{"","","","","","",""},
            {"","","","","","",""},{"","","","","","",""},
            {"","","","","","",""},{"","","","","","",""},
            {"","","","","","",""},{"","","","","","",""},
            {"","","","","","",""},{"","","","","","",""},
            {"","","","","","",""},{"","","","","","",""},
            {"","","","","","",""},{"","","","","","",""},
            {"","","","","","",""},{"","","","","","",""},
            {"","","","","","",""},{"","","","","","",""},
            {"","","","","","",""},{"","","","","","",""},
            {"","","","","","",""},{"","","","","","",""}};
    public static final String DATA_DRIVER ="com.mysql.cj.jdbc.Driver";
    public static final String DATA_URL ="jdbc:mysql://localhost:3306/student?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false&&allowPublicKeyRetrieval=true";
    public static final String USERNAME ="root";
    public static final String PASSWORD ="123";    public static final String SQL="select id,name,math,wuli,english,everage,sum from stu";

    JFrame f=new JFrame("学生成绩管理系统");
    JButton display=new JButton("显示");
    JButton search=new JButton("查找");
    JButton modify=new JButton("修改");
    JButton adda=new JButton("添加");
    JButton delete=new JButton("删除");
    JButton sort=new JButton("排序");
    JButton save=new JButton("保存文件");
    JButton quit=new JButton("退出");
    JTable tab;
    DefaultTableModel tabmo;
    Connection con;
    Statement sta;
    ResultSet rs=null;

    /**
     * Menu类的构造方法，加载驱动程序，启动student数据库，建表；构造系统基本菜单和其中的相关组件，为相应的组件添加监听器，使窗口做出不同的行动
     */

    public Menu() throws Exception{
        Class.forName(DATA_DRIVER);
        con=DriverManager.getConnection(DATA_URL, USERNAME, PASSWORD);
        sta=con.createStatement();
        tabmo=new DefaultTableModel(inf,title);
        tab=new JTable(tabmo);
        JScrollPane js=new JScrollPane(tab);
        JPanel jp=new JPanel();
        jp.add(display);
        jp.add(search);
        jp.add(modify);
        jp.add(adda);
        jp.add(delete);
        jp.add(sort);
        jp.add(save);
        jp.add(quit);
        f.add(jp,BorderLayout.NORTH);
        f.add(js,BorderLayout.CENTER);
        f.setSize(700,500);
        f.setLocation(250,70);
        f.setVisible(true);
        display.addActionListener(this);
        search.addActionListener(this);
        modify.addActionListener(this);
        adda.addActionListener(this);
        delete.addActionListener(this);
        sort.addActionListener(this);
        save.addActionListener(this);
        quit.addActionListener(this);
    }

    /**
     * 列的数目，也就是第0行/标题的长度
     * @return
     */
    public int getColumnCount(){
        return title.length;
    }

    /**
     * 排的数目，也就是学生数量+1的长度
     * @return
     */
    public int getRowCount(){
        return inf.length;
    }

    /**
     *获取表格单元的对应值
     * @param row        the row whose value is to be queried
     * @param col     the column whose value is to be queried
     * @return
     */
    public Object getValueAt(int row,int col){
        return inf[row][col];
    }

    /**
     *获取表格第0行（目录行）每个列单元对应的名字
     * @param col  the column being queried
     * @return
     */
    public String getColumnName(int col){
        return title[col];
    }

    /**
     *用反射机制获取目录单元对应的class类型，
     * @param col  the column being queried
     * @return
     */
    public Class<?>getColumnClass(int col){
        return this.getValueAt(0,col).getClass();
    }

    /**
     *重写父类AbstractTableModel的方法，控制表格的行和列不可被编辑
     * @param row  the row being queried
     * @param col the column being queried
     * @return
     */
    public boolean isCellEditable(int row,int col){
        return false;
    }

    /**
     *设置每一个表格单元对应的内容/值；
     * @param newv   value to assign to cell
     * @param row   row of cell
     * @param col  column of cell
     */
    public void setValueAt(Object newv,int row,int col){
        this.inf[row][col]=newv;
    }

    /**
     *重写接口方法，针对不同事件源，先撤销当前面板内容，并根据对应类给出不同实现内容/系统的不同反应；
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==display){
            try{
                this.show(SQL,0,"");
            }catch(Exception ex){
                System.out.println("异常");
            }
        }
        if(e.getSource()==search){
            try{
                f.dispose();
                new Search();
                if("".equals(tabmo.getValueAt(0,0))){
                    JOptionPane.showMessageDialog(null,"数据库中不存在你查找学生的信息，请重新输入！","提示！",JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    new Search();
                }
            }catch(Exception ex){
                System.out.println("异常");
            }
        }
        if(e.getSource()==modify){
            try{
                f.dispose();
                new Modify();
            }catch(Exception ex){
                System.out.println("异常");
            }
        }
        if(e.getSource()==adda){
            try{
                f.dispose();
                new Adda();
            }catch(Exception ex){
                System.out.println("异常");
            }
        }
        if(e.getSource()==delete){
            try{
                f.dispose();
                new Delete();
            }catch(Exception ex){
                System.out.println("异常");
            }
        }
        if(e.getSource()==sort){
            try{
                JOptionPane.showMessageDialog(null,"排序是将读取到数据库的内容进行排序，不修改数据库的内容！","提示！",JOptionPane.INFORMATION_MESSAGE);
                this.sort();
            }catch(Exception ex){
                System.out.println("异常");
            }
        }
        if(e.getSource()==save){
            try{
                this.save();
                JOptionPane.showMessageDialog(null,"保存文件成功，可以在D盘根目录查看文件！","提示！",JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception ex){System.out.println("异常");}
        }
        if(e.getSource()==quit){
            try{
                sta.close();
                con.close();
                System.exit(1);
            }catch(Exception ex){System.out.println("异常");}
        }
    }

    /**
     *显示录入的所有学生的成绩
     * @param s
     * @param p
     * @param t
     * @throws Exception
     */
    public void show(String s,int p,String t)throws Exception{
        if (p != 0) {
            sta.executeUpdate(t);
        }
        /**
         * 查询结果集
         */
        rs=sta.executeQuery(s);
        int id0;
        String name0;
        float math0;
        float wuli0;
        float english0;
        float everage0;
        float sum0;
        int i=0;
        /**
         * 使用查询到的结果集，按照结果集原始顺序/输入学生的顺序，按行列保存到相应的表格单元
         * */
        while(rs.next()){
            id0=rs.getInt("id");
            name0=rs.getString("name");
            math0=rs.getFloat("math");
            wuli0=rs.getFloat("wuli");
            english0=rs.getFloat("english");
            everage0=toTwo(rs.getFloat("everage"));
            sum0=rs.getFloat("sum");
            tabmo.setValueAt(id0,i,0);
            tabmo.setValueAt(name0,i,1);
            tabmo.setValueAt(math0,i,2);
            tabmo.setValueAt(wuli0,i,3);
            tabmo.setValueAt(english0,i,4);
            tabmo.setValueAt(everage0,i,5);
            tabmo.setValueAt(sum0,i,6);
            i+=1;
            if (i>=tabmo.getRowCount()) {
                tabmo.addRow(new Object[]{});
            }
        }
    }

    /**
     *对所有学生的成绩根据总分进行排序
     * @throws Exception
     */
    public void sort()throws Exception{
        int []id=new int[1000];
        String []name=new String[1000];
        float []math=new float[1000];
        float []wuli=new float[1000];
        float []english=new float[1000];
        float []everage=new float[1000];
        float []sum=new float[1000];
        int idtemp;
        String nametemp;
        float mathtemp;
        float wulitemp;
        float englishtemp;
        float everagetemp;
        float sumtemp;
        int num=0;
        rs=sta.executeQuery(SQL);
        /**
         * 使用查询到结果集将学生结果集中学生相应相应信息保存至相应元素的数组中
         */
        while(rs.next()){
            id[num]=rs.getInt("id");
            name[num]=rs.getString("name");
            math[num]=rs.getFloat("math");
            wuli[num]=rs.getFloat("wuli");
            english[num]=rs.getFloat("english");
            everage[num]=toTwo(rs.getFloat("everage"));
            sum[num]=rs.getFloat("sum");
            num+=1;
        }
        /**
         * 给学生按照总分的高低进行学生所有信息的排序
         */
        for(int j=1;j<num;j++)
            for(int k=0;k<num-j;k++)
                if(sum[k]<sum[k+1])
                {
                    idtemp=id[k];
                    id[k]=id[k+1];
                    id[k+1]=idtemp;

                    nametemp=name[k];
                    name[k]=name[k+1];
                    name[k+1]=nametemp;

                    mathtemp=math[k];
                    math[k]=math[k+1];
                    math[k+1]=mathtemp;

                    wulitemp=wuli[k];
                    wuli[k]=wuli[k+1];
                    wuli[k+1]=wulitemp;

                    englishtemp=english[k];
                    english[k]=english[k+1];
                    english[k+1]=englishtemp;

                    everagetemp=everage[k];
                    everage[k]=everage[k+1];
                    everage[k+1]=everagetemp;

                    sumtemp=sum[k];
                    sum[k]=sum[k+1];
                    sum[k+1]=sumtemp;

                }
        /**
         * 排序之后重新按数组顺序依次将排序好的学生信息填入（重置）表格单元；
         * */
        for(int n=0;n<num;n++){
            tabmo.setValueAt(id[n],n,0);
            tabmo.setValueAt(name[n],n,1);
            tabmo.setValueAt(math[n],n,2);
            tabmo.setValueAt(wuli[n],n,3);
            tabmo.setValueAt(english[n],n,4);
            tabmo.setValueAt(everage[n],n,5);
            tabmo.setValueAt(sum[n],n,6);
            if(n>=tabmo.getRowCount()) {
                tabmo.addRow(new Object[]{});
            }
        }
    }

    /**
     *将写入的学生信息保留为名为“student.txt"的文本文件；
     * @throws Exception
     */
    public void save()throws Exception{
        String []id1=new String[1000];
        String []name1=new String[1000];
        String []math1=new String[1000];
        String []wuli1=new String[1000];
        String []english1=new String[1000];
        String []everage1=new String[1000];
        String []sum1=new String[1000];
        int i=0;
        Writer out= new FileWriter("d:"+File.separator+"Student.txt");
        /**
         * 用到了excuteQuery方法,作用对象时statement，传入SQL语句，返回结果集对象，用于查询获取结果集；
         */
        rs=sta.executeQuery(SQL);
        while(rs.next()){
            id1[i]=rs.getString("id");
            name1[i]=rs.getString("name");
            math1[i]=rs.getString("math");
            wuli1[i]=rs.getString("wuli");
            english1[i]=rs.getString("english");
            everage1[i]=String.valueOf(toTwo(rs.getFloat("everage")));
            sum1[i]=rs.getString("sum");
            i+=1;
        }
        for(int j=0;j<i;j++){
            out.write(id1[j]+"\t\t");/*用到了\t制表字符*/
            out.write(name1[j]+"\t\t");
            out.write(math1[j]+"\t\t");
            out.write(wuli1[j]+"\t\t");
            out.write(english1[j]+"\t\t");
            out.write(everage1[j]+"\t\t");
            out.write(sum1[j]);
            out.write("\r");
        }
        out.close();
    }

    /**
     * 将传入的参数指定的数值保留两位小数
     * @param f
     */
    public static float toTwo(float f){
        BigDecimal b=new BigDecimal(f);
        return b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();

    }
}
