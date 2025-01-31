/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hproject;
import hproject.mySQLQueries;
import hproject.Checking;
import hproject.MyDate;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/**
 *
 * @author GuHeHe
 */
public class Sale extends javax.swing.JDialog {

    /**
     * Creates new form Sale
     */
    mySQLQueries msql = new mySQLQueries();
    MyDate md = new MyDate();
    String strdataitem[]=new String[9];
    String strquery[]=new String[9];
    Vector vid = new Vector();
    Vector vamount = new Vector();
    Long l = 0l ;
    int removerow = 0;
    int rowcount = 0;
    int dataqty = 0;
    DefaultTableModel dtm = new DefaultTableModel();
    String str[],stri[];
    String s;
    int totalamount01 = 0;
    int tempqty = 0;
    String getpid;
    private static String staffid;
    public Sale(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
       // fillItem();
        createtable();
    //    setstaffid();
        AutoID();
       // tblpurchase.setSelectionMode(0);
        lbldate.setText(md.getdate());
    }
    
   
    public void setstaffid()
    {
     //   System.out.println(staffid);
        lblstaffid.setText(staffid);
    }
    
    public void createtable()
    {
        dtm.addColumn("No");
        dtm.addColumn("Item ID");
        dtm.addColumn("Item Detail");
        dtm.addColumn("Price");
        dtm.addColumn("Quantity");
        dtm.addColumn("Amount");
        tblpurchase.setModel(dtm);
        setColumnWidth(0,10);
        setColumnWidth(1,40);
        setColumnWidth(2,200);
        setColumnWidth(3,40);
        setColumnWidth(4,20);
        setColumnWidth(5,50);
    }
    public void setColumnWidth(int index , int width)
    {
        DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblpurchase.getColumnModel();
        TableColumn tc = tcm.getColumn(index);
        tc.setPreferredWidth(width);
    }
    public void AutoID()
    {
        lblpurid.setText((String.valueOf(msql.getAutoid("saleid", "sale", "S-"))));
    }
    
    public String getpromotypename(String tabelid)
    {
        String name[] = new String[7];
        name = msql.getpromodata(tabelid);
        getpid = name[1];
        getpid = msql.getprotypeName(getpid);
        return getpid;
    }
    public void deleteRow()
    {
        int i = tblpurchase.getSelectedRow();
        vamount.remove(i);
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
        lbltotalamount.setText(Checking.Sumamount(vamount, 1)+"Kyats");
        totalamount01=Checking.Sumamount01(vamount);

    }

    public void deleteRow01()
    {
        vamount.remove(removerow);
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
        lbltotalamount.setText(Checking.Sumamount(vamount, 1)+"Kyats");
        totalamount01=Checking.Sumamount01(vamount);

    }
    public void itemaddmethod()
    {
        if(!getpid.equals(null))
        {
            System.out.println("wryyyyyyyyyyyyyyyyyy");
        }
        tempqty+=Integer.parseInt(txtquantity.getText());
        //System.out.println("sum tempqty" +tempqty);
        String sqty=String.valueOf(tempqty);
        strdataitem[0]=String.valueOf(vid.size()+1);  //for number
        vid.addElement(strdataitem[1]);//itemid
       // strdataitem[2]+="|"+strdataitem[6]+"|"+strdataitem[7]+"|"+strdataitem[8]; // 2= name, 6=brand,  7= type , 8=remark 
        strdataitem[3]=lblprice.getText();  // price
        strdataitem[4]=txtquantity.getText(); //Qty
        if(!sqty.equals("0"))
        {            
            strdataitem[4]=sqty;
        }
        Long amount = Long.parseLong(txtquantity.getText())*Long.parseLong(strdataitem[3]);  // $ * Qty
        strdataitem[5]=String.valueOf(amount);  //total
        vamount.addElement(strdataitem[5]);
        dtm.addRow(strdataitem);
        tblpurchase.setModel(dtm);
        txtid.requestFocus();      
        rowcount++;
        //System.out.println(rowcount);
        
    }
    
    public void showitem()
    {
        strquery = msql.getItemData1(txtid.getText());
        strdataitem[1]=strquery[0];//itemid
        strdataitem[2]=strquery[1];//itemname
        strdataitem[3]=msql.getTypeName(strquery[2]);//type name
        lblitemname.setText(strdataitem[2]);
        strdataitem[4]=strquery[3];//sale price
        getpid=strquery[5];
        strdataitem[5]=strquery[6];// qty
        dataqty=Integer.parseInt(strdataitem[5]);
        System.out.println(dataqty);
        System.out.println(getpid);
        getpromotypename(getpid);
//        lblitemtype.setText(strdataitem[3]);
        lblprice.setText(strdataitem[4]);
        txtquantity.requestFocus();
    }
    public void clearItem()
    {
        lblitemname.setText("");
    //    lblitemtype.setText("");
        lblprice.setText("");
        txtquantity.setText("");
        txtid.setText("");
        tempqty=0;
    }
    public void clear()
    {
        lblitemname.setText("");
        lbltotalamount.setText("");
        txtid.setText("");
        while(dtm.getRowCount()>0)
            dtm.removeRow(0);
        tblpurchase.setModel(dtm);
        vid.removeAllElements();
        vamount.removeAllElements();
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
    public boolean duplicate(String id)
    {
        boolean b=loop(id, gettabledata());
        return b;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnadd = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        txtquantity = new javax.swing.JTextField();
        lblitemname = new javax.swing.JLabel();
        lblprice = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnsave = new javax.swing.JButton();
        btnclose = new javax.swing.JButton();
        lbltotalamount = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblpurchase = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblpurid = new javax.swing.JLabel();
        lbldate = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblstaffid = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sale");
        setLocation(new java.awt.Point(600, 250));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Info :"));
        jPanel2.setToolTipText("");

        jLabel8.setText("Item ID :");

        jLabel9.setText("Item Name :");

        jLabel11.setText("Price :");

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

        lblitemname.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblprice.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
                            .addComponent(jLabel9))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblitemname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtid, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtquantity, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addComponent(lblprice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btnadd)
                        .addGap(78, 78, 78)
                        .addComponent(btndelete)
                        .addGap(94, 94, 94)
                        .addComponent(btnupdate)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblprice, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jLabel11)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(lblitemname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtquantity, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnadd)
                    .addComponent(btndelete)
                    .addComponent(btnupdate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel14.setText("Total Amount :");

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

        lbltotalamount.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Sale Form Info:"));

        jLabel1.setText("Staff ID :");

        lblpurid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbldate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel7.setText("Date :");

        jLabel2.setText("Sale ID :");

        lblstaffid.setText(staffid);
        lblstaffid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(66, 66, 66)
                        .addComponent(lblstaffid, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblpurid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblpurid, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblstaffid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbldate, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(328, 328, 328)
                        .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnclose, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(35, 35, 35)
                                .addComponent(lbltotalamount, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbltotalamount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclose, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
        if(Checking.IsNull(txtid.getText()))
        {
            JOptionPane.showMessageDialog(null, "You must choose Item ID!");
            txtid.requestFocus();
            
        }
        else if(!Checking.checktxtquantity(txtquantity.getText()))
        {
            txtquantity.requestFocus();
            txtquantity.selectAll();
        }
        else if(!Checking.checkqtyhigh(dataqty,Integer.parseInt(txtquantity.getText())))
        {
            txtquantity.requestFocus();
            txtquantity.selectAll();
        }
        else if(!Checking.checkqtyhigh(dataqty,Integer.parseInt(txtquantity.getText())))
        {
            txtquantity.requestFocus();
            txtquantity.selectAll();
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
            lbltotalamount.setText(Checking.Sumamount(vamount,1)+"Kyats");
            totalamount01=Checking.Sumamount01(vamount);
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
            lbltotalamount.setText(Checking.Sumamount(vamount, 1)+"Kyats");
            totalamount01=Checking.Sumamount01(vamount);
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
            lblprice.setText(strdataitem[3]);
            deleteRow();
            itemaddmethod();
            lbltotalamount.setText(Checking.Sumamount(vamount,1)+"Kyats");
            totalamount01=Checking.Sumamount01(vamount);
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
            txtquantity.requestFocus();
            //   getRootPane().setDefaultButton(btnupdate);
        }
    }//GEN-LAST:event_txtidKeyPressed

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        if(vid.size()==0) {

            JOptionPane.showMessageDialog(null, "There is no item for Sale!");
        }//end else if
        else {
            if(JOptionPane.showConfirmDialog(null, "Are you sure to Save ?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
            {
                boolean save;
                String[]savedata1=new String[4];
                String []savedata2=new String[4];
                savedata1[0]=lblpurid.getText();//saleid
                savedata1[1]=lblstaffid.getText();//stid
                savedata1[2]=lbldate.getText();//saledate
                savedata1[3]=Integer.toString(totalamount01);
                //System.out.println(savedata1[3]);
                //System.out.println(lbltotalamount.getText());
               // totalamount01= Integer.parseInt(lbltotalamount.getText());
                //System.out.println(totalamount01);
                save=msql.insertData("sale", savedata1);
                if(save) {
                    for(int i=0 ; i<vid.size() ; i++) {
                        savedata2[0]=lblpurid.getText();//saleid
                        savedata2[1]=(String)tblpurchase.getValueAt(i, 1);//itemid
                        savedata2[2]=(String)tblpurchase.getValueAt(i, 3);//price
                        savedata2[3]=(String)tblpurchase.getValueAt(i, 4);//Qty
                        save=msql.insertData("saledetail", savedata2);
                        msql.S_updateitemquantity("itemdetail",strdataitem[1], strdataitem[4]);
                    }
                }

                if(save) {
                    JOptionPane.showMessageDialog(null, " Items sold are not exchangeable . Thank You !");
                    AutoID();
                    clear();
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

    private void tblpurchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpurchaseMouseClicked
        // TODO add your handling code here:
        int r = tblpurchase.getSelectedRow();
        //  System.out.println(r);
        txtid.setText(tblpurchase.getValueAt(r, 1).toString());
        lblitemname.setText(tblpurchase.getValueAt(r, 2).toString());
        lblprice.setText(tblpurchase.getValueAt(r, 3).toString());
        txtquantity.setText(tblpurchase.getValueAt(r, 4).toString());
    }//GEN-LAST:event_tblpurchaseMouseClicked

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
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        staffid=args[0];
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Sale dialog = new Sale(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbldate;
    private javax.swing.JLabel lblitemname;
    private javax.swing.JLabel lblprice;
    private javax.swing.JLabel lblpurid;
    private javax.swing.JLabel lblstaffid;
    private javax.swing.JLabel lbltotalamount;
    private javax.swing.JTable tblpurchase;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtquantity;
    // End of variables declaration//GEN-END:variables
}
