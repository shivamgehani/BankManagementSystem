package bank_management.operations;

//import java.io.IOException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.Random;

public class Register implements ActionListener {

    long cnic = 0;
    long m_phone = 0;
    double balance = 0;

    JFrame frame3;
    JLabel login1, imageicon2, l_name, l_cnic, l_phone, l_balance, l_gender, acc_no, l_accid;
    JTextField t_name, t_cnic, t_phone, textbalance, t_accid;
    JButton back, Create;
    JRadioButton r_button1, r_button2;
    String radiobutton = "";
    String url = "jdbc:ucanaccess://bank.accdb";

    public Register() {

        frame3 = new JFrame("CREATE ACCOUNT");

        ImageIcon ic2 = new ImageIcon("Images/create.jpg");
        imageicon2 = new JLabel(null, ic2, JLabel.CENTER);
        imageicon2.setBounds(5, 0, 400, 600);

        Font ft1 = new Font("Times New Roman", Font.BOLD, 22);
        login1 = new JLabel("CREATE ACCOUNT");
        login1.setBounds(90, 0, 300, 150);
        login1.setFont(ft1);
        login1.setForeground(Color.white);

        Font ft2 = new Font("Times New Roman", Font.BOLD, 16);
        l_accid = new JLabel("ACCOUNT ID");
        l_accid.setBounds(30, 45, 150, 150);
        l_accid.setFont(ft2);
        l_accid.setForeground(Color.white);
        t_accid = new JTextField();
        Random rondom = new Random();
        int i = rondom.nextInt(100);
        t_accid.setText(i + "");
        t_accid.setEnabled(false);
        t_accid.setBounds(190, 100, 150, 30);

        l_name = new JLabel("NAME");
        l_name.setBounds(30, 100, 150, 150);
        l_name.setFont(ft2);
        l_name.setForeground(Color.white);
        t_name = new JTextField();
        t_name.setBounds(190, 155, 150, 30);

        l_cnic = new JLabel("CNIC");
        l_cnic.setBounds(30, 155, 150, 150);
        l_cnic.setFont(ft2);
        l_cnic.setForeground(Color.white);
        t_cnic = new JTextField();
        t_cnic.setBounds(190, 210, 150, 30);

        l_phone = new JLabel("MOBILE NUMBER");
        l_phone.setBounds(30, 210, 150, 150);
        l_phone.setFont(ft2);
        l_phone.setForeground(Color.white);
        t_phone = new JTextField();
        t_phone.setBounds(190, 265, 150, 30);

        l_balance = new JLabel("BALANCE");
        l_balance.setBounds(30, 320, 150, 150);
        l_balance.setFont(ft2);
        l_balance.setForeground(Color.white);
        textbalance = new JTextField();
        textbalance.setBounds(190, 375, 150, 30);

        l_gender = new JLabel("GENDER");
        l_gender.setBounds(30, 265, 150, 150);
        l_gender.setFont(ft2);
        l_gender.setForeground(Color.white);

        r_button1 = new JRadioButton("MALE");
        r_button1.setBounds(170, 325, 70, 30);
        r_button1.setForeground(Color.black);
        r_button1.addActionListener((ActionEvent e) -> {
            r_button1.setSelected(true);
            r_button2.setSelected(false);
            radiobutton = "Male";
        });

        r_button2 = new JRadioButton("FEMALE");
        r_button2.setBounds(270, 325, 90, 30);
        r_button2.setForeground(Color.black);
        r_button2.addActionListener((ActionEvent e) -> {
            r_button1.setSelected(false);
            r_button2.setSelected(true);
            radiobutton = "Female";
        });

        back = new JButton("BACK");
        back.setBounds(5, 0, 80, 30);
        back.setBackground(Color.gray);
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.addActionListener(this);

        Create = new JButton("CREATE");
        Create.setBounds(145, 480, 100, 40);
        Create.setBackground(Color.gray);
        Create.setOpaque(true);
        Create.setBorderPainted(false);
        Create.addActionListener(this);

        frame3.add(t_accid);
        frame3.add(l_accid);
        frame3.add(l_gender);
        frame3.add(r_button1);
        frame3.add(r_button2);
        frame3.add(Create);
        frame3.add(textbalance);
        frame3.add(t_cnic);
        frame3.add(t_name);
        frame3.add(t_phone);
        frame3.add(l_balance);
        frame3.add(l_phone);
        frame3.add(l_name);
        frame3.add(l_cnic);
        frame3.add(back);
        frame3.add(login1);
        frame3.add(imageicon2);

        frame3.setSize(400, 600);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setLayout(null);
        frame3.setVisible(true);
        frame3.setLocationRelativeTo(null);
        frame3.setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            frame3.setVisible(false);
            new Front();
        }
        if (ae.getSource() == Create) {
            if (t_name.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Empty_Name_Field");
            }
            if (t_cnic.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Empty_Cnic_Field");
            }
            if (t_phone.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Empty_Phone_Field");
            }
            if (textbalance.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Empty_Balance_Field");
            }
            if (radiobutton.equals("")) {
                JOptionPane.showMessageDialog(null, "Select_Gender_Field");
            }
            if (!(t_name.getText().equals("") || t_cnic.getText().equals("") || t_phone.getText().equals("") || textbalance.getText().equals(""))) {
                try {
                    cnic = Long.parseLong(t_cnic.getText());
                    try {
                        m_phone = Long.parseLong(t_phone.getText());
                        try {
                            balance = Double.parseDouble(textbalance.getText());
                            if (balance < 0) {
                                JOptionPane.showMessageDialog(new JFrame(), "Balance Should Not Be in Minus", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                Method();
                            }

                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Balance Should Be Numeric", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Phone Field Should Be Numeric", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "CNIC Should Be Numeric", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        }

    }

    void Method() {
        try {
            Connection con;
            Statement stmt, stmt1, stmt2;
            ResultSet res;

            con = DriverManager.getConnection(url);

            int bal = 0;
            stmt = con.createStatement();
            String forbalance = "select * from bank_info";
            res = stmt.executeQuery(forbalance);
            while (res.next()) {
                bal = res.getInt("b_balance");
            }

            stmt1 = con.createStatement();
            res = stmt1.executeQuery(forbalance);
            String id1 = "";
            while (res.isLast()) {
                id1 = res.getString("c_accid");
            }
            System.out.println(id1);

            int rowsUpdated = 0;
            if (bal > balance) {
                double total = balance + bal;

                String sql = "INSERT INTO customer_info(c_accid,c_name,c_cnic,c_phone,c_gender,c_balance) VALUES ('" + t_accid.getText() + "','" + t_name.getText() + "','" + cnic + "','" + m_phone + "','" + radiobutton + "','" + balance + "')";
                rowsUpdated = stmt.executeUpdate(sql);

                String sql1 = "update bank_info Set b_balance = ('" + total + "')";
                stmt2 = con.createStatement();
                stmt2.executeUpdate(sql1);
            } else {
                JOptionPane.showMessageDialog(imageicon2, "Bank Have Enough Amount");
            }

            if (rowsUpdated > 0) {
                System.out.println("Accoutn SuccessFully Create");
                JOptionPane.showMessageDialog(imageicon2, "Account Created Successfully");
                con.close();

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
