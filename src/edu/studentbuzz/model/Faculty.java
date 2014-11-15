package edu.studentbuzz.model;

import edu.studentbuzz.helper.dbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Faculty {
    private String emailID;    
    private String name;
    private Integer deptID;
    private String college;
    private String designation;
    private String picture;
    private Short gender;

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
    

    public Faculty() {
    }

    public Faculty(String emailID) {
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

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void update() {
         dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Update faculty set Name='"+name+"', College='"+college+"', Designation='"+designation+"', Picture='"+picture+"', gender='"+gender+"',Dept_ID='"+deptID+"' where Email_ID='"+emailID+"'");
        data.close();
    }

    public void findById() {
        dbConnection data = new dbConnection();
       data.open();
        ResultSet rs =data.executeQuery("Select * from faculty where Email_ID='"+emailID+"'");
        try{
            if(rs.next())
            {
            emailID=rs.getString(1);
            name=rs.getString(2);
            deptID=rs.getInt(3);
            college=rs.getString(4);
            designation=rs.getString(5);
            picture=rs.getString(6);
            gender=rs.getShort(7);
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
        data.executeUpdate("Insert into faculty(Email_ID,Name,Dept_ID,College,Designation,Picture,gender) values('"+emailID+"','"+name+"','"+deptID+"','"+college+"','"+designation+"','"+picture+"','"+gender+"')");
        data.close();
    }

    public void remove() {
         dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Delete from faculty where Email_ID='"+emailID+"'");
        data.close();
    }

   public ArrayList<Faculty> find(String cond) {
        ArrayList<Faculty> al = new ArrayList<Faculty>();
        Faculty obj;
        dbConnection data = new dbConnection();
        data.open();
        ResultSet rs=data.executeQuery(cond);
        
        try{
        while(rs.next())
        {
            obj = new Faculty();
            obj.setEmailID(rs.getString(1));
            obj.setName(rs.getString(2));
            obj.setDeptID(rs.getInt(3));
            obj.setCollege(rs.getString(4));
            obj.setDesignation(rs.getString(5));
            obj.setPicture(rs.getString(6));
            obj.setGender(rs.getShort(7));
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
    
    
}