
package bank_management.operations;
import bank_management.operations.Operation1;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
public class Display implements ActionListener
{
    JList list1;
    DefaultListModel dl,dl1;
    int x1;
    String url = "jdbc:ucanaccess://bank.accdb";
    JFrame display;
    JLabel imageicon;
    JButton back,customer,deleted,bankinfo;
    
   public Display()
    {
        list1 = new JList();
        list1.setBounds(90,330,220,250);
        
        
        
        display = new JFrame("Display Info");
        
        ImageIcon ic = new ImageIcon("Images/create.jpg");
        imageicon = new JLabel("Logo",ic,JLabel.CENTER);
        imageicon.setBounds(5,0,400,600);

        back = new JButton("BACK");
        back.setBounds(5,0,80,30);
        back.setBackground(Color.gray);
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.addActionListener(this);

        Font ft1 = new Font("Times New Roman",Font.BOLD,14);
        customer = new JButton("Customer Info");
        customer.setBounds(40,100,140,70);
        customer.setBackground(Color.white);
        customer.setFont(ft1);
        customer.setOpaque(true);
        customer.setBorderPainted(true);
        customer.addActionListener((ActionEvent e) -> {
       
        String value1= JOptionPane.showInputDialog(null,"Enter Account ID ");
            
            try
                {
                  x1 = Integer.parseInt(value1);
                   DisplayCustomer();
                }
                catch(NumberFormatException e2)
                {
                    JOptionPane.showMessageDialog(display,"Wrong ID");
                }
                catch(NullPointerException ee)
                {
                    JOptionPane.showMessageDialog(imageicon, "Field Is Empty");
                    
                }
          
        });

        deleted = new JButton("Deleted info");
        deleted.setBounds(230,100,140,70);
        deleted.setBackground(Color.white);
        deleted.setFont(ft1);
        deleted.setOpaque(true);
        deleted.setBorderPainted(true);
        deleted.addActionListener((ActionEvent e) -> {
            String value = JOptionPane.showInputDialog(null,"Enter Account ID "); 
            try
            {
                int x = Integer.parseInt(value);
                try
                {
                    Connection con;
                    Statement st;
                    ResultSet rs1;

                    con = DriverManager.getConnection(url);
                    st=con.createStatement();
                    rs1=st.executeQuery("Select * from deleted_info where d_id = '"+x+"'");
                    while(rs1.next())
                    {

                       String id1=rs1.getString("d_id");
                       String name1=rs1.getString("d_name");
                       String cnic1=rs1.getString("d_cnic");
                       String mphone1=rs1.getString("d_mobile");
                       String gender1=rs1.getString("d_gender");
                       
                        dl1 = new DefaultListModel();
                        dl1.addElement("\n*******Display Detail of Deleted*******\n ");
                        dl1.addElement("Account ID: "+id1+"\n");
                        dl1.addElement("Name: "+name1+"\n");
                        dl1.addElement("Cnic No: "+cnic1+"\n");
                        dl1.addElement("Phone No: "+mphone1+"\n");
                        dl1.addElement("Gender: "+gender1+"\n");
                        
                        list1.setModel(dl1);


                    }

                }
                catch(SQLException ae)
                {
                    System.out.println("Exception SQl");
                    ae.printStackTrace();
                }
                catch(Exception ew)
                {
                    System.out.println("Exception");
                    ew.printStackTrace();
                }
                }
                    catch(NumberFormatException e1)
                    {
                        JOptionPane.showMessageDialog(null,"Wrong ID");
                    }
        
                });

        bankinfo = new JButton("BANK info");
        bankinfo.setBounds(130,230,140,70);
        bankinfo.setBackground(Color.white);
        bankinfo.setOpaque(true);
        bankinfo.setFont(ft1);
        bankinfo.setBorderPainted(true);
        bankinfo.addActionListener((ActionEvent e) -> {
            
            try
                {
                    Connection con;
                    Statement st1;
                    ResultSet rs1;

                    con = DriverManager.getConnection(url);
                    st1=con.createStatement();
                    rs1=st1.executeQuery("Select * from bank_info");
                    while(rs1.next())
                    {

                       String id1=rs1.getString("b_branch");
                       String name1=rs1.getString("b_name");
                       String city=rs1.getString("b_city");
                       String balance=rs1.getString("b_balance");
                       
                        dl = new DefaultListModel();
                        dl.addElement("\n*******Displaying Detail of Bank*******\n ");
                        dl.addElement("Branch Code: "+id1+"\n");
                        dl.addElement("Bank Name: "+name1+"\n");
                        dl.addElement("City Name: "+city+"\n");
                        dl.addElement("balance: "+balance+"\n");
                        
                        
                        list1.setModel(dl);


                    }

                }   
                catch(SQLException ae)
                {
                    System.out.println("Exception SQl");
                    ae.printStackTrace();
                }
                catch(Exception ew)
                {
                    System.out.println("Exception");
                    ew.printStackTrace();
                }
                    
        });
            
        
        
        
        display.add(list1);
        display.add(bankinfo);
        display.add(customer);
        display.add(deleted);
        display.add(back);
        display.add(imageicon);

        display.setSize(400,600);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.setLayout(null);
        display.setVisible(true);
        display.setLocationRelativeTo(null);
        display.setResizable(false);
    }
    void DisplayCustomer()
    {
        
        try
        {
            Connection con;
            Statement st;
            ResultSet rs;
           
            con = DriverManager.getConnection(url);
            st=con.createStatement();
            rs=st.executeQuery("Select * from customer_info where c_accid = '"+x1+"'");
            while(rs.next())
            {
               String id=rs.getString("c_accid");
               String name=rs.getString("c_name");
               String cnic=rs.getString("c_cnic");
               String mphone=rs.getString("c_phone");
               String gender=rs.getString("c_gender");
               int balance=rs.getInt("c_balance");
                dl = new DefaultListModel();
                dl.addElement("\n*******Displaying Detail of Customer*******\n ");
                dl.addElement("Account ID: "+id+"\n");
                dl.addElement("Name: "+name+"\n");
                dl.addElement("Cnic No: "+cnic+"\n");
                dl.addElement("Phone No: "+mphone+"\n");
                dl.addElement("Gender: "+gender+"\n");
                dl.addElement("Balance: "+balance+"");
                list1.setModel(dl);
         
            
            }
            
	}
        catch(SQLException ae)
        {
            System.out.println("Exception SQl");
            ae.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("Exception");
            e.printStackTrace();
        }
    }
     public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==back)
        {
            display.setVisible(false);
            new Operation1();
        }
    }
   

}