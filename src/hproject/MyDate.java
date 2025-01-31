/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hproject;
import java.util.*;
/**
 *
 * @author Chan Myae Soe Oo
 */
public class MyDate {
public String getdate()
{
    StringBuffer str = new StringBuffer("");
    Date date = new Date();

    System.out.println(date);
    //Sun Jan 15 07:48:30 MMT 2012

    str.append(String.valueOf(date).substring(24));
    //2012

    str.append("-");
    str.append(date.getMonth()+1);
    //String.valeOf(date).substring(3,7)//1

    str.append("-");
    str.append(String.valueOf(date).substring(8,10));
    //15

    str.append(" (");
    str.append(String.valueOf(date).substring(0,3));
    //Sun

    str.append(")");
    return str.toString();
    //2012-1-15(Sun)
    }
}
