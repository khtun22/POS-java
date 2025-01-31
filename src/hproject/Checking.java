/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hproject;

import javax.swing.*;
import java.util.*;
import java.lang.String.*;
import javax.swing.JOptionPane;
public class Checking {
    
    public static boolean IsNull(String str)
    {
        if(str.trim().equals("") || str.trim().equals(null))
            return true;
        else
            return false;
    }
    
    public static boolean IsValidName(String str)
    {
        if(IsNull(str)||str.startsWith(""))
            return false;
        if(!IsLetter(str))
            return false;
        return true;
    }
    public static boolean checkpassword(String str01, String str02)
    {
        if(str01.equals(str02))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public static boolean checkprice(int pp, String sp,String s)
    {
        int salep=Integer.parseInt(sp);
        if(s.equals("order"))
        {
            if(salep>pp)
            {
                return true;
            }
        }
        else if(s.equals("promotion"))
        {
            if(salep>pp)
            {
                return true;
            }
        }
        else
        {
            // think about sale 
        }
        
        return false;
    }
    
    public static String checkboxche(JCheckBox p,JCheckBox s,JCheckBox sf)
    {
        String str = null;
        if(p.isSelected())
        {
            str="P";
        }
        if(s.isSelected())
        {
            str="S";
        }
        
        if(p.isSelected() && s.isSelected())
        {
            str="PS";
        }
        
        if(!p.isSelected() && !s.isSelected())
        {
            str="N";
        }
        if(p.isSelected() && s.isSelected() && sf.isSelected())
        {
            str="PSN";
        }
        return str;
    }
    public static boolean checkqtyhigh(int dataqty, int intqty)
    {
        if(dataqty<intqty)
        {
            return false;
        }
        return true;
    }
    public static boolean IsLetter(String str)
    {
        boolean b = false ;
        for(int i=0 ; i<str.length() ; i++)
        {
            if(Character.isLetter(str.charAt(i)))b = true ;
            else { b = false ; break ;}
        }
      return b;
    }
    public static boolean IsEformat(String str)
    {
        boolean b = false ;
        int t = str.indexOf(".");
        int a = str.indexOf("@");
        if((t< 0)|| (a<0) || (str.indexOf(" ")>0) )
        {
            return b ; 
        }
        String st1 = str.substring(0,a);
        String st = str.substring(t+1);
        if((!st1.trim().equals(""))&&(st.equals("com")))
        {
            b=true;
            return b;
        }
        else
            return b ;
       }
    public static boolean IsAllDigit(String str)
    {
        boolean b = false ;
        for(int i = 0 ; i<str.length() ; i++)
        {
            if(Character.isDigit(str.charAt(i)))b=true;
            else { b=false ; break ;}

        }
        return b;
    }
    
    public static boolean checktxtquantity(String strqp)
    {
        if(strqp.equals(" "))
        {
            JOptionPane.showMessageDialog(null, "You must enter the Quantity!");
            return false;
        }
        
        else if(Integer.parseInt(strqp)>10000)
        {
            JOptionPane.showMessageDialog(null, "The Quantity you entered is too much!");
            return false;
        }
        
        
        /*else if(Integer.parseInt(strqp)==0)
        {
            JOptionPane.showMessageDialog(null, "You can't enter zero to quantity!");
            return false;
        } 
        else if(!IsAllDigit(strqp))
        {
            JOptionPane.showMessageDialog(null, "You must enter NUMBER for Quantity!");
            return false;
        }*/
        else return true;
    }
    public static boolean checktxtprice(String strqp)
    {
        if(strqp.equals(""))
        {
            JOptionPane.showMessageDialog(null, "You must enter the Price!");
            return false;
        }
        else if(!IsAllDigit(strqp))
        {
            JOptionPane.showMessageDialog(null, "You must enter NUMBER for Price!");
            return false;
        }
        else if(Long.parseLong(strqp)>1000000000)
        {
            JOptionPane.showMessageDialog(null, "The Price you entered is too much (more than 1,000,000,000)! ");
            return false;
        }
        else return true;
    }
    public static boolean checksaletxtprice(String strqp)
    {
        if(strqp.equals(""))
        {
            JOptionPane.showMessageDialog(null, "You must enter the Price!");
            return false;
        }
        else if(!IsAllDigit(strqp))
        {
            JOptionPane.showMessageDialog(null, "You must enter NUMBER for Price!");
            return false;
        }
        else if(Long.parseLong(strqp)>1000000000)
        {
            JOptionPane.showMessageDialog(null, "The Price you entered is too much (more than 1,000,000,000)! ");
            return false;
        }
        else return true;
    }
    
    public static boolean checkorderQuantity(String strqp , String id)
    {
        mySQLQueries mysql = new mySQLQueries();
        String q = mysql.getItemData(id)[5];
        if(Integer.parseInt(strqp)>Integer.parseInt(q)-2)
        {
           return false;
        }
        else return true;

    }
    public static boolean IsContain(char c, String str)
    {
        for(int i = 0 ; i<str.length() ; i++)
        {if(str.charAt(i)==c)return true;}
        return false;
    }
    
    public static String Sumamount(Vector data , int t)
    {
        long sum = 0 ;
        for(int i = 0 ; i<data.size() ; i++)
            sum+=Long.parseLong((String)data.elementAt(i));
        if(t==1)
        {
            int len = String.valueOf(sum).length(),
            index = 0 ;
            StringBuffer str = new StringBuffer("");
            for(int i = 0 ; i<len ; i++)
            {
                if(index==3)
                {
                    str.append(",");
                    index = 0 ;
                    i--;
                }
                else
                {
                    str.append(String.valueOf(sum).charAt(len-i-1));
                    index++;
                }
            }
                return str.reverse().toString();
        }

        else
        {
            return String.valueOf(sum);
        }
    }
    public static int Sumamount01(Vector data)
    {
        long sum = 0 ;
        for(int i = 0 ; i<data.size() ; i++)
            sum+=Long.parseLong((String)data.elementAt(i));
        return (int) sum;
    }
}
