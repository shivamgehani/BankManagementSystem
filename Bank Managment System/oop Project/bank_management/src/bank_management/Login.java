
package bank_management;

import bank_management.operations.Front;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Login
{
    
    Login()
    {
        JFrame frame2;
        JLabel imageicon1,password,login2;
        JTextField textlogin;
        JPasswordField textpassword;
        JButton btn_login;

        frame2 = new JFrame("LOGIN PAGE");
        
        ImageIcon ic1 = new ImageIcon("Images/bank_cover.jpg");
        imageicon1 = new JLabel(ic1);
        imageicon1.setBounds(5,0,400,500);
        
        

        Font ft2=new Font("Times New Roman",Font.BOLD,15);
        login2 = new JLabel("UserName");
        login2.setBounds(50,100,150,300);
        login2.setFont(ft2);
        login2.setForeground(Color.BLACK);
        textlogin = new JTextField();
        textlogin.setBounds(140,230,150,30);

        password = new JLabel("Password");
        password.setBounds(50,153,150,310);
        password.setFont(ft2);
        password.setForeground(Color.black);
        textpassword = new JPasswordField();
        textpassword.setBounds(140,290,150,30);
       

        btn_login = new JButton("Login");
        btn_login.setBounds(170,350,80,50);
        btn_login.setBackground(Color.white);
        btn_login.setOpaque(true);
        btn_login.setBorderPainted(false);
        btn_login.addActionListener((ActionEvent e) -> {
        if(textlogin.getText().equals("Admin") && textpassword.getText().equals("test123"))
            {
                frame2.setVisible(false);
                new Front();
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Wrong Email Or Password");
                    
            }
            
        });

        
        
        frame2.add(btn_login);
        frame2.add(textpassword);
        frame2.add(textlogin);
        frame2.add(login2);
        frame2.add(password);
        frame2.add(imageicon1);
        frame2.setSize(400,490);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setLayout(null);
        frame2.setVisible(true);
        frame2.setLocationRelativeTo(null);
        frame2.setResizable(false);
    }
    public static void main(String ar[])
    {

        new Login();
    }
}
