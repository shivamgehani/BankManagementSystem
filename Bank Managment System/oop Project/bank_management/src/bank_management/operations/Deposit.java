
package bank_management.operations;
import bank_management.operations.Operation1;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
public class Deposit implements ActionListener{
    JFrame deposit;
    JLabel imageicon,id,amount;
    JButton bdeposit,back;
    JTextField idt,amount_t;
    String url = "jdbc:ucanaccess://bank.accdb";
    public Deposit()
    {
        deposit = new JFrame("Deposit");
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

        amount = new JLabel("AMOUNT");
        amount.setBounds(50,150,150,150);
        amount.setFont(ft2);
        amount.setForeground(Color.black);
        amount_t = new JTextField();
        amount_t.setBounds(160,205,150,30);
        
        bdeposit = new JButton("DEPOSIT");
        bdeposit.setBounds(130,300,130,40);
        bdeposit.setBackground(Color.gray);
        bdeposit.setOpaque(true);
        bdeposit.setBorderPainted(false);
        bdeposit.addActionListener(this);

        back = new JButton("BACK");
        back.setBounds(5,0,80,30);
        back.setBackground(Color.gray);
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.addActionListener(this);


        deposit.add(id);
        deposit.add(idt);
        deposit.add(amount);
        deposit.add(amount_t);
        deposit.add(bdeposit);
        deposit.add(back);
        deposit.add(imageicon);
        

        deposit.setSize(400,600);
        deposit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deposit.setLayout(null);
        deposit.setVisible(true);
        deposit.setLocationRelativeTo(null);
        deposit.setResizable(false);
    }
     public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==back)
        {
            deposit.setVisible(false);
            new Operation1();
        }
        if(ae.getSource()==bdeposit)
        {
            
            String value = idt.getText();
            String balances = amount_t.getText();
            int s = 0;
            if(!(value.equals("") && balances.equals("")))
            {
                try
                {
                    
                    int accid = Integer.parseInt(value);
                    int balance = Integer.parseInt(balances);
                    if(balance >0)
                    {
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
                                        balance+=cbalance;
                                        String sql1 = "update customer_info Set c_balance = ('"+balance+"') where c_accid = '"+accid+"'";
                                        stmt2 = con.createStatement();
                                        rowsUpdated = stmt2.executeUpdate(sql1);
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(deposit,"Bank Have Enough Amount");
                                    }
                                     
                                    if(rowsUpdated > 0) 
                                    {
                             
                                        JOptionPane.showMessageDialog(deposit,"Deposited Successfully");
                                        con.close();
                                        break;
                                    }
 
                                }
                                while(res.isLast())
                                {
                                    JOptionPane.showMessageDialog(deposit,"Invalid Account ID");
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
                      JOptionPane.showMessageDialog(deposit,"Negative/Zero Amount");  
                }
                        
                        
            }   
            catch(NumberFormatException ea)
            {
                    JOptionPane.showMessageDialog(deposit,"Wrong Account ID");
            }
        }
        else
            {
            JOptionPane.showMessageDialog(deposit,"Empty Field");
            }
        }
    }
     
}