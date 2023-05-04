import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class MainPage extends JFrame {
    private JLabel labelNav;
    private JButton btnMain;
    private JButton btnSeznam;
    private JButton btnAddPeople;
    private JButton btnNote;
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
    private JButton btnDeleteForm;
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textPhone;
    private JButton btnSave;
    private JTextPane textAddress;
    private JComboBox chosseTrida;
    private JTable tableZaci;
    private JTextField findDataTable;
    private JButton btnFindData;
    private JButton addData;
    private JButton btnDeleteData;
    private JButton btnRepairData;
    private JTextArea textArea1;
    private JButton button2;
    private JPanel tab5;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;
    private JTabbedPane tabbedPane4;
    private JTabbedPane tabbedPane5;
    private JTabbedPane tabbedPane6;

    public MainPage() {
        tab1.setVisible(true);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(false);
        connectData();
        comboBox();
        viewDataTable();
        btnRepairData.setVisible(false);
        btnDeleteData.setVisible(false);
        tableZaci.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
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
        btnMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab1.setVisible(true);
                tab2.setVisible(false);
                tab3.setVisible(false);
                tab4.setVisible(false);
            }
        });
        btnSeznam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab1.setVisible(false);
                tab2.setVisible(true);
                tab3.setVisible(false);
                tab4.setVisible(false);
                findDataTable.setText("");
            }
        });
        btnAddPeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab1.setVisible(false);
                tab2.setVisible(false);
                tab3.setVisible(true);
                tab4.setVisible(false);
                btnDeleteData.setVisible(false);
                btnRepairData.setVisible(false);
                btnSave.setVisible(true);
                btnDeleteForm.setVisible(true);
                textName.setText("");
                chosseTrida.setSelectedItem("...");
                textEmail.setText("");
                textPhone.setText("");
                textAddress.setText("");
            }
        });
        btnNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab1.setVisible(false);
                tab2.setVisible(false);
                tab3.setVisible(false);
                tab4.setVisible(true);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savaData();
            }
        });
        btnDeleteForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFormData();
            }
        });

        btnFindData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repairData();
            }
        });
        btnRepairData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repairDataForm();
            }
        });
        btnDeleteData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteData();
            }
        });
        addData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab1.setVisible(false);
                tab2.setVisible(false);
                tab3.setVisible(true);
                tab4.setVisible(false);
                btnDeleteData.setVisible(false);
                btnRepairData.setVisible(false);
                btnSave.setVisible(true);
                btnDeleteForm.setVisible(true);
                textName.setText("");
                chosseTrida.setSelectedItem("...");
                textEmail.setText("");
                textPhone.setText("");
                textAddress.setText("");
            }
        });
    }
    public void closeWindow(){
        WindowEvent closeProject = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeProject);
    }
    public void minimizeWindow(){
        setState(Frame.ICONIFIED);
    }
    public void comboBox(){
        chosseTrida.addItem("...");
        chosseTrida.addItem("1.A");
        chosseTrida.addItem("1.B");
        chosseTrida.addItem("1.C");
        chosseTrida.addItem("1.D");
        chosseTrida.addItem("2.A");
        chosseTrida.addItem("2.B");
        chosseTrida.addItem("2.C");
        chosseTrida.addItem("2.D");
        chosseTrida.addItem("3.A");
        chosseTrida.addItem("3.B");
        chosseTrida.addItem("3.C");
        chosseTrida.addItem("3.D");
        chosseTrida.addItem("4.A");
        chosseTrida.addItem("4.B");
        chosseTrida.addItem("4.C");
        chosseTrida.addItem("4.D");
    }
    Connection con;
    PreparedStatement pst;
    public void connectData(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/projektjava","root","");
            System.out.println("připojeno k databází funguje!");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void savaData(){
        String name, trida, email, phone, address;
        name = textName.getText();
        trida = chosseTrida.getSelectedItem().toString();
        email = textEmail.getText();
        phone = textPhone.getText();
        address = textAddress.getText();
                try{
                    pst = con.prepareStatement("insert into zaci(name, class, email, phone, address)values(?,?,?,?,?)");
                    pst.setString(1, name);
                    pst.setString(2, trida);
                    pst.setString(3, email);
                    pst.setString(4, phone);
                    pst.setString(5, address);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Žák byl úspěšně přidán.");

                    textName.setText("");
                    chosseTrida.setSelectedItem("...");
                    textEmail.setText("");
                    textPhone.setText("");
                    textAddress.setText("");
                    viewDataTable();
                    tab3.setVisible(false);
                    tab2.setVisible(true);


                }catch (SQLException e1){
                    e1.printStackTrace();
                }
    }
    // udělat rozdělanou zkoušku..
    public void deleteFormData(){
        /// Zkouška pro zajistění, aby ve formuláři bylo něco napsané.
        if (textName.getSelectedText() == ""){
            JOptionPane.showMessageDialog(null, "je plné");
        }else {
            JOptionPane.showMessageDialog(null, "je prázdné");
        }
        textName.setText("");
        chosseTrida.setSelectedItem("...");
        textEmail.setText("");
        textPhone.setText("");
        textAddress.setText("");
    }
    public void viewDataTable(){
        //String column_names[]= {"Serial Number","Medicine Name","Dose","Frequency"};
        String col[] = {"ID", "Jméno a příjmení", "Třída", "E-mail", "Telefonní číslo", "Adresa"};
        try{
            pst = con.prepareStatement("select * from zaci");
            ResultSet rs = pst.executeQuery();
            tableZaci.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException eTable){
            eTable.printStackTrace();
        }
    }
    public void repairData(){
        String index = findDataTable.getText();
        //JOptionPane.showMessageDialog(null, "Funkce tabulky." + index);
        try{

            //JOptionPane.showMessageDialog(null, index);
            pst = con.prepareStatement("select name,class,email,phone,address from zaci where id = ?");
            pst.setString(1, index);
            ResultSet rs = pst.executeQuery();
            if (rs.next() == true){
                btnDeleteData.setVisible(true);
                btnRepairData.setVisible(true);
                btnSave.setVisible(false);
                btnDeleteForm.setVisible(false);
                String name = rs.getString(1);
                String trida = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                String address = rs.getString(5);

                textName.setText(name);
                chosseTrida.setSelectedItem(trida);
                textEmail.setText(email);
                textPhone.setText(phone);
                textAddress.setText(address);

                tab2.setVisible(false);
                tab3.setVisible(true);

            }else{
                pst = con.prepareStatement("select name,class,email,phone,address from zaci where name = ?");
                pst.setString(1, index);
                ResultSet rsa = pst.executeQuery();
                if (rsa.next() == true){
                    btnDeleteData.setVisible(true);
                    btnRepairData.setVisible(true);
                    btnSave.setVisible(false);
                    btnDeleteForm.setVisible(false);
                    String name = rsa.getString(1);
                    String trida = rsa.getString(2);
                    String email = rsa.getString(3);
                    String phone = rsa.getString(4);
                    String address = rsa.getString(5);

                    textName.setText(name);
                    chosseTrida.setSelectedItem(trida);
                    textEmail.setText(email);
                    textPhone.setText(phone);
                    textAddress.setText(address);

                    tab2.setVisible(false);
                    tab3.setVisible(true);

                }else{
                    JOptionPane.showMessageDialog(null, "Daný žák nebyl nalezen!");
                }
            }
        }catch (SQLException e){

        }
    }
    public void repairDataForm(){
        String id, name, trida, email, phone, address;
        name = textName.getText();
        trida = chosseTrida.getSelectedItem().toString();
        email = textEmail.getText();
        phone = textPhone.getText();
        address = textAddress.getText();
        id = findDataTable.getText();
        try{
           pst = con.prepareStatement("update zaci set name = ?,class = ?, email = ?, phone = ?, address = ? where id = ? ");
           pst.setString(1, name);
            pst.setString(2, trida);
            pst.setString(3, email);
            pst.setString(4, phone);
            pst.setString(5, address);
            pst.setString(6, id);
            pst.executeUpdate();
            viewDataTable();
            textName.setText("");
            chosseTrida.setSelectedItem("...");
            textEmail.setText("");
            textPhone.setText("");
            textAddress.setText("");
            findDataTable.setText("");
            tab3.setVisible(false);
            tab2.setVisible(true);
        }catch (SQLException e1){

        }
    }
    public void deleteData(){
        String id;
        id = findDataTable.getText();
        try{
            pst = con.prepareStatement("delete from zaci where id = ?");
            pst.setString(1, id);
            pst.executeUpdate();
            viewDataTable();
            textName.setText("");
            chosseTrida.setSelectedItem("...");
            textEmail.setText("");
            textPhone.setText("");
            textAddress.setText("");
            findDataTable.setText("");
            tab3.setVisible(false);
            tab2.setVisible(true);

        }catch (SQLException e){

        }
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
