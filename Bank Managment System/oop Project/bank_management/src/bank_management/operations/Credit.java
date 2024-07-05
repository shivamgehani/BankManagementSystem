package bank_management.operations;
import bank_management.operations.Operation1;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
public class Credit implements ActionListener
{
    int idtext = 0 ;
    Connection con;
    Statement stmt,stmt1;
    ResultSet res;
    JFrame credit;
    JLabel imageicon,id;
    JButton back,bclick;
    JTextField idt;
    String url = "jdbc:ucanaccess://bank.accdb";
    public Credit()
    {
    
        credit = new JFrame("Credit card");
        
        ImageIcon ic = new ImageIcon("Images/create.jpg");
        imageicon = new JLabel("Logo",ic,JLabel.CENTER);
        imageicon.setBounds(5,0,400,600);
        
        Font ft2=new Font("Times New Roman",Font.BOLD,16);
        id = new JLabel("ACCOUNT ID");
        id.setBounds(40,100,150,150);
        id.setFont(ft2);
        id.setForeground(Color.white);
        idt = new JTextField();
        idt.setBounds(160,155,150,30);

        bclick = new JButton("SUBMIT");
        bclick.setBounds(130,250,130,40);
        bclick.setBackground(Color.gray);
        bclick.setOpaque(true);
        bclick.setBorderPainted(false);
        bclick.addActionListener(this);



        back = new JButton("BACK");
        back.setBounds(5,0,80,30);
        back.setBackground(Color.gray);
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.addActionListener(this);
        

        

        
        credit.add(bclick);
        credit.add(idt);
        credit.add(id);        
        credit.add(back);
        credit.add(imageicon);

        credit.setSize(400,600);
        credit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        credit.setLayout(null);
        credit.setVisible(true);
        credit.setLocationRelativeTo(null);
        credit.setResizable(false);
    }
     public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==back)
        {
            credit.setVisible(false);
            new Service();
        }
        if(ae.getSource()==bclick)
        {
            if(!(idt.getText().equals("")))
            {
                try
                {
                    idtext = Integer.parseInt(idt.getText());
                    try
                    {
                        Connection con;
                        Statement stmt,stmt1,stmt2;
                        ResultSet res;

                        con = DriverManager.getConnection(url);


                        stmt = con.createStatement();
                        String forid = "select * from customer_info";


                        stmt1 = con.createStatement();
                        res = stmt1.executeQuery(forid);
                        String id12;
                        int id1;
                        while(res.next())
                        {
                            id12 =res.getString("c_accid");
                            id1 = Integer.parseInt(id12);
                            if(idtext==id1)
                            {
                                String credit1 = "Yes";

                                String sql = "update customer_info set c_credit = '"+credit1+"' where c_accid = '"+idtext+"' " ;
                                stmt.executeUpdate(sql);
                                JOptionPane.showMessageDialog(credit,"Credit Card Added Successfully");

                            }
                            else if(res.isLast())
                            {
                                JOptionPane.showMessageDialog(credit,"Account Id Not Found");
                            }
                        }
                        
                    }

                    catch(SQLException e)
                    {
                        System.out.println(e.getMessage());
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
            }
            catch(NumberFormatException w)
            {
                JOptionPane.showMessageDialog(imageicon,"Wrong input");
            }
            }
            else
            {
                JOptionPane.showMessageDialog(imageicon,"Account Field Is Empty");
            }
        }
    }
    
        
           
 
}