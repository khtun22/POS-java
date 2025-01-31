/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hproject;

import java.awt.event.KeyEvent;
import java.util.Vector;
import hproject.mySQLQueries;
import hproject.Checking;
import static hproject.mySQLQueries.con;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JOptionPane;

/**
 *
 * @author GuHeHe
 */
public class PromoOne extends javax.swing.JDialog {

    /**
     * Creates new form PromoOne
     */
    mySQLQueries msql=new mySQLQueries();
    DefaultTableModel dtm = new DefaultTableModel();
    int dataqty = 0;
    String strdataitem[]=new String[9];
    String strquery[]=new String[4];
    int rowcount = 0;
    int removerow = 0;
    Vector vid = new Vector();
    int tempqty = 0;
    String disp;
    private String str = null;
    String strquery01[];
    public PromoOne(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        createtable();
        filltable();
    }
    public void filltable()
    {
        str="SELECT * FROM temppro";
        System.out.println(str);
        try
        {
            Connection con = DBConnection.GetMySQLConnection();
        }
        catch(ClassNotFoundException cnfe)
        {
            System.out.println(cnfe);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        fillItem(str);
    }
    public void fillItem(String sql)
    {
        String strdataitem[]= new String[5];
        try
        {
            Statement ste = con.createStatement();
            while(dtm.getRowCount()>0)
               dtm.removeRow(0);
            ResultSet rs = ste.executeQuery(sql);
            while(rs.next())
            {
                strdataitem[0]=String.valueOf(vid.size()+1);
                strdataitem[1]=rs.getString(2);//itemid
                strdataitem[2]=rs.getString(1);//itemname
                strdataitem[3]=rs.getString(4);//price
                strdataitem[4]=rs.getString(3);//qty
                dtm.addRow(strdataitem);
            }
            tblpurchase.setModel(dtm);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
        }
        
    }
    public void createtable()
    {
        dtm.addColumn("No");
        dtm.addColumn("Item Code");
        dtm.addColumn("Item Name");
        dtm.addColumn("Discount Price");
        dtm.addColumn("Quantity");
        tblpurchase.setModel(dtm);
        setColumnWidth(0,10);
        setColumnWidth(1,40);
        setColumnWidth(2,200);
        setColumnWidth(3,40);
        setColumnWidth(4,20);
    }
    public void setColumnWidth(int index , int width)
    {
        DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblpurchase.getColumnModel();
        TableColumn tc = tcm.getColumn(index);
        tc.setPreferredWidth(width);
    }
    
    public boolean loop(String str01, String []str02)
    {
       // boolean b=false;
        for(int i=0;i<rowcount;i++)
        {
           // System.out.println(str01+" is enter username");
           // System.out.println("real username is "+str02[i]);
            if(str01.equals(str02[i]))
            {
               // System.out.println("Answer is true");
                removerow=i;
                //System.out.println(removerow +"DDDDDDDDDDDDDDD");
                return true;
            }
            else
            {
               // System.out.println("Noooooooooooooooooooooooooooooooooooooooo");
            }
        }
        return false; 
    }
    
    public void clear()
    {
        txtitemname.setText("");
        txtid.setText("");
        txtquantity.setText("");
        txtdisp.setText("");
        /*while(dtm.getRowCount()>0)
            dtm.removeRow(0);
        tblpurchase.setModel(dtm);
        vid.removeAllElements();*/
    }
    
    public boolean duplicate(String id)
    {
        boolean b=loop(id, gettabledata());
        return b;
    }
    
    public String[] gettabledata()
    {
        String s[] = new String[rowcount];
       // s=tblpurchase.getValueAt(r, 0).toString();
        if(rowcount!=0)
        {
            for(int i=0;i<rowcount;i++)
            {
                s[i]=tblpurchase.getValueAt(i, 1).toString();
               // System.out.println(s.length);
            }
        }
        return s;
    }
    
    
    
    public void deleteRow()
    {
        int i = tblpurchase.getSelectedRow();
       
        int no ;
        if(!vid.lastElement().equals(vid.get(i)))
        {
            vid.remove(i);
            no = vid.indexOf(vid.get(i));
            dtm.removeRow(i);
            dtm.setValueAt(no+1, i, 0);

        }
        else
        {
            vid.remove(i);
            dtm.removeRow(i);

        }
        rowcount--;
        tblpurchase.setModel(dtm);
        

    }

    
    public void deleteRow01()
    {
        int no ;
        tempqty=Integer.parseInt((String)tblpurchase.getValueAt(removerow, 4));
        if(!vid.lastElement().equals(vid.get(removerow)))
        {
            vid.remove(removerow);
            no = vid.indexOf(vid.get(removerow));
            dtm.removeRow(removerow);
            dtm.setValueAt(no+1, removerow, 0);

        }
        else
        {
            vid.remove(removerow);
            dtm.removeRow(removerow);

        }
        rowcount--;
        tblpurchase.setModel(dtm);

    }
    public void checkingtxt()
    {
        if(!Checking.checktxtquantity(txtquantity.getText()))
        {
            txtquantity.requestFocus();
            txtquantity.selectAll();
        }
        else if(!Checking.checkqtyhigh(dataqty,Integer.parseInt(txtquantity.getText())))
        {
            txtquantity.requestFocus();
            txtquantity.selectAll();
            System.out.println(dataqty);
        }
    }
    
    public void itemaddmethod()
    {
        tempqty+=Integer.parseInt(txtquantity.getText());
        String sqty=String.valueOf(tempqty);
        strdataitem[0]=String.valueOf(vid.size()+1);  //for number
        vid.addElement(strdataitem[1]);//itemid
       // strdataitem[2]+="|"+strdataitem[6]+"|"+strdataitem[7]+"|"+strdataitem[8]; // 2= name, 6=brand,  7= type , 8=remark 
        strdataitem[3]=txtdisp.getText();  // price
        
        strdataitem[4]=txtquantity.getText(); //Qty
        if(!sqty.equals("0"))
        {            
            strdataitem[4]=sqty;
        }
       
        
        dtm.addRow(strdataitem);
        tblpurchase.setModel(dtm);
        txtid.requestFocus();      
        dataqty=Integer.parseInt(strdataitem[4]);
        rowcount++;
        //System.out.println(rowcount);
        
    }
    
    public void showitem()
    {
        strquery = msql.getItemData1(txtid.getText());
        strdataitem[1]=strquery[0];//itemid
        strdataitem[2]=strquery[1];//itemname
        strdataitem[3]=msql.getTypeName(strquery[2]);//type name
        txtitemname.setText(strdataitem[2]);
        strdataitem[4]=strquery[3];//sale price
        disp=strdataitem[4];
//        lblitemtype.setText(strdataitem[3]);
        txtdisp.setText(strdataitem[4]);
        txtquantity.requestFocus();
    }
    public void clearItem()
    {
        txtitemname.setText("");
    //    lblitemtype.setText("");
        txtdisp.setText("");
        txtquantity.setText("");
        txtid.setText("");
        tempqty=0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnadd = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        txtquantity = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        txtdisp = new javax.swing.JTextField();
        txtitemname = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblpurchase = new javax.swing.JTable();
        btnclose = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Promotion Item Entry");
        setLocation(new java.awt.Point(600, 250));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Info :"));
        jPanel2.setToolTipText("");

        jLabel8.setText("Item Code :");

        jLabel10.setText("Item Name :");

        jLabel11.setText("Discount Price :");

        jLabel12.setText("Quantity :");

        btnadd.setMnemonic('a');
        btnadd.setText("Add");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btndelete.setMnemonic('d');
        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        btnupdate.setMnemonic('u');
        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        txtid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtid)
                            .addComponent(txtitemname, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtquantity)
                            .addComponent(txtdisp)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btnadd)
                        .addGap(78, 78, 78)
                        .addComponent(btndelete)
                        .addGap(94, 94, 94)
                        .addComponent(btnupdate)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtitemname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel12))
                    .addComponent(txtquantity, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd)
                    .addComponent(btndelete)
                    .addComponent(btnupdate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblpurchase.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblpurchase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpurchaseMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblpurchase);

        btnclose.setMnemonic('c');
        btnclose.setText("Close");
        btnclose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncloseActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE)
                .addComponent(btnclose, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclose, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
        if(Checking.IsNull(txtid.getText()))
        {
            JOptionPane.showMessageDialog(null, "You must choose Item ID!");
            txtid.requestFocus();
            if(!Checking.checkprice(Integer.parseInt(txtdisp.getText()), disp, "promotion"))
            {
                JOptionPane.showMessageDialog(null, "Your price must be lower!");
                txtid.requestFocus();
            }
        }
        
        
        else
        {
            boolean tf=duplicate(txtid.getText());
            // System.out.println(tf);
            if(tf)
            {
                deleteRow01();
            }
            itemaddmethod();
            // System.out.println(tf);


            clearItem();
            //txtid.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        // TODO add your handling code here:
        if(tblpurchase.getSelectedRow()<0)
        JOptionPane.showMessageDialog(this, "Please select row to delete.");
        else
        {
            deleteRow();
            clearItem();
            //txtid.setSelectedIndex(0);

        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        if(tblpurchase.getSelectedRow()<0)
        {
            JOptionPane.showMessageDialog(this, "Please select row to update");
        }

        else if(!Checking.checktxtquantity(txtquantity.getText()))
        {
            txtquantity.requestFocus();
            txtquantity.selectAll();
        }
        else
        {
            deleteRow();
            itemaddmethod();
            clearItem();
            //  txtid.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnupdateActionPerformed

    private void txtidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            // System.out.println("Right key typed");
            showitem();
            txtquantity.setText("1");
            txtdisp.requestFocus();
            //   getRootPane().setDefaultButton(btnupdate);
        }
    }//GEN-LAST:event_txtidKeyPressed

    private void tblpurchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpurchaseMouseClicked
        // TODO add your handling code here:
        int r = tblpurchase.getSelectedRow();
        //  System.out.println(r);
        txtid.setText(tblpurchase.getValueAt(r, 1).toString());
        txtitemname.setText(tblpurchase.getValueAt(r, 2).toString());
        txtdisp.setText(tblpurchase.getValueAt(r, 3).toString());
        txtquantity.setText(tblpurchase.getValueAt(r, 4).toString());
        txtdisp.requestFocus();
    }//GEN-LAST:event_tblpurchaseMouseClicked

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        if(vid.size()==0)
        {

            JOptionPane.showMessageDialog(null, "There is no item for Promotion!");
            txtid.requestFocus();
        }
        else
        {
            if(JOptionPane.showConfirmDialog(null, "Are you sure to Save ?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
            {
                boolean save = false,save01 = false, save02 = false;
                int vidsize=0;
                String []savedata2=new String[4];
                vidsize=vid.size();

                for(int i=0 ; i<vidsize ; i++) {
                        
                        savedata2[0]=(String)tblpurchase.getValueAt(i, 1);//itemid
                        savedata2[1]=(String)tblpurchase.getValueAt(i, 2);//name
                        savedata2[2]=(String)tblpurchase.getValueAt(i, 3);//price
                        savedata2[3]=(String)tblpurchase.getValueAt(i, 4);//Qty
                        save=msql.insertData("temppro01", savedata2);
                    }

                if(save) {
                    JOptionPane.showMessageDialog(null, " Items Saved!");
                    clear();
                    dispose();
                } else {
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
            java.util.logging.Logger.getLogger(PromoOne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PromoOne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PromoOne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PromoOne.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PromoOne dialog = new PromoOne(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnclose;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblpurchase;
    private javax.swing.JTextField txtdisp;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtitemname;
    private javax.swing.JTextField txtquantity;
    // End of variables declaration//GEN-END:variables
}
