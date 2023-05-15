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
    private JButton btnHodnoceni;
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
    private JButton btnSaveNote;
    private JPanel tab5;
    private JTabbedPane tabbedPaneClass;
    private JTable tableClass1;
    private JTable tableClass4;
    private JTable tableClass2;
    private JTable tableClass3;
    private JTextField textId;
    private JLabel labelID;
    private JTextArea txtAreaNote;
    private JPanel panelClass1;
    private JPanel panelClass2;
    private JPanel panelClass3;
    private JPanel panelClass4;
    private JButton btnSaveHodnoceni;
    private JCheckBox checkBoxRepairNote;
    private JTextArea textAreaNote;
    private JTable tableZaciMain;
    private JTabbedPane tabbedPane1;
    private JTable tableClass1Main;
    private JTable tableClass2Main;
    private JTable tableClass3Main;
    private JTable tableClass4Main;
    private JPanel tab6;
    private JButton btnNapoveda;

    public MainPage() {
        connectData();
        viewDataTable();
        viewDataTableHodnoceni();
        viewNote();
        tab1.setVisible(true);
        tab2.setVisible(false);
        tab3.setVisible(false);
        tab4.setVisible(false);
        tab5.setVisible(false);
        tab6.setVisible(false);
        comboBox();

        btnRepairData.setVisible(false);
        btnDeleteData.setVisible(false);
        labelID.setVisible(false);
        textId.setVisible(false);
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
                viewNote();
                viewDataTable();
                viewDataTableHodnoceni();
                tab1.setVisible(true);
                tab2.setVisible(false);
                tab3.setVisible(false);
                tab4.setVisible(false);
                tab5.setVisible(false);
                tab6.setVisible(false);
            }
        });
        btnSeznam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab1.setVisible(false);
                tab2.setVisible(true);
                tab3.setVisible(false);
                tab4.setVisible(false);
                tab5.setVisible(false);
                tab6.setVisible(false);
                findDataTable.setText("");
            }
        });
        btnHodnoceni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab1.setVisible(false);
                tab2.setVisible(false);
                tab3.setVisible(false);
                tab4.setVisible(false);
                tab5.setVisible(true);
                tab6.setVisible(false);
            }
        });
        btnNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewNote();
                tab1.setVisible(false);
                tab2.setVisible(false);
                tab3.setVisible(false);
                tab4.setVisible(true);
                tab5.setVisible(false);
                tab6.setVisible(false);
                txtAreaNote.setEnabled(false);
                checkBoxRepairNote.setSelected(false);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savaData();
                viewDataTable();
                JOptionPane.showMessageDialog(null, "Žák byl úspěšně přidán...");
                tab3.setVisible(false);
                tab2.setVisible(true);
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
                chosseData();
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
                tab5.setVisible(false);
                tab6.setVisible(false);
                btnDeleteData.setVisible(false);
                btnRepairData.setVisible(false);
                btnSave.setVisible(true);
                btnDeleteForm.setVisible(true);
                textId.setVisible(false);
                labelID.setVisible(false);
                textName.setText("");
                chosseTrida.setSelectedItem("...");
                textEmail.setText("");
                textPhone.setText("");
                textAddress.setText("");
            }
        });
        btnSaveHodnoceni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDataHodnoceni();
                viewDataTableHodnoceni();
            }
        });
        btnSaveNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBoxRepairNote.setSelected(false);
                checkBtn();
                repairNote();
            }
        });
        checkBoxRepairNote.addActionListener(e-> checkBtn());
        /*btnNapoveda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tab1.setVisible(false);
                tab2.setVisible(false);
                tab3.setVisible(false);
                tab4.setVisible(false);
                tab5.setVisible(false);
                tab6.setVisible(true);
            }
        });*/
    }
    public void checkBtn(){
        if (checkBoxRepairNote.isSelected()){
            btnSaveNote.setVisible(true);
            txtAreaNote.setEnabled(true);
        }else {
            btnSaveNote.setVisible(false);
            txtAreaNote.setEnabled(false);
        }
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
        chosseTrida.addItem("2.A");
        chosseTrida.addItem("3.A");
        chosseTrida.addItem("4.A");
    }
    Connection con;
    PreparedStatement pstZaci;
    PreparedStatement pstHodnoceni;
    PreparedStatement pstPoznamky;
    public void connectData(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/projektjava","root","");
            System.out.println("připojeno k databází funguje!");
        }catch (ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
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
                    pstZaci = con.prepareStatement("insert into zaci(name, class, email, phone, address)values(?,?,?,?,?)");
                    pstZaci.setString(1, name);
                    pstZaci.setString(2, trida);
                    pstZaci.setString(3, email);
                    pstZaci.setString(4, phone);
                    pstZaci.setString(5, address);
                    pstZaci.executeUpdate();
                    pstHodnoceni = con.prepareStatement("insert into hodnoceni_zaci(name, class, zkouseni1, zkouseni2, test1, test2, test3, test4, test5, aktivita1, aktivita2, aktivita3, aktivita4, aktivita5, bonus, prumer)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    pstHodnoceni.setString(1, name);
                    pstHodnoceni.setString(2, trida);
                    pstHodnoceni.setString(3,"" );
                    pstHodnoceni.setString(4, "");
                    pstHodnoceni.setString(5, "");
                    pstHodnoceni.setString(6, "");
                    pstHodnoceni.setString(7, "");
                    pstHodnoceni.setString(8, "");
                    pstHodnoceni.setString(9, "");
                    pstHodnoceni.setString(10, "");
                    pstHodnoceni.setString(11, "");
                    pstHodnoceni.setString(12, "");
                    pstHodnoceni.setString(13, "");
                    pstHodnoceni.setString(14, "");
                    pstHodnoceni.setString(15, "");
                    pstHodnoceni.setString(16, "");
                    pstHodnoceni.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Žák byl úspěšně přidán.");
                    textName.setText("");
                    chosseTrida.setSelectedItem("...");
                    textEmail.setText("");
                    textPhone.setText("");
                    textAddress.setText("");
                    viewDataTable();
                    viewDataTableHodnoceni();
                    tab3.setVisible(false);
                    tab2.setVisible(true);


                }catch (SQLException e1){
                    JOptionPane.showMessageDialog(null, "Problém s databází!");
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
    public void viewNote(){
        try{
            pstPoznamky = con.prepareStatement("select text from poznamky where id = 1");
            ResultSet rs = pstPoznamky.executeQuery();
            if (rs.next() == true) {
                String text = rs.getString(1);
                txtAreaNote.setText(text);
            }
        }catch (SQLException eTable){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
        try{
            pstPoznamky = con.prepareStatement("select text from poznamky where id = 1");
            ResultSet rsMain = pstPoznamky.executeQuery();
            textAreaNote.setEnabled(false);
            if (rsMain.next() == true) {
                String text = rsMain.getString(1);
                textAreaNote.setText(text);
            }
        }catch (SQLException eTable){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
    }
    public void viewDataTable(){
        //String column_names[]= {"Serial Number","Medicine Name","Dose","Frequency"};
        String col[] = {"ID", "Jméno a příjmení", "Třída", "E-mail", "Telefonní číslo", "Adresa"};
        try{
            pstZaci = con.prepareStatement("select * from zaci");
            ResultSet rs = pstZaci.executeQuery();
            tableZaci.setModel(DbUtils.resultSetToTableModel(rs));

        }catch (SQLException eTable){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
        tableZaciMain.setEnabled(false);
        try{
            pstZaci = con.prepareStatement("select * from zaci");
            ResultSet rsMain = pstZaci.executeQuery();
            tableZaciMain.setModel(DbUtils.resultSetToTableModel(rsMain));

        }catch (SQLException eTable){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }

    }
    public void viewDataTableHodnoceni(){
        try{
            pstHodnoceni = con.prepareStatement("select * from hodnoceni_zaci where class = '1.A'");
            ResultSet rsOne = pstHodnoceni.executeQuery();
            tableClass1.setModel(DbUtils.resultSetToTableModel(rsOne));

        }catch (SQLException eTable){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
        try{
            pstHodnoceni = con.prepareStatement("select * from hodnoceni_zaci where class = '1.A'");
            ResultSet rsOneMain = pstHodnoceni.executeQuery();
            tableClass1Main.setModel(DbUtils.resultSetToTableModel(rsOneMain));
            tableClass1Main.setEnabled(false);
        }catch (SQLException eTable){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
        try{
            pstHodnoceni = con.prepareStatement("select * from hodnoceni_zaci where class = '2.A'");
            ResultSet rsTwo = pstHodnoceni.executeQuery();
            tableClass2.setModel(DbUtils.resultSetToTableModel(rsTwo));
        }catch (SQLException eTable){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
        try{
            pstHodnoceni = con.prepareStatement("select * from hodnoceni_zaci where class = '2.A'");
            ResultSet rsTwoMain = pstHodnoceni.executeQuery();
            tableClass2Main.setModel(DbUtils.resultSetToTableModel(rsTwoMain));
            tableClass2Main.setEnabled(false);
        }catch (SQLException eTable){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }

        try{
            pstHodnoceni = con.prepareStatement("select * from hodnoceni_zaci where class = '3.A'");
            ResultSet rsThree = pstHodnoceni.executeQuery();
            tableClass3.setModel(DbUtils.resultSetToTableModel(rsThree));
        }catch (SQLException eTable){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
        try{
            pstHodnoceni = con.prepareStatement("select * from hodnoceni_zaci where class = '3.A'");
            ResultSet rsThreeMain = pstHodnoceni.executeQuery();
            tableClass3Main.setModel(DbUtils.resultSetToTableModel(rsThreeMain));
            tableClass3Main.setEnabled(false);
        }catch (SQLException eTable){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
        try{
            pstHodnoceni = con.prepareStatement("select * from hodnoceni_zaci where class = '4.A'");
            ResultSet rsFour = pstHodnoceni.executeQuery();
            tableClass4.setModel(DbUtils.resultSetToTableModel(rsFour));
        }catch (SQLException eTable){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
        try{
            pstHodnoceni = con.prepareStatement("select * from hodnoceni_zaci where class = '4.A'");
            ResultSet rsFourMain = pstHodnoceni.executeQuery();
            tableClass4Main.setModel(DbUtils.resultSetToTableModel(rsFourMain));
            tableClass4Main.setEnabled(false);
        }catch (SQLException eTable){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }

    }
    String idData;
    public void chosseData(){
        String index = findDataTable.getText();
        //JOptionPane.showMessageDialog(null, "Funkce tabulky." + index);
        try{

            //JOptionPane.showMessageDialog(null, index);
            pstZaci = con.prepareStatement("select name,class,email,phone,address from zaci where id = ?");
            pstZaci.setString(1, index);
            ResultSet rs = pstZaci.executeQuery();
            if (rs.next() == true){
                btnDeleteData.setVisible(true);
                btnRepairData.setVisible(true);
                labelID.setVisible(true);
                textId.setVisible(true);
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
                textId.setText(index);
                idData = index;

                tab2.setVisible(false);
                tab3.setVisible(true);

            }else{
                pstZaci = con.prepareStatement("select id,name,class,email,phone,address from zaci where name = ?");
                pstZaci.setString(1, index);
                ResultSet rsa = pstZaci.executeQuery();
                if (rsa.next() == true){
                    btnDeleteData.setVisible(true);
                    btnRepairData.setVisible(true);
                    labelID.setVisible(true);
                    textId.setVisible(true);
                    btnSave.setVisible(false);
                    btnDeleteForm.setVisible(false);
                    String id = rsa.getString(1);
                    String name = rsa.getString(2);
                    String trida = rsa.getString(3);
                    String email = rsa.getString(4);
                    String phone = rsa.getString(5);
                    String address = rsa.getString(6);

                    textName.setText(name);
                    chosseTrida.setSelectedItem(trida);
                    textEmail.setText(email);
                    textPhone.setText(phone);
                    textAddress.setText(address);
                    textId.setText(id);
                    idData = textId.getText();

                    tab2.setVisible(false);
                    tab3.setVisible(true);

                }else{
                    JOptionPane.showMessageDialog(null, "Daný žák nebyl nalezen!");
                }
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
    }
    public void repairDataForm(){
        String id, name, trida, email, phone, address;
        name = textName.getText();
        trida = chosseTrida.getSelectedItem().toString();
        email = textEmail.getText();
        phone = textPhone.getText();
        address = textAddress.getText();
        id = idData;
        try{
           pstZaci = con.prepareStatement("update zaci set name = ?,class = ?, email = ?, phone = ?, address = ? where id = ? ");
           pstZaci.setString(1, name);
            pstZaci.setString(2, trida);
            pstZaci.setString(3, email);
            pstZaci.setString(4, phone);
            pstZaci.setString(5, address);
            pstZaci.setString(6, id);
            pstZaci.executeUpdate();
            pstHodnoceni = con.prepareStatement("update hodnoceni_zaci set name = ?, class = ? where id = ? ");
            pstHodnoceni.setString(1, name);
            pstHodnoceni.setString(2, trida);
            pstHodnoceni.setString(3, id);
            pstHodnoceni.executeUpdate();
            viewDataTable();
            viewDataTableHodnoceni();
            textName.setText("");
            chosseTrida.setSelectedItem("...");
            textEmail.setText("");
            textPhone.setText("");
            textAddress.setText("");
            textId.setText("");
            findDataTable.setText("");
            idData = "0";
            tab3.setVisible(false);
            tab2.setVisible(true);
        }catch (SQLException e1){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
    }
    public void repairNote(){
        String text = txtAreaNote.getText();
        try {
            pstPoznamky = con.prepareStatement("update poznamky set text = ? where id = 1");
            pstPoznamky.setString(1, text);
            pstPoznamky.executeUpdate();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
    }
    public void deleteData(){
        String id;
        id = idData;
        try{
            pstZaci = con.prepareStatement("delete from zaci where id = ?");
            pstZaci.setString(1, id);
            pstZaci.executeUpdate();
            pstHodnoceni = con.prepareStatement("delete from hodnoceni_zaci where id = ?");
            pstHodnoceni.setString(1, id);
            pstHodnoceni.executeUpdate();
            viewDataTable();
            viewDataTableHodnoceni();
            textName.setText("");
            chosseTrida.setSelectedItem("...");
            textEmail.setText("");
            textPhone.setText("");
            textAddress.setText("");
            findDataTable.setText("");
            textId.setText("");
            tab3.setVisible(false);
            tab2.setVisible(true);

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Problém s databází!");
        }
    }
    public void saveDataHodnoceni(){
        if (panelClass1.isVisible() == true){
            try{for (int i=0;i<tableClass1.getRowCount(); i++) {
                String id = tableClass1.getValueAt(i, 0).toString().trim();
                String zk1 = tableClass1.getValueAt(i, 3).toString();
                String zk2 = tableClass1.getValueAt(i, 4).toString().trim();
                String test1 = tableClass1.getValueAt(i, 5).toString().trim();
                String test2 = tableClass1.getValueAt(i, 6).toString().trim();
                String test3 = tableClass1.getValueAt(i, 7).toString().trim();
                String test4 = tableClass1.getValueAt(i, 8).toString().trim();
                String test5 = tableClass1.getValueAt(i, 9).toString().trim();
                String akt1 = tableClass1.getValueAt(i, 10).toString().trim();
                String akt2 = tableClass1.getValueAt(i, 11).toString().trim();
                String akt3 = tableClass1.getValueAt(i, 12).toString().trim();
                String akt4 = tableClass1.getValueAt(i, 13).toString().trim();
                String akt5 = tableClass1.getValueAt(i, 14).toString().trim();
                String bonus = tableClass1.getValueAt(i, 15).toString().trim();
                String pozn = tableClass1.getValueAt(i, 16).toString().trim();
                //počet výsledné známky::

                double znamka = 0 ;
                int vaha = 0 ;
                //testy
                if (test1.equals("1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test1.equals("1-")){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test1.equals("2")){
                    znamka += 10;
                    vaha += 5;
                }
                if (test1.equals("2-")){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test1.equals("3")){
                    znamka += 15;
                    vaha += 5;
                }
                if (test1.equals("3-")){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test1.equals("4")){
                    znamka += 20;
                    vaha += 5;
                }
                if (test1.equals("4-")){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test1.equals( "5" )){
                    znamka += 25;
                    vaha += 5;
                }
                if (test2.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test2.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test2.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test2.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test2.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test2.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test2.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test2.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test2.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                if (test3.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test3.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test3.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test3.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test3.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test3.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test3.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test3.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test3.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                if (test4.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test4.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test4.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test4.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test4.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test4.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test4.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test4.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test4.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                if (test5.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test5.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test5.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test5.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test5.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test5.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test5.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test5.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test5.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
    //zkouseni

                if (zk1.equals( "1")){
                    znamka += 4;
                    vaha += 4;
                }
                if (zk1.equals( "1-") ){
                    znamka += 6;
                    vaha += 4;
                }
                if (zk1.equals( "2" )){
                    znamka += 8;
                    vaha +=4;
                }
                if (zk1.equals( "2-") ){
                    znamka += 10;
                    vaha += 4;
                }
                if (zk1.equals( "3" )){
                    znamka += 12;
                    vaha += 4;
                }
                if (zk1.equals( "3-") ){
                    znamka += 14;
                    vaha += 4;
                }
                if (zk1.equals( "4" )){
                    znamka += 16;
                    vaha += 4;
                }
                if (zk1.equals( "4-") ){
                    znamka += 18;
                    vaha += 4;
                }
                if (zk1.equals( "5" )){
                    znamka += 20;
                    vaha += 4;
                }
                if (zk2.equals( "1")){
                    znamka += 4;
                    vaha += 4;
                }
                if (zk2.equals( "1-") ){
                    znamka += 6;
                    vaha += 4;
                }
                if (zk2.equals( "2" )){
                    znamka += 8;
                    vaha +=4;
                }
                if (zk2.equals( "2-") ){
                    znamka += 10;
                    vaha += 4;
                }
                if (zk2.equals( "3" )){
                    znamka += 12;
                    vaha += 4;
                }
                if (zk2.equals( "3-") ){
                    znamka += 14;
                    vaha += 4;
                }
                if (zk2.equals( "4" )){
                    znamka += 16;
                    vaha += 4;
                }
                if (zk2.equals( "4-") ){
                    znamka += 18;
                    vaha += 4;
                }
                if (zk2.equals( "5" )){
                    znamka += 20;
                    vaha += 4;
                }

    ///aktivita
                if (akt1.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt1.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt1.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt1.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt1.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt1.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt1.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt1.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt1.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }
                if (akt2.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt2.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt2.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt2.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt2.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt2.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt2.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt2.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt2.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }
                if (akt3.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt3.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt3.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt3.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt3.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt3.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt3.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt3.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt3.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }
                if (akt4.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt4.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt4.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt4.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt4.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt4.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt4.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt4.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt4.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }

                if (akt5.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt5.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt5.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt5.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt5.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt5.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt5.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt5.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt5.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }

                //bonus
                if (bonus.equals( "1")){
                    znamka += 1;
                    vaha += 1;
                }
                if (bonus.equals( "1-") ){
                    znamka += 1.5;
                    vaha += 1;
                }
                if (bonus.equals( "2" )){
                    znamka += 2;
                    vaha += 1;
                }
                if (bonus.equals( "2-") ){
                    znamka += 2.5;
                    vaha += 1;
                }
                if (bonus.equals( "3" )){
                    znamka += 3;
                    vaha += 1;
                }
                if (bonus.equals( "3-") ){
                    znamka += 3.5;
                    vaha += 1;
                }
                if (bonus.equals( "4" )){
                    znamka += 4;
                    vaha += 1;
                }
                if (bonus.equals( "4-") ){
                    znamka += 4.5;
                    vaha += 1;
                }
                if (bonus.equals( "5" )){
                    znamka += 5;
                    vaha += 1;
                }


                double vyslednaZnamka;
                vyslednaZnamka = znamka / vaha;
                double rounted = Math.round(vyslednaZnamka*100)/100;
                String vyslednaZnamka2 = String.format("%.2f", vyslednaZnamka);
                pstHodnoceni = con.prepareStatement("update hodnoceni_zaci set zkouseni1 = ?, zkouseni2 = ?, test1 = ?, test2=?, test3=?, test4=?, test5=?, aktivita1=?, aktivita2=?, aktivita3=?, aktivita4=?, aktivita5=?, bonus=?, prumer=? where id = ? ");
                pstHodnoceni.setString(1, zk1);
                pstHodnoceni.setString(2, zk2);
                pstHodnoceni.setString(3, test1);
                pstHodnoceni.setString(4, test2);
                pstHodnoceni.setString(5, test3);
                pstHodnoceni.setString(6, test4);
                pstHodnoceni.setString(7, test5);
                pstHodnoceni.setString(8, akt1);
                pstHodnoceni.setString(9, akt2);
                pstHodnoceni.setString(10, akt3);
                pstHodnoceni.setString(11, akt4);
                pstHodnoceni.setString(12, akt5);
                pstHodnoceni.setString(13, bonus);
                pstHodnoceni.setString(14, String.valueOf(vyslednaZnamka2));
                pstHodnoceni.setString(15, id);
                pstHodnoceni.executeUpdate();
            }
            }catch (SQLException e){ JOptionPane.showMessageDialog(null, "Problém s databází!");}
        }
        if (panelClass2.isVisible() == true){
            try{for (int i=0;i<tableClass2.getRowCount(); i++) {
                String id = tableClass2.getValueAt(i, 0).toString();
                String zk1 = tableClass2.getValueAt(i, 3).toString();
                String zk2 = tableClass2.getValueAt(i, 4).toString();
                String test1 = tableClass2.getValueAt(i, 5).toString();
                String test2 = tableClass2.getValueAt(i, 6).toString();
                String test3 = tableClass2.getValueAt(i, 7).toString();
                String test4 = tableClass2.getValueAt(i, 8).toString();
                String test5 = tableClass2.getValueAt(i, 9).toString();
                String akt1 = tableClass2.getValueAt(i, 10).toString();
                String akt2 = tableClass2.getValueAt(i, 11).toString();
                String akt3 = tableClass2.getValueAt(i, 12).toString();
                String akt4 = tableClass2.getValueAt(i, 13).toString();
                String akt5 = tableClass2.getValueAt(i, 14).toString();
                String bonus = tableClass2.getValueAt(i, 15).toString();
                String pozn = tableClass2.getValueAt(i, 16).toString();
                //počet výsledné známky::

                double znamka = 0 ;
                int vaha = 0 ;
                //testy
                if (test1.equals("1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test1.equals("1-")){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test1.equals("2")){
                    znamka += 10;
                    vaha += 5;
                }
                if (test1.equals("2-")){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test1.equals("3")){
                    znamka += 15;
                    vaha += 5;
                }
                if (test1.equals("3-")){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test1.equals("4")){
                    znamka += 20;
                    vaha += 5;
                }
                if (test1.equals("4-")){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test1.equals( "5" )){
                    znamka += 25;
                    vaha += 5;
                }
                if (test2.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test2.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test2.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test2.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test2.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test2.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test2.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test2.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test2.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                if (test3.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test3.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test3.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test3.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test3.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test3.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test3.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test3.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test3.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                if (test4.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test4.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test4.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test4.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test4.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test4.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test4.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test4.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test4.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                if (test5.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test5.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test5.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test5.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test5.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test5.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test5.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test5.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test5.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                //zkouseni

                if (zk1.equals( "1")){
                    znamka += 4;
                    vaha += 4;
                }
                if (zk1.equals( "1-") ){
                    znamka += 6;
                    vaha += 4;
                }
                if (zk1.equals( "2" )){
                    znamka += 8;
                    vaha +=4;
                }
                if (zk1.equals( "2-") ){
                    znamka += 10;
                    vaha += 4;
                }
                if (zk1.equals( "3" )){
                    znamka += 12;
                    vaha += 4;
                }
                if (zk1.equals( "3-") ){
                    znamka += 14;
                    vaha += 4;
                }
                if (zk1.equals( "4" )){
                    znamka += 16;
                    vaha += 4;
                }
                if (zk1.equals( "4-") ){
                    znamka += 18;
                    vaha += 4;
                }
                if (zk1.equals( "5" )){
                    znamka += 20;
                    vaha += 4;
                }
                if (zk2.equals( "1")){
                    znamka += 4;
                    vaha += 4;
                }
                if (zk2.equals( "1-") ){
                    znamka += 6;
                    vaha += 4;
                }
                if (zk2.equals( "2" )){
                    znamka += 8;
                    vaha +=4;
                }
                if (zk2.equals( "2-") ){
                    znamka += 10;
                    vaha += 4;
                }
                if (zk2.equals( "3" )){
                    znamka += 12;
                    vaha += 4;
                }
                if (zk2.equals( "3-") ){
                    znamka += 14;
                    vaha += 4;
                }
                if (zk2.equals( "4" )){
                    znamka += 16;
                    vaha += 4;
                }
                if (zk2.equals( "4-") ){
                    znamka += 18;
                    vaha += 4;
                }
                if (zk2.equals( "5" )){
                    znamka += 20;
                    vaha += 4;
                }

                ///aktivita
                if (akt1.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt1.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt1.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt1.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt1.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt1.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt1.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt1.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt1.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }
                if (akt2.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt2.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt2.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt2.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt2.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt2.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt2.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt2.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt2.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }
                if (akt3.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt3.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt3.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt3.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt3.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt3.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt3.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt3.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt3.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }
                if (akt4.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt4.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt4.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt4.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt4.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt4.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt4.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt4.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt4.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }

                if (akt5.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt5.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt5.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt5.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt5.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt5.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt5.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt5.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt5.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }

                //bonus
                if (bonus.equals( "1")){
                    znamka += 1;
                    vaha += 1;
                }
                if (bonus.equals( "1-") ){
                    znamka += 1.5;
                    vaha += 1;
                }
                if (bonus.equals( "2" )){
                    znamka += 2;
                    vaha += 1;
                }
                if (bonus.equals( "2-") ){
                    znamka += 2.5;
                    vaha += 1;
                }
                if (bonus.equals( "3" )){
                    znamka += 3;
                    vaha += 1;
                }
                if (bonus.equals( "3-") ){
                    znamka += 3.5;
                    vaha += 1;
                }
                if (bonus.equals( "4" )){
                    znamka += 4;
                    vaha += 1;
                }
                if (bonus.equals( "4-") ){
                    znamka += 4.5;
                    vaha += 1;
                }
                if (bonus.equals( "5" )){
                    znamka += 5;
                    vaha += 1;
                }


                double vyslednaZnamka;
                vyslednaZnamka = znamka / vaha;
                String vyslednaZnamka2 = String.format("%.2f", vyslednaZnamka);
                pstHodnoceni = con.prepareStatement("update hodnoceni_zaci set zkouseni1 = ?, zkouseni2 = ?, test1 = ?, test2=?, test3=?, test4=?, test5=?, aktivita1=?, aktivita2=?, aktivita3=?, aktivita4=?, aktivita5=?, bonus=?, prumer=? where id = ? ");
                pstHodnoceni.setString(1, zk1);
                pstHodnoceni.setString(2, zk2);
                pstHodnoceni.setString(3, test1);
                pstHodnoceni.setString(4, test2);
                pstHodnoceni.setString(5, test3);
                pstHodnoceni.setString(6, test4);
                pstHodnoceni.setString(7, test5);
                pstHodnoceni.setString(8, akt1);
                pstHodnoceni.setString(9, akt2);
                pstHodnoceni.setString(10, akt3);
                pstHodnoceni.setString(11, akt4);
                pstHodnoceni.setString(12, akt5);
                pstHodnoceni.setString(13, bonus);
                pstHodnoceni.setString(14, String.valueOf(vyslednaZnamka2));
                pstHodnoceni.setString(15, id);
                pstHodnoceni.executeUpdate();
            }
            }catch (SQLException e){ JOptionPane.showMessageDialog(null, "Problém s databází!");}
        }
        if (panelClass3.isVisible() == true){
            try{for (int i=0;i<tableClass3.getRowCount(); i++) {
                String id = tableClass3.getValueAt(i, 0).toString();
                String zk1 = tableClass3.getValueAt(i, 3).toString();
                String zk2 = tableClass3.getValueAt(i, 4).toString();
                String test1 = tableClass3.getValueAt(i, 5).toString();
                String test2 = tableClass3.getValueAt(i, 6).toString();
                String test3 = tableClass3.getValueAt(i, 7).toString();
                String test4 = tableClass3.getValueAt(i, 8).toString();
                String test5 = tableClass3.getValueAt(i, 9).toString();
                String akt1 = tableClass3.getValueAt(i, 10).toString();
                String akt2 = tableClass3.getValueAt(i, 11).toString();
                String akt3 = tableClass3.getValueAt(i, 12).toString();
                String akt4 = tableClass3.getValueAt(i, 13).toString();
                String akt5 = tableClass3.getValueAt(i, 14).toString();
                String bonus = tableClass3.getValueAt(i, 15).toString();
                String pozn = tableClass3.getValueAt(i, 16).toString();
                //počet výsledné známky::

                double znamka = 0 ;
                int vaha = 0 ;
                //testy
                if (test1.equals("1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test1.equals("1-")){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test1.equals("2")){
                    znamka += 10;
                    vaha += 5;
                }
                if (test1.equals("2-")){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test1.equals("3")){
                    znamka += 15;
                    vaha += 5;
                }
                if (test1.equals("3-")){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test1.equals("4")){
                    znamka += 20;
                    vaha += 5;
                }
                if (test1.equals("4-")){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test1.equals( "5" )){
                    znamka += 25;
                    vaha += 5;
                }
                if (test2.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test2.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test2.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test2.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test2.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test2.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test2.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test2.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test2.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                if (test3.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test3.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test3.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test3.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test3.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test3.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test3.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test3.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test3.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                if (test4.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test4.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test4.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test4.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test4.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test4.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test4.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test4.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test4.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                if (test5.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test5.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test5.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test5.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test5.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test5.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test5.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test5.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test5.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                //zkouseni

                if (zk1.equals( "1")){
                    znamka += 4;
                    vaha += 4;
                }
                if (zk1.equals( "1-") ){
                    znamka += 6;
                    vaha += 4;
                }
                if (zk1.equals( "2" )){
                    znamka += 8;
                    vaha +=4;
                }
                if (zk1.equals( "2-") ){
                    znamka += 10;
                    vaha += 4;
                }
                if (zk1.equals( "3" )){
                    znamka += 12;
                    vaha += 4;
                }
                if (zk1.equals( "3-") ){
                    znamka += 14;
                    vaha += 4;
                }
                if (zk1.equals( "4" )){
                    znamka += 16;
                    vaha += 4;
                }
                if (zk1.equals( "4-") ){
                    znamka += 18;
                    vaha += 4;
                }
                if (zk1.equals( "5" )){
                    znamka += 20;
                    vaha += 4;
                }
                if (zk2.equals( "1")){
                    znamka += 4;
                    vaha += 4;
                }
                if (zk2.equals( "1-") ){
                    znamka += 6;
                    vaha += 4;
                }
                if (zk2.equals( "2" )){
                    znamka += 8;
                    vaha +=4;
                }
                if (zk2.equals( "2-") ){
                    znamka += 10;
                    vaha += 4;
                }
                if (zk2.equals( "3" )){
                    znamka += 12;
                    vaha += 4;
                }
                if (zk2.equals( "3-") ){
                    znamka += 14;
                    vaha += 4;
                }
                if (zk2.equals( "4" )){
                    znamka += 16;
                    vaha += 4;
                }
                if (zk2.equals( "4-") ){
                    znamka += 18;
                    vaha += 4;
                }
                if (zk2.equals( "5" )){
                    znamka += 20;
                    vaha += 4;
                }

                ///aktivita
                if (akt1.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt1.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt1.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt1.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt1.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt1.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt1.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt1.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt1.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }
                if (akt2.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt2.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt2.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt2.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt2.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt2.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt2.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt2.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt2.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }
                if (akt3.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt3.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt3.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt3.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt3.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt3.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt3.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt3.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt3.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }
                if (akt4.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt4.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt4.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt4.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt4.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt4.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt4.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt4.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt4.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }

                if (akt5.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt5.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt5.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt5.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt5.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt5.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt5.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt5.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt5.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }

                //bonus
                if (bonus.equals( "1")){
                    znamka += 1;
                    vaha += 1;
                }
                if (bonus.equals( "1-") ){
                    znamka += 1.5;
                    vaha += 1;
                }
                if (bonus.equals( "2" )){
                    znamka += 2;
                    vaha += 1;
                }
                if (bonus.equals( "2-") ){
                    znamka += 2.5;
                    vaha += 1;
                }
                if (bonus.equals( "3" )){
                    znamka += 3;
                    vaha += 1;
                }
                if (bonus.equals( "3-") ){
                    znamka += 3.5;
                    vaha += 1;
                }
                if (bonus.equals( "4" )){
                    znamka += 4;
                    vaha += 1;
                }
                if (bonus.equals( "4-") ){
                    znamka += 4.5;
                    vaha += 1;
                }
                if (bonus.equals( "5" )){
                    znamka += 5;
                    vaha += 1;
                }


                double vyslednaZnamka;
                vyslednaZnamka = znamka / vaha;
                String vyslednaZnamka2 = String.format("%.2f", vyslednaZnamka);
                pstHodnoceni = con.prepareStatement("update hodnoceni_zaci set zkouseni1 = ?, zkouseni2 = ?, test1 = ?, test2=?, test3=?, test4=?, test5=?, aktivita1=?, aktivita2=?, aktivita3=?, aktivita4=?, aktivita5=?, bonus=?, prumer=? where id = ? ");
                pstHodnoceni.setString(1, zk1);
                pstHodnoceni.setString(2, zk2);
                pstHodnoceni.setString(3, test1);
                pstHodnoceni.setString(4, test2);
                pstHodnoceni.setString(5, test3);
                pstHodnoceni.setString(6, test4);
                pstHodnoceni.setString(7, test5);
                pstHodnoceni.setString(8, akt1);
                pstHodnoceni.setString(9, akt2);
                pstHodnoceni.setString(10, akt3);
                pstHodnoceni.setString(11, akt4);
                pstHodnoceni.setString(12, akt5);
                pstHodnoceni.setString(13, bonus);
                pstHodnoceni.setString(14, String.valueOf(vyslednaZnamka2));
                pstHodnoceni.setString(15, id);
                pstHodnoceni.executeUpdate();
            }
            }catch (SQLException e){ JOptionPane.showMessageDialog(null, "Problém s databází!");}
        }
        if (panelClass4.isVisible() == true){
            try{for (int i=0;i<tableClass4.getRowCount(); i++) {
                String id = tableClass4.getValueAt(i, 0).toString();
                String zk1 = tableClass4.getValueAt(i, 3).toString();
                String zk2 = tableClass4.getValueAt(i, 4).toString();
                String test1 = tableClass4.getValueAt(i, 5).toString();
                String test2 = tableClass4.getValueAt(i, 6).toString();
                String test3 = tableClass4.getValueAt(i, 7).toString();
                String test4 = tableClass4.getValueAt(i, 8).toString();
                String test5 = tableClass4.getValueAt(i, 9).toString();
                String akt1 = tableClass4.getValueAt(i, 10).toString();
                String akt2 = tableClass4.getValueAt(i, 11).toString();
                String akt3 = tableClass4.getValueAt(i, 12).toString();
                String akt4 = tableClass4.getValueAt(i, 13).toString();
                String akt5 = tableClass4.getValueAt(i, 14).toString();
                String bonus = tableClass4.getValueAt(i, 15).toString();
                String pozn = tableClass4.getValueAt(i, 16).toString();
                //počet výsledné známky::

                double znamka = 0 ;
                int vaha = 0 ;
                //testy
                if (test1.equals("1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test1.equals("1-")){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test1.equals("2")){
                    znamka += 10;
                    vaha += 5;
                }
                if (test1.equals("2-")){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test1.equals("3")){
                    znamka += 15;
                    vaha += 5;
                }
                if (test1.equals("3-")){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test1.equals("4")){
                    znamka += 20;
                    vaha += 5;
                }
                if (test1.equals("4-")){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test1.equals( "5" )){
                    znamka += 25;
                    vaha += 5;
                }
                if (test2.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test2.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test2.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test2.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test2.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test2.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test2.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test2.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test2.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                if (test3.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test3.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test3.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test3.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test3.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test3.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test3.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test3.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test3.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                if (test4.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test4.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test4.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test4.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test4.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test4.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test4.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test4.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test4.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                if (test5.equals( "1")){
                    znamka += 5;
                    vaha += 5;
                }
                if (test5.equals( "1-") ){
                    znamka += 7.5;
                    vaha += 5;
                }
                if (test5.equals( "2" )){
                    znamka += 10;
                    vaha += 5;
                }
                if (test5.equals( "2-") ){
                    znamka += 12.5;
                    vaha += 5;
                }
                if (test5.equals( "3" )){
                    znamka += 15;
                    vaha += 5;
                }
                if (test5.equals( "3-") ){
                    znamka += 17.5;
                    vaha += 5;
                }
                if (test5.equals( "4" )){
                    znamka += 20;
                    vaha += 5;
                }
                if (test5.equals( "4-") ){
                    znamka += 22.5;
                    vaha += 5;
                }
                if (test5.equals( "5" )) {
                    znamka += 25;
                    vaha += 5;
                }
                //zkouseni

                if (zk1.equals( "1")){
                    znamka += 4;
                    vaha += 4;
                }
                if (zk1.equals( "1-") ){
                    znamka += 6;
                    vaha += 4;
                }
                if (zk1.equals( "2" )){
                    znamka += 8;
                    vaha +=4;
                }
                if (zk1.equals( "2-") ){
                    znamka += 10;
                    vaha += 4;
                }
                if (zk1.equals( "3" )){
                    znamka += 12;
                    vaha += 4;
                }
                if (zk1.equals( "3-") ){
                    znamka += 14;
                    vaha += 4;
                }
                if (zk1.equals( "4" )){
                    znamka += 16;
                    vaha += 4;
                }
                if (zk1.equals( "4-") ){
                    znamka += 18;
                    vaha += 4;
                }
                if (zk1.equals( "5" )){
                    znamka += 20;
                    vaha += 4;
                }
                if (zk2.equals( "1")){
                    znamka += 4;
                    vaha += 4;
                }
                if (zk2.equals( "1-") ){
                    znamka += 6;
                    vaha += 4;
                }
                if (zk2.equals( "2" )){
                    znamka += 8;
                    vaha +=4;
                }
                if (zk2.equals( "2-") ){
                    znamka += 10;
                    vaha += 4;
                }
                if (zk2.equals( "3" )){
                    znamka += 12;
                    vaha += 4;
                }
                if (zk2.equals( "3-") ){
                    znamka += 14;
                    vaha += 4;
                }
                if (zk2.equals( "4" )){
                    znamka += 16;
                    vaha += 4;
                }
                if (zk2.equals( "4-") ){
                    znamka += 18;
                    vaha += 4;
                }
                if (zk2.equals( "5" )){
                    znamka += 20;
                    vaha += 4;
                }

                ///aktivita
                if (akt1.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt1.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt1.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt1.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt1.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt1.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt1.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt1.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt1.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }
                if (akt2.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt2.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt2.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt2.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt2.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt2.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt2.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt2.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt2.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }
                if (akt3.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt3.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt3.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt3.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt3.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt3.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt3.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt3.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt3.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }
                if (akt4.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt4.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt4.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt4.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt4.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt4.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt4.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt4.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt4.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }

                if (akt5.equals( "1")){
                    znamka += 2;
                    vaha += 2;
                }
                if (akt5.equals( "1-") ){
                    znamka += 3;
                    vaha += 2;
                }
                if (akt5.equals( "2" )){
                    znamka += 4;
                    vaha += 2;
                }
                if (akt5.equals( "2-") ){
                    znamka += 5;
                    vaha += 2;
                }
                if (akt5.equals( "3" )){
                    znamka += 6;
                    vaha += 2;
                }
                if (akt5.equals( "3-") ){
                    znamka += 7;
                    vaha += 2;
                }
                if (akt5.equals( "4" )){
                    znamka += 8;
                    vaha += 2;
                }
                if (akt5.equals( "4-") ){
                    znamka += 9;
                    vaha += 2;
                }
                if (akt5.equals( "5" )){
                    znamka += 10;
                    vaha += 2;
                }

                //bonus
                if (bonus.equals( "1")){
                    znamka += 1;
                    vaha += 1;
                }
                if (bonus.equals( "1-") ){
                    znamka += 1.5;
                    vaha += 1;
                }
                if (bonus.equals( "2" )){
                    znamka += 2;
                    vaha += 1;
                }
                if (bonus.equals( "2-") ){
                    znamka += 2.5;
                    vaha += 1;
                }
                if (bonus.equals( "3" )){
                    znamka += 3;
                    vaha += 1;
                }
                if (bonus.equals( "3-") ){
                    znamka += 3.5;
                    vaha += 1;
                }
                if (bonus.equals( "4" )){
                    znamka += 4;
                    vaha += 1;
                }
                if (bonus.equals( "4-") ){
                    znamka += 4.5;
                    vaha += 1;
                }
                if (bonus.equals( "5" )){
                    znamka += 5;
                    vaha += 1;
                }


                double vyslednaZnamka;
                vyslednaZnamka = znamka / vaha;
                String vyslednaZnamka2 = String.format("%.2f", vyslednaZnamka);
                pstHodnoceni = con.prepareStatement("update hodnoceni_zaci set zkouseni1 = ?, zkouseni2 = ?, test1 = ?, test2=?, test3=?, test4=?, test5=?, aktivita1=?, aktivita2=?, aktivita3=?, aktivita4=?, aktivita5=?, bonus=?, prumer=? where id = ? ");
                pstHodnoceni.setString(1, zk1);
                pstHodnoceni.setString(2, zk2);
                pstHodnoceni.setString(3, test1);
                pstHodnoceni.setString(4, test2);
                pstHodnoceni.setString(5, test3);
                pstHodnoceni.setString(6, test4);
                pstHodnoceni.setString(7, test5);
                pstHodnoceni.setString(8, akt1);
                pstHodnoceni.setString(9, akt2);
                pstHodnoceni.setString(10, akt3);
                pstHodnoceni.setString(11, akt4);
                pstHodnoceni.setString(12, akt5);
                pstHodnoceni.setString(13, bonus);
                pstHodnoceni.setString(14, String.valueOf(vyslednaZnamka2));
                pstHodnoceni.setString(15, id);
                pstHodnoceni.executeUpdate();
            }
            }catch (SQLException e){ JOptionPane.showMessageDialog(null, "Problém s databází!");}
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
