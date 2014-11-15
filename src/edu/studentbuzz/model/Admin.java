package edu.studentbuzz.model;

import edu.studentbuzz.helper.dbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin {
    private String emailID;
    private String name;
    private String picture;
    private Short gender ;

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Admin() {
    }

    public Admin(String emailID) {
        this.emailID = emailID;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public void update() {
         
         dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Update admin set Name='"+name+"', Picture='"+picture+"' , gender='"+gender+"' where Email_ID='"+emailID+"'");
        data.close();
    }

    public void findById() {
         dbConnection data = new dbConnection();
       data.open();
        ResultSet rs =data.executeQuery("Select * from admin where Email_ID='"+emailID+"'");
        try{
            if(rs.next())
            {
            emailID=rs.getString(1);
            name=rs.getString(2);
            picture=rs.getString(3);
            gender=rs.getShort(4);
            
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
        data.executeUpdate("Insert into admin(Email_ID,Name,Picture,gender) values('"+emailID+"','"+name+"','"+picture+"','"+gender+"')");
        data.close();
    }

public ArrayList<Admin> find(String cond) {
        ArrayList<Admin> al = new ArrayList<Admin>();
        Admin obj;
        dbConnection data = new dbConnection();
        data.open();
        ResultSet rs=data.executeQuery(cond);
        
        try{
        while(rs.next())
        {
            obj = new Admin();
            obj.setEmailID(rs.getString(1));
            obj.setName(rs.getString(2));
            obj.setPicture(rs.getString(3));
            obj.setGender(rs.getShort(4));
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
 public void remove() {
         dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Delete from admin where Email_ID='"+emailID+"'");
        data.close();
    } 
}