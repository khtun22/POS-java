/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hproject;

/**
 *
 * @author GuHeHe
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class test extends JFrame implements KeyListener {

    JLabel label;

    private static int p=0,r=0;
    public test(String s) {
        super(s);
        JPanel p = new JPanel();
        label = new JLabel("Key Listener!");
        p.add(label);
        add(p);
        addKeyListener(this);
        setSize(200, 100);
        setVisible(true);
        changefixpri(totalsp(30000));
        tff(tf("t"));

    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key typed");
            
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key typed");
            
        }

    }

    public void tff(boolean tf)
    {
        boolean tfff=tf;
        if(tfff)
        {
            System.out.println("True");
        }
        else
        {
            System.out.println("false");
        }
    }
    public boolean tf(String str)
    {
        if(str.equals("t"))
        {
            return true;
        }
        return false;
    }
    
    public int totalsp(int sp)
    {
        int tenper;
        tenper=sp/10;
        sp=sp+tenper;
        System.out.println("10 per is : "+sp);
        return sp;
    }

    public String changefixpri(int cfp)
    {
        int cvi = 0;
        String str = null;
        if(cfp%100!=0)
        {
            cvi=(int) (cfp/100);
            cvi=cvi*100;
            cvi=cvi+100;
        }
        else
        {
            cvi=cfp;
        }
        str=Integer.toString(cvi);
        System.out.println("fix pri is :"+str);
        return str;
    }
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key pressed");
            p++;
            System.out.println(p);
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
            p--;
            System.out.println(p);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key Released");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key Released");
        }
    }

    public static void main(String[] args) {
        new test("Key Listener Tester");
        System.out.println("total sp is :");
    }
}
