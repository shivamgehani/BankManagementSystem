
package bank_management.operations;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class Service implements ActionListener
{
    JLabel imageicon;
    JButton debit,credit,back;
    JFrame service;
    Service()
    {   
        service = new JFrame("SERVICE");
        
        ImageIcon ic = new ImageIcon("Images/create.jpg");
        imageicon = new JLabel("Logo",ic,JLabel.CENTER);
        imageicon.setBounds(5,0,400,600);
        
        Font ft1 = new Font("Times New Roman",Font.BOLD,14);
        credit = new JButton("CREDIT CARD");
        credit.setBounds(40,200,140,70);
        credit.setBackground(Color.white);
        credit.setOpaque(true);
        credit.setFont(ft1);
        credit.setBorderPainted(true);
        credit.addActionListener((ActionEvent e) -> {
            try{
                service.setVisible(false);
                new Credit();
                }
                catch (Exception ex)
                {
                    ex.getStackTrace();
                }
            
        });

        debit = new JButton("DEBIT CARD");
        debit.setBounds(230,200,140,70);
        debit.setBackground(Color.white);
        debit.setOpaque(true);
        debit.setFont(ft1);
        debit.setBorderPainted(true);
        debit.addActionListener((ActionEvent e) -> {
            try{
            service.setVisible(false);
            new Debit();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });

        back = new JButton("BACK");
        back.setBounds(5,0,80,30);
        back.setBackground(Color.gray);
        back.setOpaque(true);
        back.setBorderPainted(false);
        back.addActionListener(this);

        service.add(debit);
        service.add(credit);
        service.add(back);
        service.add(imageicon);

        service.setSize(400,600);
        service.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        service.setLayout(null);
        service.setVisible(true);
        service.setLocationRelativeTo(null);
        service.setResizable(false);
    }
   
     public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==back)
        {
            service.setVisible(false);
            new Operation1();
        }
    }
    
}
