/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hproject;
import hproject.mySQLQueries;
import hproject.Checking;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
/**
 *
 * @author GuHeHe
 */
public class Login extends javax.swing.JDialog {

    /**
     * Creates new form Login
     */
    boolean check01=false;
    mySQLQueries msql = new mySQLQueries();
    private String staffid;
    public Login(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getRootPane().setDefaultButton(btnlogin);
    }

    public void clear()
    {
        txtun.setText("");
        txtpassword.setText("");
        txtun.requestFocus();
    }
    
    public void clear01()
    {
        txtpassword.selectAll();
        txtpassword.requestFocus();
    }
    public String checklogin(String un,String p)
    {
        String s = null;
        boolean b=acceptun(un);
        if(b)
        {
            //System.out.println("UserName is correct");
            boolean br=acceptpw(un,p);
            if(br)
            {
                //System.out.println("Password is correct");
                s=msql.getstatus(un, p);
                check01=true;
                
               // System.out.println(msql.getstaffid(un, p));
                return s;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Fail to login");
                //System.out.println("Password is incorrect");
                clear01();
                return s;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Fail to login");
            //System.out.println("UserName is incorrect");
            clear01();
            return s;
        }
                
    }
    
    public void openform(String str)
    {
        JOptionPane.showMessageDialog(null, "Successfully Log In!","Log In.",JOptionPane.INFORMATION_MESSAGE); 
        String info[]=new String[1];
        info[0]=this.staffid;
        if(str.equals("P"))
        { 
            MainMenuP.main(info);
        }
        else if(str.equals("S"))
        {
            MainMenuS.main(info);
        }
        
        else if(str.equals("PS"))
        {
            MainMenuPS.main(info);
        }
        
        
        
        else if(str.equals("PSN"))
        {
            MainMenu.main(info);
        }
    }
    public void close()
    {
        WindowEvent we=new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(we);
        this.dispose();
    }
    public int getnum()
    {
        int row=0;
        row=msql.loginrow();
        return row;
    }
    public boolean acceptun(String str)
    {
        String s[]=new String[getnum()];
        s=msql.allofun(getnum());
        boolean b=loopun(str,s);
        return b;
    }
    public boolean acceptpw(String un, String pw)
    {
        String s=msql.allofp(un);
        boolean b=looppw(pw, s);
        return b;
    }
    
    public boolean loopun(String str01, String []str02)
    {
        boolean b=false;
        for(int i=0;i<getnum();i++)
        {
           // System.out.println(str01+" is enter username");
           // System.out.println("real username is "+str02[i]);
            if(str01.equals(str02[i]))
            {
                //System.out.println("Answer is true");
                return true;
            }
            else
            {
               // System.out.println("Noooooooooooooooooooooooooooooooooooooooo");
            }
        }
        return false; 
    }
    
    public boolean looppw(String str01, String str02)
    {
        boolean b=false;
        //System.out.println(str01+" is enter Password");
        //System.out.println("real Password is "+str02);
        if(str01.equals(str02))
        {
            //System.out.println("Answer is true");
            return true;
        }
        else
        {
            //System.out.println("Noooooooooooooooooooooooooooooooooooooooo");
        }
        return false; 
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtun = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtpassword = new javax.swing.JPasswordField();
        btnlogin = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        btnclose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Log In");
        setLocation(new java.awt.Point(600, 250));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setText("User Name :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Password :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtun)
                    .addComponent(txtpassword, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnlogin.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnlogin.setMnemonic('l');
        btnlogin.setText("Log In");
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });

        btncancel.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btncancel.setMnemonic('a');
        btncancel.setText("Cancel");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });

        btnclose.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnclose.setMnemonic('c');
        btnclose.setText("Close");
        btnclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnlogin)
                        .addGap(59, 59, 59)
                        .addComponent(btncancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(btnclose)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnlogin)
                    .addComponent(btncancel)
                    .addComponent(btnclose))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        // TODO add your handling code here:
        if(Checking.IsNull(txtun.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please enter valid Name.");
            txtun.requestFocus();
            txtun.selectAll();
        }
        else if(Checking.IsNull(txtpassword.getText()))
        {
            JOptionPane.showMessageDialog(null, "Please enter Password.");
            clear01();
        }
        else
        {
            String []str = new String[3];
            String status=null,staffid=null;
            str[0]=txtun.getText();
            str[1]=txtpassword.getText();
            status=checklogin(str[0],str[1]);
            if(check01==true)
            {
              //  System.out.println("HHHHHHHHHHHHHH");
                this.staffid=msql.getstaffid(str[0],str[1]);
                //System.out.println(staffid);
                
                close();
                openform(status);
            }
           // System.out.println(gs.getid());
            
        }
    }//GEN-LAST:event_btnloginActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btncancelActionPerformed

    private void btncloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncloseActionPerformed
        // TODO add your handling code here:
        if((JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?","Confirm exiting.",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE))==JOptionPane.YES_OPTION)
        dispose();
    }//GEN-LAST:event_btncloseActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login dialog = new Login(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btnclose;
    private javax.swing.JButton btnlogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtpassword;
    private javax.swing.JTextField txtun;
    // End of variables declaration//GEN-END:variables
}
