
package bank_management.operations;
import javax.swing.*;
import java.awt.event.*;
//import java.rmi.server.Operation;
import java.awt.*;
import java.sql.*;
class Withdraw implements ActionListener
{
    JFrame withdraw;
    JLabel label,imageicon,id,balance;
    JButton bwithdraw,back;
    JTextField idt,balancet;
    String url = "jdbc:ucanaccess://bank.accdb";
    Withdraw()
    {
        withdraw = new JFrame("Withdraw");
        
        ImageIcon ic = new ImageIcon("Images/create.jpg");
        imageicon = new JLabel("Logo",ic,JLabel.CENTER);
        imageicon.setBounds(5,0,400,600);
        
        Font ft2=new Font("Times New Roman",Font.BOLD,16);
        id = new JLabel("ACCOUNT ID");
        id.setBounds(50,100,150,150);
        id.setFont(ft2);
        id.setForeground(Color.black);
        idt = new JTextField();
        idt.setBounds(160,155,150,30);

        balance = new JLabel("BALANCE");
        balance.setBounds(50,150,150,150);
        balance.setFont(ft2);
        balance.setForeground(Color.black);
        balancet = new JTextField();
        balancet.setBounds(160,205,150,30);



        back = new JButton("BACK");
        back.setBounds(5,0,80,30);
        back.setBackground(Color.gray);
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.addActionListener(this);
        
        bwithdraw = new JButton("WITHDRAW");
        bwithdraw.setBounds(130,300,130,40);
        bwithdraw.setBackground(Color.gray);
        bwithdraw.setOpaque(true);
        bwithdraw.setBorderPainted(false);
        bwithdraw.addActionListener(this);
        

        withdraw.add(bwithdraw);
        withdraw.add(balancet);
        withdraw.add(balance);
        withdraw.add(idt);
        withdraw.add(id);        
        withdraw.add(back);
        withdraw.add(imageicon);

        withdraw.setSize(400,600);
        withdraw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        withdraw.setLayout(null);
        withdraw.setVisible(true);
        withdraw.setLocationRelativeTo(null);
        withdraw.setResizable(false);
    }
     public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==back)
        {
            withdraw.setVisible(false);
            new Operation1();
        }
        if(ae.getSource()==bwithdraw)
        {
            
            String value = idt.getText();
            String balances = balancet.getText();
            int s = 0;
            if(!(value.equals("") && balances.equals("")))
            {
                try
                {
                    
                    int accid = Integer.parseInt(value);
                    int balance = Integer.parseInt(balances);
                    if(balance>0){
                        try
                        {
                            Connection con;
                            Statement stmt,stmt1,stmt2,stmt3;
                            ResultSet res,res1;

                            con = DriverManager.getConnection(url);

                            int bal = 0 ;
                            stmt = con.createStatement();
                            String forbalance = "select * from bank_info";
                            res = stmt.executeQuery(forbalance);
                            while(res.next())
                            {
                                 bal = res.getInt("b_balance");
                            }
                            

                            stmt1 = con.createStatement();
                            String searchQ = "select c_accid from customer_info";
                            res = stmt1.executeQuery(searchQ);
                            
                            int a = 0;
                            
                            while(res.next())
                            {
                                a = res.getInt("c_accid");
                                if(a==accid)
                                {
                                    String searchQa = "select c_balance from customer_info where c_accid = '"+a+"'";
                                    stmt3 = con.createStatement();
                                    res1 = stmt3.executeQuery(searchQa);
                                    int cbalance = 0;
                                    while(res1.next())
                                    {
                                          cbalance = res1.getInt("c_balance");
                                    }
                                    int rowsUpdated = 0;
                                    if(bal>balance)
                                    {
                                        cbalance-=balance;
                                        if(cbalance>0)
                                        {
                                            String sql1 = "update customer_info Set c_balance = ('"+cbalance+"') where c_accid = '"+accid+"'";
                                            stmt2 = con.createStatement();
                                            rowsUpdated = stmt2.executeUpdate(sql1);
                                        }
                                        else
                                        {
                                            JOptionPane.showMessageDialog(withdraw,"Enough Amount");
                                            break;
                                        }
                                        
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(withdraw,"Bank Have Enough Amount");
                                    }
                                     
                                    if(rowsUpdated > 0) 
                                    {
                             
                                        JOptionPane.showMessageDialog(withdraw,"Withdraw Successfully");
                                        con.close();
                                        break;
                                    }
 
                                }
                                while(res.isLast())
                                {
                                    JOptionPane.showMessageDialog(withdraw,"Invalid Account ID");
                                    break;
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
                else
                    {
                        JOptionPane.showMessageDialog(withdraw,"Amount is Negative/zero");
                    }
            }
            catch(NumberFormatException ea)
            {
                    JOptionPane.showMessageDialog(withdraw,"Wrong Account ID");
            }
            
        
            }
        }
    }
    
}
