package hproject;

import java.sql.*;
import java.sql.Connection;
import java.util.*;

public class DBConnection
{
	public static Connection con=null;
	String url;
	public static Connection GetMySQLConnection() throws ClassNotFoundException, SQLException
	{
            try{
                    Class.forName("com.mysql.jdbc.Driver");
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/pos01?user=root&password=root");
                    System.out.println ("Connection with MySQL successful");
            }
            catch(ClassNotFoundException cnfx)
            {
                    cnfx.printStackTrace();
                    System.out.println (cnfx);
            }
            catch(SQLException sqle)
            {
                    sqle.printStackTrace();
                    System.out.println (sqle);
            }
            catch(Exception exp)
            {
                    exp.printStackTrace();
                    System.out.println (exp);
            }
            System.out.println (con);
            return con;
	}
        
        public Connection ConnectionStop()
        {
            return null;
        }
        public ResultSet SQLSelect(String field,String table)
        {
            ResultSet rs=null;
            try
            {
                Statement stmt=GetMySQLConnection().createStatement();
                rs=stmt.executeQuery("SELECT "+field+" FROM "+table);
                
            }catch(SQLException ex)
            {
                System.out.println(ex);
            }
            catch(ClassNotFoundException e)
            {
                System.out.println(e);
            }
            return rs;
        }
        
        public boolean ExecuteSQL(String sql)
	{
		try
		{
			Statement stmt=GetMySQLConnection().createStatement();
			stmt.executeUpdate(sql);
			return true;
		}
		catch(ClassNotFoundException cnf)
		{
			System.out.println (cnf);
			return false;
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
        
        public String getPrimaryKey(String field,String table , String prefix)
        {
            ResultSet rs = SQLSelect(field , table);
            int current;
            try
            {
                java.util.ArrayList result = new java.util.ArrayList();
                while(rs.next())
                {
                    result.add(rs.getString(field));
                }
                if(result.size()>0)
                {
                    current = Integer.parseInt(result.get(result.size()-1).toString().substring(2,10))+1;
                   if(current>0&&current<=9){return prefix +"0000000"+current;}
                   else if(current>9 && current<=99)
                   {
                       return prefix +"000000"+current;
                   }
                   else if(current>99 && current<=999)
                   {
                       return prefix +"00000"+current;
                   }
                   else if(current>999 && current<=9999)
                   {
                       return prefix +"0000"+current;
                   }
                   else if(current>9999 && current<=99999)
                   {
                       return prefix +"000"+current;
                   }
                   else if(current>99999 && current<=999999)
                   {
                       return prefix +"00"+current;
                   }
                   else if(current>999999 && current<=9999999)
                   {
                       return prefix +"0"+current;
                   }
                   else if(current>9999999 && current<=99999999)
                   {
                       return prefix +current;
                   }
                }
            }catch(SQLException ex)
            {//SQL Exception

            }
            return prefix+"00000001";//Return
        }//end GetPrimarykey
        
        public String getPrimaryKey1(String field,String table , String prefix)
        {
            ResultSet rs = SQLSelect(field , table);
            int current;
            try
            {
                java.util.ArrayList result = new java.util.ArrayList();
                while(rs.next())
                {
                    result.add(rs.getString(field));
                }
                if(result.size()>0)
                {
                   current = Integer.parseInt(result.get(result.size()-1).toString().substring(2,10))+2;
                   if(current>0&&current<=9){return prefix +"0000000"+current;}
                   else if(current>9 && current<=99)
                   {
                       return prefix +"000000"+current;
                   }
                   else if(current>99 && current<=999)
                   {
                       return prefix +"00000"+current;
                   }
                   else if(current>999 && current<=9999)
                   {
                       return prefix +"0000"+current;
                   }
                   else if(current>9999 && current<=99999)
                   {
                       return prefix +"000"+current;
                   }
                   else if(current>99999 && current<=999999)
                   {
                       return prefix +"00"+current;
                   }
                   else if(current>999999 && current<=9999999)
                   {
                       return prefix +"0"+current;
                   }
                   else if(current>9999999 && current<=99999999)
                   {
                       return prefix +current;
                   }
                }
            }catch(SQLException ex)
            {//SQL Exception

            }
            return prefix+"00000001";//Return
        }
        public String getPrimaryKey2(String field,String table , String prefix)
        {
            ResultSet rs = SQLSelect(field , table);
            int current;
            try
            {
                java.util.ArrayList result = new java.util.ArrayList();
                while(rs.next())
                {
                    result.add(rs.getString(field));
                }
                if(result.size()>0)
                {
                    current = Integer.parseInt(result.get(result.size()-1).toString().substring(3,10))+1;
                   if(current>0 && current<=9)
                   {
                       return prefix +"000000"+current;
                   }
                   else if(current>9 && current<=99)
                   {
                       return prefix +"00000"+current;
                   }
                   else if(current>99 && current<=999)
                   {
                       return prefix +"0000"+current;
                   }
                   else if(current>999 && current<=9999)
                   {
                       return prefix +"000"+current;
                   }
                   else if(current>9999 && current<=99999)
                   {
                       return prefix +"00"+current;
                   }
                   else if(current>99999 && current<=999999)
                   {
                       return prefix +"0"+current;
                   }
                  else if(current>999999 && current<=9999999)
                  {
                      return prefix +current;
                  }
                }
            }catch(SQLException ex)
            {//SQL Exception

            }
            return prefix+"0000001";//Return
        }//end GetPrimarykey2

        public static void main(String args[])throws ClassNotFoundException,SQLException
        {
            DBConnection db=new DBConnection();
            
            db.GetMySQLConnection();
        }
}