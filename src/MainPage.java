import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainPage extends JFrame {
    private JLabel labelNav;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel textNav;
    private JPanel NavbarPanel;
    private JPanel mainPanel1;
    private JButton closeWindow;
    private JButton minimizeWindow;
    private JPanel zk;
    private JPanel main;
    private JPanel tab1;
    private JPanel tab2;
    private JPanel tab3;
    private JPanel tab4;
    private JPanel TabPanel;
    private JTable tableList;

    public MainPage() {
        tab1.setVisible(true);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(false);

        closeWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeWindow();
            }
        });
        minimizeWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minimizeWindow();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab1.setVisible(true);
                tab2.setVisible(false);
                tab3.setVisible(false);
                tab4.setVisible(false);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab1.setVisible(false);
                tab2.setVisible(true);
                tab3.setVisible(false);
                tab4.setVisible(false);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab1.setVisible(false);
                tab2.setVisible(false);
                tab3.setVisible(true);
                tab4.setVisible(false);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab1.setVisible(false);
                tab2.setVisible(false);
                tab3.setVisible(false);
                tab4.setVisible(true);
            }
        });
    }
    void closeWindow(){
        WindowEvent closeProject = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeProject);
    }
    void minimizeWindow(){
        setState(Frame.ICONIFIED);
    }
    public static void main(String[] args) {
        MainPage page = new MainPage();
        page.setContentPane(page.mainPanel1);
        page.setTitle("Projekt");
        page.setExtendedState(JFrame.MAXIMIZED_BOTH);
        page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        page.setUndecorated(true);
        page.setVisible(true);
    }
}
