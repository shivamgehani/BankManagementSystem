
package bank_management.operations;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class Front implements ActionListener
{
    JFrame frame1;
    JLabel imageicon,label12;
    JButton service,register;
  
        
    public Front(){

        frame1 = new JFrame();
        
        ImageIcon ic = new ImageIcon("Images/bank_cover.jpg");
        imageicon = new JLabel(null,ic,JLabel.CENTER);
        imageicon.setBounds(5,0,400,500);
        
        Font ft12=new Font("Times New Roman",Font.BOLD,26);
        label12 = new JLabel("Welcome");
        label12.setBounds(150,85,300,150);
        label12.setFont(ft12);
        label12.setForeground(Color.DARK_GRAY);
       
        service = new JButton("SERVICE");
        service.setBounds(250,380,120,40);
        service.setBackground(Color.white);
        service.setOpaque(true);
        service.setBorderPainted(false);
        service.addActionListener(this);
        
        
        
        
        register = new JButton(" CREATE ");
        register.setBounds(35,380,120,40);
        register.setBackground(Color.white);
        register.setOpaque(true);
        register.setBorderPainted(false);
        register.addActionListener(this);

        frame1.add(label12);
        frame1.add(service);
        frame1.add(register);
        frame1.add(imageicon);
    
        
        
        
        frame1.setSize(400,490);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);
        frame1.setResizable(false);
    }
    
    public void actionPerformed(ActionEvent ae){
            if(ae.getSource()==service)
            { 
                frame1.setVisible(false); 
                new Operation1();
                
            }
           
            if(ae.getSource()==register)
            {
                frame1.setVisible(false);
                new Register();
            }
    }

   
}