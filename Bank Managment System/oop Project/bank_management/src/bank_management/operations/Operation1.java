package bank_management.operations;

import bank_management.operations.Service;
import bank_management.operations.Withdraw;
//import java.io.IOException.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Operation1 implements ActionListener {

    JFrame mainpage;
    String url = "jdbc:ucanaccess://bank.accdb";
    String id = " ";
    JLabel label, imageicon;
    JButton register, deposit, service, withdraw, display, deleteAcc,back;

    public Operation1() {

        mainpage = new JFrame();

        ImageIcon ic = new ImageIcon("Images/create.jpg");
        imageicon = new JLabel("Logo", ic, JLabel.CENTER);
        imageicon.setBounds(5, 0, 400, 600);

        Font ft2 = new Font("Times New Roman", Font.BOLD, 20);
        label = new JLabel("WELCOME");
        label.setBounds(150, 5, 300, 150);
        label.setFont(ft2);
        label.setForeground(Color.black);

        Font ft1 = new Font("Times New Roman", Font.BOLD, 14);
        deposit = new JButton("Deposit Amount");
        deposit.setBounds(40, 150, 140, 70);
        deposit.setBackground(Color.white);
        deposit.setFont(ft1);
        deposit.setOpaque(true);
        deposit.setBorderPainted(true);
        deposit.addActionListener((ActionEvent e) -> {
            mainpage.setVisible(false);
            new Deposit();
        });

        withdraw = new JButton("Withdraw Amount");
        withdraw.setBounds(220, 150, 150, 70);
        withdraw.setBackground(Color.white);
        withdraw.setFont(ft1);
        withdraw.setOpaque(true);
        withdraw.setBorderPainted(true);
        withdraw.addActionListener((ActionEvent e) -> {
            mainpage.setVisible(false);
            new Withdraw();
        });

        service = new JButton("Other Services");
        service.setBounds(220, 300, 150, 70);
        service.setBackground(Color.white);
        service.setOpaque(true);
        service.setFont(ft1);
        service.setBorderPainted(true);
        service.addActionListener((ActionEvent e) -> {
            mainpage.setVisible(false);
            new Service();
        });

        display = new JButton("Display Info");
        display.setBounds(40, 300, 140, 70);
        display.setBackground(Color.white);
        display.setOpaque(true);
        display.setFont(ft1);
        display.setBorderPainted(true);
        display.addActionListener((ActionEvent e) -> {
            mainpage.setVisible(false);
            new Display();
        });

        deleteAcc = new JButton("Delete Account");
        deleteAcc.setBounds(135, 450, 140, 70);
        deleteAcc.setBackground(Color.white);
        deleteAcc.setOpaque(true);
        deleteAcc.setFont(ft1);
        deleteAcc.setBorderPainted(true);
        deleteAcc.addActionListener((ActionEvent e) -> {
            
            id = JOptionPane.showInputDialog(mainpage, "Enter Account ID");
         
            try{
                if (!(id.equals("") || id.equals(null))) {
                try {
                     
                    String ida, name, mphone, gender, cnic;
                    int balance;
                    int x = Integer.parseInt(id);
                    try {
                        Connection con;
                        Statement stmt, stmt1, stmt2, stmt3;
                        ResultSet rs;

                        con = DriverManager.getConnection(url);

                        stmt = con.createStatement();
                        String forid = "select * from customer_info";

                        stmt1 = con.createStatement();
                        rs = stmt1.executeQuery(forid);
                        String id12;
                        int id1;
                        while (rs.next()) {
                            id12 = rs.getString("c_accid");
                            id1 = Integer.parseInt(id12);
                            if (x == id1) {

                                ida = rs.getString("c_accid");
                                name = rs.getString("c_name");
                                cnic = rs.getString("c_cnic");
                                mphone = rs.getString("c_phone");
                                gender = rs.getString("c_gender");

                                //System.out.println(ida+" "+name+"  "+cnic+" ");
                                String deteleQ = "DELETE from customer_info where c_accid = '" + x + "'";
                                stmt2 = con.createStatement();
                                stmt2.executeUpdate(deteleQ);
                                String insertQ = "INSERT INTO deleted_info(d_id,d_name,d_cnic,d_mobile,d_gender) VALUES ('" + ida + "','" + name + "','" + cnic + "','" + mphone + "','" + gender + "')";
                                stmt3 = con.createStatement();
                                stmt3.executeUpdate(insertQ);
                                JOptionPane.showMessageDialog(mainpage, "Account Deleted Successfully");
                                break;
                            } else if (rs.isLast()) {
                                JOptionPane.showMessageDialog(mainpage, "Account Id Not Found");
                            }
                        }

                    }  catch (Exception en) {
                        System.out.println(en.getMessage());
                    }
                } 
                catch (Exception ea) {
                    JOptionPane.showMessageDialog(mainpage, "Wrong Input", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
                else {
                        JOptionPane.showMessageDialog(mainpage,"Empty Field");
            }
            }
            catch(Exception ex)
            {
                  JOptionPane.showMessageDialog(mainpage,"Empty Field");
            }

        });
        
        back = new JButton("BACK");
        back.setBounds(5, 0, 80, 30);
        back.setBackground(Color.gray);
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.addActionListener(this);

        mainpage.add(deleteAcc);
        mainpage.add(label);
        mainpage.add(service);
        mainpage.add(display);
        mainpage.add(withdraw);
        mainpage.add(deposit);
        mainpage.add(imageicon);
        mainpage.add(back);
        mainpage.setSize(400, 600);
        mainpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainpage.setLayout(null);
        mainpage.setVisible(true);
        mainpage.setLocationRelativeTo(null);
    }
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            mainpage.setVisible(false);
            new Front();
        }
    }
}

