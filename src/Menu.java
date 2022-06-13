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
 * ʵ��ϵͳGUi���棬�˵���Ͳ˵�����(�����ļ���������ʾ����ʵ�ֶ����������ӿڣ��̳�AbstractTableModel��;
 *
 */
public class Menu extends AbstractTableModel implements ActionListener{
    public static String []title={"ѧ��","����","��ѧ","����","Ӣ��","ƽ����","�ܷ�"};
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

    JFrame f=new JFrame("ѧ���ɼ�����ϵͳ");
    JButton display=new JButton("��ʾ");
    JButton search=new JButton("����");
    JButton modify=new JButton("�޸�");
    JButton adda=new JButton("���");
    JButton delete=new JButton("ɾ��");
    JButton sort=new JButton("����");
    JButton save=new JButton("�����ļ�");
    JButton quit=new JButton("�˳�");
    JTable tab;
    DefaultTableModel tabmo;
    Connection con;
    Statement sta;
    ResultSet rs=null;

    /**
     * Menu��Ĺ��췽��������������������student���ݿ⣬��������ϵͳ�����˵������е���������Ϊ��Ӧ�������Ӽ�������ʹ����������ͬ���ж�
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
     * �е���Ŀ��Ҳ���ǵ�0��/����ĳ���
     * @return
     */
    public int getColumnCount(){
        return title.length;
    }

    /**
     * �ŵ���Ŀ��Ҳ����ѧ������+1�ĳ���
     * @return
     */
    public int getRowCount(){
        return inf.length;
    }

    /**
     *��ȡ���Ԫ�Ķ�Ӧֵ
     * @param row        the row whose value is to be queried
     * @param col     the column whose value is to be queried
     * @return
     */
    public Object getValueAt(int row,int col){
        return inf[row][col];
    }

    /**
     *��ȡ����0�У�Ŀ¼�У�ÿ���е�Ԫ��Ӧ������
     * @param col  the column being queried
     * @return
     */
    public String getColumnName(int col){
        return title[col];
    }

    /**
     *�÷�����ƻ�ȡĿ¼��Ԫ��Ӧ��class���ͣ�
     * @param col  the column being queried
     * @return
     */
    public Class<?>getColumnClass(int col){
        return this.getValueAt(0,col).getClass();
    }

    /**
     *��д����AbstractTableModel�ķ��������Ʊ����к��в��ɱ��༭
     * @param row  the row being queried
     * @param col the column being queried
     * @return
     */
    public boolean isCellEditable(int row,int col){
        return false;
    }

    /**
     *����ÿһ�����Ԫ��Ӧ������/ֵ��
     * @param newv   value to assign to cell
     * @param row   row of cell
     * @param col  column of cell
     */
    public void setValueAt(Object newv,int row,int col){
        this.inf[row][col]=newv;
    }

    /**
     *��д�ӿڷ�������Բ�ͬ�¼�Դ���ȳ�����ǰ������ݣ������ݶ�Ӧ�������ͬʵ������/ϵͳ�Ĳ�ͬ��Ӧ��
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==display){
            try{
                this.show(SQL,0,"");
            }catch(Exception ex){
                System.out.println("�쳣");
            }
        }
        if(e.getSource()==search){
            try{
                f.dispose();
                new Search();
                if("".equals(tabmo.getValueAt(0,0))){
                    JOptionPane.showMessageDialog(null,"���ݿ��в����������ѧ������Ϣ�����������룡","��ʾ��",JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    new Search();
                }
            }catch(Exception ex){
                System.out.println("�쳣");
            }
        }
        if(e.getSource()==modify){
            try{
                f.dispose();
                new Modify();
            }catch(Exception ex){
                System.out.println("�쳣");
            }
        }
        if(e.getSource()==adda){
            try{
                f.dispose();
                new Adda();
            }catch(Exception ex){
                System.out.println("�쳣");
            }
        }
        if(e.getSource()==delete){
            try{
                f.dispose();
                new Delete();
            }catch(Exception ex){
                System.out.println("�쳣");
            }
        }
        if(e.getSource()==sort){
            try{
                JOptionPane.showMessageDialog(null,"�����ǽ���ȡ�����ݿ�����ݽ������򣬲��޸����ݿ�����ݣ�","��ʾ��",JOptionPane.INFORMATION_MESSAGE);
                this.sort();
            }catch(Exception ex){
                System.out.println("�쳣");
            }
        }
        if(e.getSource()==save){
            try{
                this.save();
                JOptionPane.showMessageDialog(null,"�����ļ��ɹ���������D�̸�Ŀ¼�鿴�ļ���","��ʾ��",JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception ex){System.out.println("�쳣");}
        }
        if(e.getSource()==quit){
            try{
                sta.close();
                con.close();
                System.exit(1);
            }catch(Exception ex){System.out.println("�쳣");}
        }
    }

    /**
     *��ʾ¼�������ѧ���ĳɼ�
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
         * ��ѯ�����
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
         * ʹ�ò�ѯ���Ľ���������ս����ԭʼ˳��/����ѧ����˳�򣬰����б��浽��Ӧ�ı��Ԫ
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
     *������ѧ���ĳɼ������ֽܷ�������
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
         * ʹ�ò�ѯ���������ѧ���������ѧ����Ӧ��Ӧ��Ϣ��������ӦԪ�ص�������
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
         * ��ѧ�������ֵܷĸߵͽ���ѧ��������Ϣ������
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
         * ����֮�����°�����˳�����ν�����õ�ѧ����Ϣ���루���ã����Ԫ��
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
     *��д���ѧ����Ϣ����Ϊ��Ϊ��student.txt"���ı��ļ���
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
         * �õ���excuteQuery����,���ö���ʱstatement������SQL��䣬���ؽ�����������ڲ�ѯ��ȡ�������
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
            out.write(id1[j]+"\t\t");/*�õ���\t�Ʊ��ַ�*/
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
     * ������Ĳ���ָ������ֵ������λС��
     * @param f
     */
    public static float toTwo(float f){
        BigDecimal b=new BigDecimal(f);
        return b.setScale(2,BigDecimal.ROUND_HALF_UP).floatValue();

    }
}
