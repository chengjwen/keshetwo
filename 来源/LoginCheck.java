/**
*用户登录检查
*/
public class LoginCheck {
    private final String name ;
    private final String password ;
/**
*有两个参数的构造方法
*/
    public LoginCheck(String name, String password){
        this.name = name ;
        this.password = password ;
    }
/**
*判断用户输入的用户名密码是否正确
*/
    public boolean validate(){
        return("root".equals(name)&&"java".equals(password));
    }
}
