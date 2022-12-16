package architect.tranning.ch03;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        WinForm winForm = new WinForm("WINDOW窗口");
        winForm.addChild(new Picture("LOGO图片"));
        winForm.addChild(new Button("登录"));
        winForm.addChild(new Button("注册"));

        Frame frame = new Frame("FRAME1");
        winForm.addChild(frame);

        frame.addChild(new Label("用户名"));
        frame.addChild(new TextBox("文本框"));
        frame.addChild(new Label("密码"));
        frame.addChild(new PasswordBox("密码框"));
        frame.addChild(new CheckBox("复选框"));
        frame.addChild(new TextBox("记住用户名"));
        frame.addChild(new LinkLabel("忘记密码"));

        winForm.print();
    }
}
