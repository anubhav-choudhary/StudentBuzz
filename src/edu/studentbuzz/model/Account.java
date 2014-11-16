package edu.studentbuzz.model;

import edu.studentbuzz.helper.dbConnection;
import edu.studentbuzz.helper.Md5Generator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Account  {  
    private String emailID;
    private String password;
    private Short status;

    public Account() {
    }

    public Account(String emailID) {
        this.emailID = emailID;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public void update() {
       dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Update account set Password='"+password+"', Status='"+status+"' where Email_ID='"+emailID+"'");
        data.close();
    }

    public void remove() {
         dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Delete from account where Email_ID='"+emailID+"'");
        data.close();
    }


    public void findById() {
       dbConnection data = new dbConnection();
       data.open();
       try{
        ResultSet rs =data.executeQuery("Select * from account where Email_ID='"+emailID+"'");
        
            if(rs.next())
            {
            emailID=rs.getString(1);
            status=rs.getShort(3);
            }

            else
            {
             emailID=null;
            }
        }
        catch(SQLException e)
                {System.out.println(e.toString());}
        data.close();
    }

    public void add() {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Insert into account(Email_ID,Password,Status) values('"+emailID+"','"+password+"','"+status+"')");
        data.close();
    }

      public ArrayList<Account> find(String cond) {
        ArrayList<Account> al = new ArrayList<Account>();
        Account obj;
        dbConnection data = new dbConnection();
        data.open();
        ResultSet rs=data.executeQuery(cond);
        
        try{
        while(rs.next())
        {
            obj = new Account();
            obj.setEmailID(rs.getString(1));
            obj.setPassword(rs.getString(2));
            obj.setStatus(rs.getShort(3));
             al.add(obj);
        }
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
        }
        data.close();
        return al;
    }
    
    public void validate(String email, String pass)
    {
        
        dbConnection data = new dbConnection();
       data.open();
        ResultSet rs =data.executeQuery("Select * from account where Password='"+new Md5Generator(pass).getResult()+"' AND Email_ID='"+email+"'");
        try{
            if(rs.next())
            {
            emailID=rs.getString(1);
            password=rs.getString(2);
            status=rs.getShort(3);
            }
            else
            {
             emailID=null;
            }
        }
        catch(SQLException e)
                {System.out.println(e.toString());}
        data.close();
    }
}