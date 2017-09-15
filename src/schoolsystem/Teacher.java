/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolsystem;

   
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Teacher extends Teachers_Details{
    
    Connection con=null;
    PreparedStatement pst =null;
    ResultSet rs=null;
    
            private String ID;
            private String FNAME;
            private String LNAME;
            private String ADDRESS;
            private int PHONE;
            private int AGE;
            private String DATE;
            
    public Teacher(String Id,String Fname,String Lname,String Address,int Phone,int age,String Date)
    {
            this.ID=Id;
            this.FNAME=Fname;
            this.LNAME=Lname;
            this.ADDRESS=Address;
            this.PHONE=Phone;
            this.AGE=age;
            this.DATE=Date;
            
        con=ConnectDB.connect();
    }
     
    Teachers_Details t=new Teachers_Details();
     
     
     
    public void add(String id,String fname,String lname,String address,int phone,int age,String date)
    {
        try{
           
            String sql="INSERT INTO Teacher(Teacher_ID,fname,lname,address,phone,age,Date_Joined )"
                    +"VALUES('"+id+"','"+fname+"','"+lname+"','"+address+"','"+phone+"','"+age+"','"+date+"')";
           
            pst = con.prepareStatement(sql);
            pst.execute(sql);
        
        }
        catch(Exception e){
            System.out.println("erro");
        }
    }
    public void delete(String tid)
    {
        try{
            String de="DELETE FROM `Teacher` WHERE Teacher_ID=?";
            pst=con.prepareStatement(de);
            
            pst.setString(1,tid);
            
           int msg= JOptionPane.showConfirmDialog(null,"Do you want to delete Teacher ID : "+tid,"close",JOptionPane.YES_OPTION,JOptionPane.NO_OPTION);
            if(msg==JOptionPane.YES_OPTION)
            {
                pst.executeUpdate();
            }
           
         
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
     public void update_table()
     {
         try{
             
            String sql= "select Teacher_ID,fname,lname,address,phone,age,Date_Joined from Teacher ";
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            
            t.table.setModel(DbUtils.resultSetToTableModel(rs));
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null,e);
         }
     
     }
    public void search(String ID)
    {
        Teachers_Details tt=new Teachers_Details();
        String sql="select * from Teacher where Teacher_ID=?";
    
        try{
             pst=con.prepareStatement(sql);
             pst.setString(1,ID);
             rs=pst.executeQuery();
            
            if(rs.next())
            {
                tt.id.setText(rs.getString("Teacher_ID"));
                tt.fname.setText(rs.getString("fname"));
                tt.lname.setText(rs.getString("lname"));
                tt.address.setText(rs.getString("address"));
                tt.phone.setText(rs.getString("phone"));
                tt.age.setText(rs.getString("age"));
                tt.date.setDate(rs.getDate("Date_Joined"));
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Invalid Teacher ID.....");
            }
        }
        catch(Exception e)
        {
            System.out.println("erro");
        }
    }
   
}
