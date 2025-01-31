/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hproject;
import hproject.mySQLQueries;
import hproject.Checking;
import static hproject.mySQLQueries.con;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author GuHeHe
 */
public class PromoEntry extends javax.swing.JDialog {

    /**
     * Creates new form Promo
     */
    mySQLQueries msql=new mySQLQueries();
    MyDate md = new MyDate();
    String strdataitem[]= new String[7];
    String strdata[]= new String[7];
    String prid;
    public PromoEntry(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        fillprotypeID();
        clear();
        AutoID();
        showdate();
        cleartemp();
        
        prid=lblpid.getText();
        
        
        getRootPane().setDefaultButton(btnsave);
    }

    public void cleartemp()
    {
        msql.deletetable("temppro");
    }
    public void showdate()
    {
        txtstartdate.setText(md.getdate().substring(0,10));
        lblregdate.setText(md.getdate().substring(0,10));
        txtenddate.setText(md.getdate().substring(0,10));
    }
    
    public void AutoID()
    {
        lblpid.setText((String.valueOf(msql.getAutoid("proid","promotion","PO-"))));
    }
    
    public void clear()
    {
        txtstartdate.setText("");
        txtenddate.setText("");
        txtpname.setText("");
        cboptid.setSelectedIndex(0);
    }
    
    public void insertItem(String sql)
    {
        
        try
        {
            Statement ste = con.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next())
            {
                strdataitem[0]=rs.getString(1);//itemname
              //  strquery=msql.getMerchandise(rs.getString(2));//merid
                strdataitem[1]=rs.getString(2);//itemid
            //    System.out.println(rs.getString(3));
                strdataitem[2]=rs.getString(3);//itemqty
                strdataitem[3]=rs.getString(4);//dispri
                strdataitem[4]=rs.getString(5);//proitemid
                strdataitem[5]=rs.getString(6);//proitemname
                strdataitem[6]=rs.getString(7);//gitemqty
                checktype();
            }
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
        
    }
    public boolean checktype()
    {
        boolean f=false;
        if(cboptid.getSelectedItem().toString().equals("One Item Discount") || cboptid.getSelectedItem().toString().equals("Many Item in One Discount"))
        {
            strdata[0]=strdataitem[1];// item id, promotion id , qty , price
            strdata[1]=prid;
            strdata[2]=strdataitem[2];
            strdata[3]=strdataitem[3];
            f=msql.insertData("promotiondetail01",strdata);
            f=msql.updateorderRecord("itempromotion", prid, strdata[0]);
            return f;
        }
        else if(cboptid.getSelectedItem().toString().equals("Gife Discount"))
        {
            strdata[0]=strdataitem[1];
            strdata[1]=prid;
            strdata[2]=strdataitem[2];
            strdata[3]=strdataitem[4];
            strdata[4]=strdataitem[6];
            return msql.insertData("promotiondetail02", strdata);
        }
        return false;
    }
    
    public void fillprotypeID()
    {
        String str[]=msql.getIDForChoice("promotiontype");
        cboptid.addItem("-Selected-");
        for(int i=0 ; i<str.length ; i++)
        cboptid.addItem(str[i].toString());
    }
    
    public boolean insertpromo()
    {
        String[]savedata1=new String[6];
        String protid=msql.getproTypeid((String)cboptid.getSelectedItem());
        savedata1[0]=lblpid.getText();
        savedata1[1]=protid;
        savedata1[2]=txtpname.getText();
        savedata1[3]=lblregdate.getText();
        savedata1[4]=txtstartdate.getText();
        savedata1[5]=txtenddate.getText();
        return msql.insertData("promotion", savedata1);
    }
    
    
    public void openpromotype()
    {
        
        if(cboptid.getSelectedIndex()!=0)
        {
            if(cboptid.getSelectedItem().toString().equals("One Item Discount"))
            {
                PromoOne po = new PromoOne(null,true);
                po.show();
            }
            else if(cboptid.getSelectedItem().toString().equals("Gift Discount"))
            {
                PromoGift pg = new PromoGift(null,true);
                pg.show();
            }
            else if(cboptid.getSelectedItem().toString().equals("Many Item in One Discount"))
            {
                PromoMany pm = new PromoMany(null,true);
                pm.show();
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblrdate = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblpid = new javax.swing.JLabel();
        txtstartdate = new javax.swing.JTextField();
        txtenddate = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cboptid = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtpname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblregdate = new javax.swing.JLabel();
        btncancel = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        btnclose = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Promotion Entry");
        setLocation(new java.awt.Point(600, 250));

        lblrdate.setBorder(javax.swing.BorderFactory.createTitledBorder("Promotion Info :"));

        jLabel1.setText("Promotion ID :");

        txtstartdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstartdateActionPerformed(evt);
            }
        });

        jLabel4.setText("Promotion Type :");

        cboptid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboptidActionPerformed(evt);
            }
        });

        jLabel2.setText("Start Date :");

        jLabel9.setText("End Date :");

        jLabel6.setText("Promotion Name:");

        jLabel7.setText("Registration Date: ");

        lblregdate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout lblrdateLayout = new javax.swing.GroupLayout(lblrdate);
        lblrdate.setLayout(lblrdateLayout);
        lblrdateLayout.setHorizontalGroup(
            lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblrdateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblpid, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(cboptid, javax.swing.GroupLayout.Alignment.LEADING, 0, 173, Short.MAX_VALUE)
                    .addComponent(txtstartdate))
                .addGap(52, 52, 52)
                .addGroup(lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtenddate, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(txtpname)
                    .addComponent(lblregdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        lblrdateLayout.setVerticalGroup(
            lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblrdateLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(lblrdateLayout.createSequentialGroup()
                        .addGroup(lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lblpid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(txtpname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(lblrdateLayout.createSequentialGroup()
                                .addGroup(lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(lblregdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtenddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(1, 1, 1))
                            .addGroup(lblrdateLayout.createSequentialGroup()
                                .addGroup(lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(lblrdateLayout.createSequentialGroup()
                                        .addGroup(lblrdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cboptid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addGap(36, 36, 36))
                                    .addComponent(txtstartdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(lblrdateLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9)))
                .addContainerGap())
        );

        btncancel.setMnemonic('a');
        btncancel.setText("Cancel");
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });

        btnsave.setMnemonic('s');
        btnsave.setText("Save");
        btnsave.setBorder(null);
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnclose.setMnemonic('c');
        btnclose.setText("Close");
        btnclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncloseActionPerformed(evt);
            }
        });

        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblrdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90)
                        .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btncancel)
                        .addGap(145, 145, 145)
                        .addComponent(btnclose, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblrdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclose, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancel)
                    .addComponent(btnedit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboptidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboptidActionPerformed
        // TODO add your handling code here:
        openpromotype();
        
    }//GEN-LAST:event_cboptidActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btncancelActionPerformed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        String bf=null;
        //bf=msql.checktabledata("temppro");
        if(txtpname.getText().equals(null))
        {
            JOptionPane.showMessageDialog(null, "You must enter promotion name!");
            txtpname.requestFocus();
        }
        else if(txtstartdate.getText().equals(null))
        {
            JOptionPane.showMessageDialog(null, "You must enter startdate!");
            txtstartdate.requestFocus();
        }
        else if(txtenddate.getText().equals(null))
        {
            JOptionPane.showMessageDialog(null, "You must enter enddate!");
            txtenddate.requestFocus();
        }
        else if(cboptid.getSelectedIndex()==0)
        {
            JOptionPane.showMessageDialog(null, "You must select Promotion Type!");
            cboptid.requestFocus();
        }

        else
        {
            if(JOptionPane.showConfirmDialog(null, "Are you sure to Save ?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
            {
                boolean save,save01 = false, save02 = false;
                String itemid = null;
                int vidsize=0;
                save=insertpromo();
                
                
                if(save)
                {
                    insertItem("select * from temppro");
                    
                    
                    AutoID();
                    clear();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Records cannot be saved because of some ERROR!");
                    clear();
                }
            }
        }
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btncloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncloseActionPerformed
        // TODO add your handling code here:
        if((JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?","Confirm exiting.",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE))==JOptionPane.YES_OPTION)
        dispose();
    }//GEN-LAST:event_btncloseActionPerformed

    private void txtstartdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstartdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstartdateActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        openpromotype();
    }//GEN-LAST:event_btneditActionPerformed

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
            java.util.logging.Logger.getLogger(PromoEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PromoEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PromoEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PromoEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PromoEntry dialog = new PromoEntry(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnsave;
    private javax.swing.JComboBox<String> cboptid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblpid;
    private javax.swing.JPanel lblrdate;
    private javax.swing.JLabel lblregdate;
    private javax.swing.JTextField txtenddate;
    private javax.swing.JTextField txtpname;
    private javax.swing.JTextField txtstartdate;
    // End of variables declaration//GEN-END:variables
}
