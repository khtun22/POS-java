
package hproject;

import hproject.DBConnection;
import java.sql.*;
import java.sql.Connection;
import javax.swing.*;
import javax.swing.JOptionPane;
public class mySQLQueries {
    static Connection con = null;
    static Statement stmt;
    static String query , query1;
    ResultSet rs;
    DBConnection connect = new DBConnection();
    public mySQLQueries()
    {
        try{
            con=connect.GetMySQLConnection();
        }catch(ClassNotFoundException cnfe)
        {
            System.out.println(cnfe);
        }catch(SQLException sqle)
        {
            System.out.println(sqle);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static boolean insertData(String tbName , String[]data)
    {
        if(tbName.equals("brand"))
        {
            query = "insert into brand (brandid,brandname) values ('"+data[0]+"','"+data[1]+"')";
        }
        else if(tbName.equals("type"))
        {
            query = "insert into type (typeid,typename) values ('"+data[0]+"','"+data[1]+"')";
        }
        else if(tbName.equals("merchandise"))
        {
            query="insert into merchandise (merid,brandid,typeid) values('"+data[0]+"','"+data[1]+"','"+data[2]+"')";
        }
        else if(tbName.equals("itemdetail"))
        {
            query = "insert into itemdetail(itemid,itemname,brandid,typeid,remark)values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"')";
        }
        else if(tbName.equals("supplier"))
        {
            query = "insert into supplier(supplierID,Name,Address,PhoneNo,Email)values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"')";
        }
        else if(tbName.equals("customer"))
        {
            query="insert into customer(customerid,name,address,phoneno,email) values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"')";
        }
        else if(tbName.equals("purchasedetail"))
        {
            query = "insert into purchasedetail(purchaseID,purchasePrice,purchaseQuantity,itemID)values('"+data[0]+"','"+Long.parseLong(data[2])+"','"+Integer.parseInt(data[3])+"','"+data[1]+"')";
        }
        else if(tbName.equals("purchase"))
        {
            int cat = data[2].indexOf("(");
            query = "insert into purchase(purchaseID,supplierID,purchaseDate,staffid,totalamount)values ('"+data[0]+"','"+data[1]+"','"+data[2].substring(0,cat)+"','"+data[3]+"','"+Integer.parseInt(data[4])+"')";
        }
        else if(tbName.equals("purchasedetail01"))
        {
            int cat = data[1].indexOf("(");
            query = "insert into fitemdetail(supplierid,date,itemid,purchaseprice,purchaseqty)values('"+data[0]+"','"+data[1].substring(0,cat)+"','"+data[2]+"','"+Long.parseLong(data[3])+"','"+Integer.parseInt(data[4])+"')";
        }
        else if(tbName.equals("saledetail"))
        {
            query = "insert into saledetail(saleID,salePrice,saleQuantity,itemID)values('"+data[0]+"','"+Long.parseLong(data[2])+"','"+Integer.parseInt(data[3])+"','"+data[1]+"')";
        }
        else if(tbName.equals("sale"))
        {
            int cat = data[2].indexOf("(");
            query = "insert into sale(saleID,staffid,saleDate,totalamount)values ('"+data[0]+"','"+data[1]+"','"+data[2].substring(0,cat)+"','"+Integer.parseInt(data[3])+"')";
        }
        else if(tbName.equals("orderdetail"))
        {
            query = "insert into orderdetail(orderid,itemid,deposit,orderQty,price)values('"+data[0]+"','"+data[1]+"','"+Long.parseLong(data[2])+"','"+Integer.parseInt(data[3])+"','"+Long.parseLong(data[4])+"')";
        }
        else if(tbName.equals("deliver"))
        {
            int cat = data[2].indexOf("(");
            query = "insert into deliver (deliverid,orderid,date) values ('"+data[0]+"','"+data[1]+"','"+data[2].substring(0,cat)+"')";
        }

        else if(tbName.equals("orders"))
        {
            int cat = data[2].indexOf("(");
            query = "insert into orders(orderid,customerid,orderdate,staffid,checkpaid,totalamount)values ('"+data[0]+"','"+data[1]+"','"+data[2].substring(0,cat)+"','"+data[3]+"','"+data[4]+"','"+Integer.parseInt(data[5])+"')";
        }
        else if(tbName.equals("staff"))
        {
            query = "insert into staff(staffid,name,address,phoneno,nrc,username,password,status)values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"','"+data[6]+"','"+data[7]+"')";
        }
        else if(tbName.equals("promotiontype"))
        {
            query = "insert into promotiontype(ptid,ptname)values('"+data[0]+"','"+data[1]+"')";
        }
        else if(tbName.equals("promotion"))
        {
            query = "insert into promotion(proid,ptid, pname, regdate, startdate, enddate  )values('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"')";
        }
        else if(tbName.equals("promotiondetail01"))
        {
            query = "insert into promotiondetail(itemid,proid,itemqty, dispri  )values('"+data[0]+"','"+data[1]+"','"+Integer.parseInt(data[2])+"','"+Integer.parseInt(data[3])+"')";
        }
        else if(tbName.equals("promotiondetail02"))
        {
            query = "insert into promotiondetail(itemid,proid,itemqty,  proitemid, proitemqty  )values('"+data[0]+"','"+data[1]+"','"+Integer.parseInt(data[2])+"','"+data[3]+"','"+Integer.parseInt(data[4])+"')";
        }
        else if(tbName.equals("temppro01"))
        {
            query = "insert into temppro(itemid,itemname,dispri, itemqty )values('"+data[0]+"','"+data[1]+"','"+Integer.parseInt(data[2])+"','"+Integer.parseInt(data[3])+"')";
        }
        else if(tbName.equals("temppro02"))
        {
            query = "insert into temppro(itemid,itemname, itemqty, proitemid, gitemname, gitemqty  )values('"+data[0]+"','"+data[1]+"','"+Integer.parseInt(data[2])+"','"+data[3]+"','"+data[4]+"','"+Integer.parseInt(data[5])+"')";
        }
        else if(tbName.equals("damageitem"))
        {
            query = "insert into damageitem(damageid,itemid,quantity, status,date )values('"+data[0]+"','"+data[1]+"','"+Integer.parseInt(data[2])+"','"+data[3]+"','"+data[4]+"')";
        }
        
        
        try{
            stmt = con.createStatement();
            System.out.println(query);
            if(stmt.executeUpdate(query)==1)
            {
                return true;
            }
            else return false;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public static boolean inserttable(String tbName)
    {
        if(tbName.equals("promotiondetail01"))
        {
            query = "insert into promotiondetail(itemid, itemqty, dispri ) select itemid, itemqty, dispri from temppro";
        }
        try{
            stmt = con.createStatement();
            System.out.println(query);
            if(stmt.executeUpdate(query)==1)
            {
                return true;
            }
            else return false;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public void S_updateitemquantity ( String tbname ,String id ,String data )
    {
        int r1=0;
        mySQLQueries msql = new mySQLQueries();
        String q = msql.getItemData(id)[6];//qty(now)
        System.out.println("curQuantity="+q);
        System.out.println("Sale Qty =" + data );
         if(tbname.equals("itemdetail"))
        {

            r1=Integer.parseInt(q)-Integer.parseInt(data);
           System.out.println(r1);

        }

        query1 = "update itemdetail set totalQty="+r1+" where itemID='"+id+"'";
        try{
            stmt = con.createStatement();
            if(stmt.executeUpdate(query1)==1)
            {
                System.out.println("One Record is Updated Successfully");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Update Not Successful.", "Update Fail", JOptionPane.ERROR_MESSAGE);
            }
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException",JOptionPane.ERROR_MESSAGE);
        }

    }
    
    
    public String[]getIDForOrder(String tbName)
    {
      try{
            if(tbName.equals("itemdetail"))
                rs = connect.SQLSelect("itemID","itemdetail where totalQty>0");
            else if(tbName.equals("orderdetail"))
                rs = connect.SQLSelect("distinct orderid","orderdetail");

            int rowcount = 0 ;
            while(rs.next())
            {
                rowcount++;
            }
            String temp[]=new String[rowcount];
            rs.beforeFirst();
            int i = 0 ;
            while(rs.next())
            {
                temp[i]=rs.getString(1);
                i++;
            }
            return temp;
        }catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null ;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public int loginrow()
    {
        int rowcount=0;
        try
        {
            stmt = con.createStatement();
            rs=connect.SQLSelect("username", "staff");
            while(rs.next())
            {
                rowcount++;
            }
            return rowcount;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return 0;
        }
    }
    public int itemrow(String id)
    {
        int rowcount=0;
        try
        {
            stmt = con.createStatement();
            rs=connect.SQLSelect("supplierid", "fitemdetail where itemid='"+id+"'");
            while(rs.next())
            {
                rowcount++;
            }
            return rowcount;
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return 0;
        }
    }
    public String[] allofun(int i)
    {
        try{
            String username[];
            stmt = con.createStatement();
            query = "select username from staff;";
            rs=stmt.executeQuery(query);
            username=new String[i];
            int j=0;
            while(rs.next())
            {
                username[j]=rs.getString(1);
                j++;
            }
            return username;
        }catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
    }
    public String allofp(String p)
    {
        try{
            String password;
            stmt = con.createStatement();
            query = "select password from staff where username='"+p+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            password=rs.getString(1);
            return password;
        }catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
    }
    
    
    
    public String getstatus(String un, String pw)
    {
        try{
            String status;
            stmt = con.createStatement();
            query = "select status from staff where username='"+un+"' and password='"+pw+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            status=rs.getString(1);
            return status;
        }catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
    }
    public String getstaffid(String un, String pw)
    {
        try{
            String status;
            stmt = con.createStatement();
            query = "select staffid from staff where username='"+un+"' and password='"+pw+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            status=rs.getString(1);
            return status;
        }catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
    }
    public String []getCustomerData(String id)
    {
        try
        {
            stmt = con.createStatement();
            String str[];
            query = "select * from customer where customerID='"+id+"'";
            str = new String[4];
            rs = stmt.executeQuery(query);
            if(rs.next())
            {
                for(int i = 0 ; i<str.length ; i++)
                {
                    str[i]=rs.getString(i+2);
                }
            }
            return str;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }
    
    public String []getCustomerData01(String name)
    {
        try
        {
            stmt = con.createStatement();
            String str[];
            query = "select * from customer where name='"+name+"'";
            str = new String[5];
            rs = stmt.executeQuery(query);
            if(rs.next())
            {
                for(int i = 0 ; i<str.length ; i++)
                {
                    str[i]=rs.getString(i+1);
                }
            }
            return str;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }
    
    public String []getstaffdata(String id)
    {
        try
        {
            stmt = con.createStatement();
            String str[];
            query = "select * from staff where staffid='"+id+"'";
            str = new String[7];
            rs = stmt.executeQuery(query);
            if(rs.next())
            {
                for(int i = 0 ; i<str.length ; i++)
                {
                    str[i]=rs.getString(i+2);
                }
            }
            return str;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }   
    
    public String checktabledata(String tbName)
    {
        if(tbName.equals("temppro"))
        {
            tbName = "select * from temppro where itemid is not null";
        }
        System.out.println(tbName);
        return tbName;
    }
    public boolean isduplicate(String tbName , String []data)
    {
        if(tbName.equals("brand"))
        {
            query = "select * from brand where brandname='"+data[0]+"'";
        }
        else if(tbName.equals("type"))
        {
            query = "select * from type where typename='"+data[0]+"'";
        }
        else if(tbName.equals("merchandise"))
        {
            query="select * from merchandise where brandid='"+data[0]+"' and typeid='"+data[1]+"'";
        }
        else if(tbName.equals("supplier"))
        {
            query = "select * from supplier where Name ='"+data[0]+"'and Address ='"+data[1]+"'and PhoneNo ='"+data[2]+"'and Email='"+data[3]+"'";
        }
        else if(tbName.equals("customer"))
        {
            query = "select * from customer where name ='"+data[0]+"'and Address ='"+data[1]+"'and PhoneNo ='"+data[2]+"'and Email='"+data[3]+"'";
        }
        else if(tbName.equals("item"))
        {
            query = "select * from item where merid ='"+data[1]+"'and itemname ='"+data[2]+"'";
        }
        else if(tbName.equals("promotiontype"))
        {
            query = "select * from promotiontype where ptname='"+data[0]+"'";
        }

        System.out.println(query);
        try{
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            if(rs.next())
                return false;
            else
                return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public String[] getItemData(String id)
    {
        try{
            stmt = con.createStatement();
            String str[]= new String[8];
            query = "select * from itemdetail where itemID='"+id+"'";
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                str[0]=rs.getString(1);//id
                str[1]=rs.getString(2);//name
                str[2]=rs.getString(3);//bid
                str[3]=rs.getString(4);//tid
                str[4]=rs.getString(5);//cursale
                str[5]=rs.getString(6);//remark
                str[6]=rs.getString(7);
                str[7]=rs.getString(8); //pur price
            }
            return str;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    public String[] getItemData1(String id)
    {
        try{
            stmt = con.createStatement();
            String str[]= new String[7];
            query = "select * from itemdetail where itemID='"+id+"'";
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                str[0]=rs.getString(1);//id
                str[1]=rs.getString(2);//name
              //  str[2]=rs.getString(3);//bid
                str[2]=rs.getString(4);//tid
                str[3]=rs.getString(5);// cursale
                str[4]=rs.getString(8);//pursale
                if(!rs.getString(9).equals(null))
                {
                    str[5]=rs.getString(9); // pid
                }
                str[6]=rs.getString(7); //qtyy
            }
            return str;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public String[] getTempData(String id)
    {
        try{
            stmt = con.createStatement();
            String str[]= new String[7];
            query = "select * from temppro where itemid='"+id+"'";
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                str[0]=rs.getString(1);//itemname
                str[1]=rs.getString(2);//itemid
                str[2]=rs.getString(3);//itemqty
                str[3]=rs.getString(4);//dispri
                str[4]=rs.getString(5);//proitemid
                str[5]=rs.getString(6);//giftitemname
                str[6]=rs.getString(7);//giftitemqty
            }
            return str;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    public String[] getpromodata(String id)
    {
        try{
            stmt = con.createStatement();
            String str[]= new String[7];
            query = "select * from promotion where proid='"+id+"'";
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                str[0]=rs.getString(1);//proid
                str[1]=rs.getString(2);//ptid
                str[2]=rs.getString(3);//startdate
                str[3]=rs.getString(4);//enddate
                str[4]=rs.getString(5);//pname
                str[5]=rs.getString(6);//regdate
            }
            return str;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    public String getBrandName(String brandid)
    {
        try{
            String brandname;
            stmt = con.createStatement();
            query = "select * from brand where brandid='"+brandid+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            brandname=rs.getString(2);
            return brandname;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public String getprotypeName(String ptid)
    {
        try{
            String typename;
            stmt = con.createStatement();
            query = "select * from promotiontype where ptid='"+ptid+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            typename=rs.getString(2);
            return typename;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    
    
    public String[]getNameForChoice(String tbName)
    {
        try{
            if(tbName.equals("type"))
                rs=connect.SQLSelect("typeName", "type");
            else if(tbName.equals("brand"))
                rs=connect.SQLSelect("brandname", "brand");
            else if(tbName.equals("customer"))
                rs = connect.SQLSelect("name","customer");
             else if(tbName.equals("customeraddress"))
                rs = connect.SQLSelect("distinct address","customer");
              else if(tbName.equals("supplieraddress"))
                rs = connect.SQLSelect("distinct address","supplier");
            else if(tbName.equals("supplier"))
                rs = connect.SQLSelect("name","supplier");
            else if(tbName.equals("staffun"))
                rs = connect.SQLSelect("username","staff");
            int rowcount = 0 ;
            while(rs.next())
            {
                rowcount++;
            }
            String []temp = new String[rowcount];
            rs.beforeFirst();
            int i = 0 ;
            while(rs.next())
            {
                temp[i]=rs.getString(1);
                i++;
            }
            return temp;
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public void P_updateitemquantity ( String tbname , String id , String nprice , String data )
    {
        int r1=0,price=0;
        mySQLQueries msql = new mySQLQueries();
        String q = msql.getItemData(id)[6];//qty(now)
        System.out.println("Save Qty ="+data);
        if(q==null)
        {
            q="0";
        }
        System.out.println("Save curQuantity="+q);
        
        if(tbname.equals("purchasedetail"))
        {
            r1=Integer.parseInt(q)+Integer.parseInt(data);
            price = Integer.parseInt(nprice)+(int)(Integer.parseInt(nprice)*0.1);
            System.out.println(price);
        }

        query1 = "update itemdetail set curSalePrice ="+price+", totalQty="+r1+" where itemID='"+id+"'";
        try{
            stmt = con.createStatement();
            if(stmt.executeUpdate(query1)==1)
            {
                System.out.println("One Record is Updated Successfully");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Update Not Successful.", "Update Fail", JOptionPane.ERROR_MESSAGE);
            }
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException",JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public boolean updateRecord(String tbName,String id , String []data)
    {
        if(tbName.equals("supplier"))
            query = "update supplier set Name='"+data[0]+"',Address='"+data[1]+"',PhoneNo='"+data[2]+"',Email='"+data[3]+"'where supplierID='"+id+"'";
        else  if(tbName.equals("customer"))
            query = "update customer set Name='"+data[0]+"',Address='"+data[1]+"',PhoneNo='"+data[2]+"',Email='"+data[3]+"'where customerID='"+id+"'";
        else if(tbName.equals("itemdetail"))
            query = "update itemdetail set itemname='"+data[0]+"',cursaleprice="+data[1]+",remark='"+data[2]+"'where itemid='"+id+"'";
        else if(tbName.equals("brand"))
             query = "update brand set brandname='"+data[0]+"' where brandid='"+id+"'";
        else if(tbName.equals("type"))
             query = "update type set typename='"+data[0]+"' where typeid='"+id+"'";
        else if(tbName.equals("orderdetail"))
             query = "update orderdetail set remark = "+Integer.parseInt(data[0])+" where orderid='"+id+"'";
        else if(tbName.equals("itemdetail01"))
            query = "update itemdetail set purchaseprice="+Integer.parseInt(data[0])+",cursaleprice="+Integer.parseInt(data[1])+" where itemid='"+id+"'";
        else if(tbName.equals("promotionitem"))
            query = "update itemdetail set pid='"+data[0]+"' where itemid='"+id+"'";
        
            
            
        else if(tbName.equals("orders"))
        {
             int cat = data[0].indexOf("(");
             query = "update orders set orderdate='"+data[0].substring(0,cat)+"'where orderid='"+id+"'";
        }
        else if(tbName.equals("orderdetails"))
             query = "update orderdetail set checkpaid ='"+data[0]+"' where orderid='"+id+"'";
        else  if(tbName.equals("staff"))
            query = "update staff set Name='"+data[0]+"',Address='"+data[1]+"',PhoneNo='"+data[2]+"',nrc='"+data[3]+"',username='"+data[4]+"',password='"+data[5]+"',status='"+data[6]+"'where staffid='"+id+"'";
        else if(tbName.equals("staffs"))
            query = "update staff set username='"+data[0]+"',password='"+data[1]+"'where staffid='"+id+"'";
        else  if(tbName.equals("staffed"))
            query = "update staff set Name='"+data[0]+"',Address='"+data[1]+"',PhoneNo='"+data[2]+"',nrc='"+data[3]+"',status='"+data[4]+"'where staffid='"+id+"'";

            try{
                stmt = con.createStatement();
                if(stmt.executeUpdate(query)==1)
                {
                    return true;
                }
                else{
                    JOptionPane.showMessageDialog(null,"The table does not contain the specified ID.","Update Fail",JOptionPane.ERROR_MESSAGE);
                    return false;}
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException", JOptionPane.ERROR_MESSAGE);
                return false;
            }
    }
    
    public boolean updateorderRecord(String tbName,String id , String data)
    {
        if(tbName.equals("order"))
             query = "update orders set checkpaid ='"+data+"' where orderid='"+id+"'";
        else if(tbName.equals("itempromotion"))
            query = "update itemdetail set pid='"+id+"' where itemid='"+data+"'";
         else if(tbName.equals("damage"))
            query = "update itemdetail set totalQty=totalQty-'"+id+"' where itemid='"+data+"'";

            try{
                stmt = con.createStatement();
                if(stmt.executeUpdate(query)==1)
                {
                    return true;
                }
                else{
                    JOptionPane.showMessageDialog(null,"The table does not contain the specified ID.","Update Fail",JOptionPane.ERROR_MESSAGE);
                    return false;}
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage(), "SQLException", JOptionPane.ERROR_MESSAGE);
                return false;
            }
    }
    public String getTypeName(String typeid)
    {
        try
        {
            String typename;
            stmt=con.createStatement();
            query="select * from type where typeid='"+typeid+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            typename=rs.getString(2);
            return typename;
       }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
    }
    
    public String getAutoid(String field , String tabel , String prefix)
    {
        if(tabel.equals("sale")||tabel.equals("purchase"))
        {
            return connect.getPrimaryKey(field, tabel, prefix);
        }
        else if(tabel.equals("sale01"))
        {
            return connect.getPrimaryKey1(field, "sale", prefix);
        }
        else
        {
            return connect.getPrimaryKey2(field, tabel, prefix);
        }
    }
    public String[] getItemDetailData(String id)
    {
        try{
            stmt = con.createStatement();
            String str[]= new String[5];
            query = "select * from fitemdetail where itemID='"+id+"'";
            rs=stmt.executeQuery(query);
            if(rs.next())
            {
                str[0]=rs.getString(1);//iid
                str[1]=rs.getString(2);//sid
                str[2]=rs.getString(3);//date
                str[3]=rs.getString(4);//price
                str[4]=rs.getString(5);//qty
            }
            return str;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    public String []getSupplierData(String id)
    {
        try
        {
            stmt = con.createStatement();
            String str[];
            query = "select * from supplier where supplierID='"+id+"'";
            str = new String[4];
            rs = stmt.executeQuery(query);
            if(rs.next())
            {
                for(int i = 0 ; i<str.length ; i++)
                {
                    str[i]=rs.getString(i+2);
                }
            }
            return str;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }
    public String []getSupplierData01(String name)
    {
        try
        {
            stmt = con.createStatement();
            String str[];
            query = "select * from supplier where name='"+name+"'";
            str = new String[5];
            rs = stmt.executeQuery(query);
            if(rs.next())
            {
                for(int i = 0 ; i<str.length ; i++)
                {
                    str[i]=rs.getString(i+1);
                }
            }
            return str;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }
    
    public void deleteRecord(String tbName,String id)
    {
        int returnvalue = 0 ;
        String query = "";
        if(tbName.equals("supplier"))
        {
            query = "delete from supplier where supplierID = '"+id+"'";
        }
        if(tbName.equals("orders"))
        {
            query = "delete from orders where orderid = '"+id+"'";
        }
        if(tbName.equals("customer"))
        {
            query = "DELETE FROM customer WHERE customerid = '"+id+"'";
        }
        if(tbName.equals("staff"))
        {
            query = "DELETE FROM staff WHERE staffid = '"+id+"'";
        }
        
        try
        {
            stmt = con.createStatement();
            if(!query.equals("")&&stmt.executeUpdate(query)==1)
                JOptionPane.showMessageDialog(null, "The record is deleted successfully in"+tbName+"table.");
            else
                JOptionPane.showMessageDialog(null,"The specified ID does not found in the table .","Delete Fail",JOptionPane.ERROR_MESSAGE);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
        }

    } 
    public void deletetable(String tbName)
    {
        int returnvalue = 0 ;
        String query = "";
        if(tbName.equals("temppro"))
        {
            query = "delete from temppro";
        }
        
        try
        {
            stmt = con.createStatement();
            if(!query.equals("")&&stmt.executeUpdate(query)==1)
                System.out.println("item deleted");
            else
                System.out.println("no item is deleted");;
            
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
        }

    } 
    public String getBrandID(String brandname)
    {
        try{
            String brandid;
            stmt = con.createStatement();
            query = "select brandID from brand where brandName='"+brandname+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            brandid=rs.getString(1);
            return brandid;
        }catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
    }
    public String getTypeID(String typename)
    {
        String typeid;
        try
        {
            stmt = con.createStatement();
            query = "select typeID from type where typeName='"+typename+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            typeid=rs.getString(1);
            return typeid;
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
    }
    public String getproTypeid(String typename)
    {
        String typeid;
        try
        {
            stmt = con.createStatement();
            query = "select ptid from promotiontype where ptname='"+typename+"';";
            rs=stmt.executeQuery(query);
            rs.next();
            typeid=rs.getString(1);
            return typeid;
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null;
        }
    }
    public ResultSet getOrderData2(String id)
    {
        try{
            stmt = con.createStatement();

            //query = "select * from orders,orderdetail where orders.orderid='"+id+"'";
           // query = "select * from orders,orderdetail where orders.orderid= orderdetail.orderid and orderdetail.orderid='"+id+"'";
            query = "select * from orders,orderdetail where orderdetail.orderid='"+id+"' and orders.orderid='"+id+"'";
            rs=stmt.executeQuery(query);
            return rs;
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(),"SQLException",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public String[]getIDForChoice(String tbName)
    {
        try{
            if((tbName.equals("itemdetail")))
                rs = connect.SQLSelect("itemID","itemdetail ");
            else if(tbName.equals("supplier"))
                rs=connect.SQLSelect("supplierid", "supplier");
            else if(tbName.equals("customer"))
                rs=connect.SQLSelect("customerID", "customer");
            else if(tbName.equals("brand"))
                rs=connect.SQLSelect("brandID", "brand");
            else if(tbName.equals("orders"))
                rs=connect.SQLSelect("distinct orderid","orders");
            else if(tbName.equals("orderdetail"))
                rs = connect.SQLSelect("orderid","orderdetail where remark !=0");
            else if(tbName.equals("staff"))
                rs=connect.SQLSelect("staffid", "staff");
            else if(tbName.equals("promotiontype"))
                rs=connect.SQLSelect("ptname", "promotiontype");
            else if(tbName.equals("type"))
                rs=connect.SQLSelect("typeid", "type");
            

            int rowcount = 0 ;
            while(rs.next())
            {
                rowcount++;
            }
            String temp[]=new String[rowcount];
            rs.beforeFirst();
            int i = 0 ;
            while(rs.next())
            {
                temp[i]=rs.getString(1);
                i++;
            }
            return temp;
        }catch(SQLException sqle)
        {
            System.out.println(sqle);
            return null ;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
