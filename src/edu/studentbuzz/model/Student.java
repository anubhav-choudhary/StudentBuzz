package edu.studentbuzz.model;

import edu.studentbuzz.helper.dbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student {
    private String emailID;
    private String name;
    private Short semester;
    private String college;
    private String branch;
    private Boolean gender;
    private String picture;

    public Student() {
    }

    public Student(String emailID) {
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

    public Short getSemester() {
        return semester;
    }

    public void setSemester(Short semester) {
        this.semester = semester;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void update() {
       dbConnection data = new dbConnection();
        data.open();
        int sgender = (gender)?1:0;
        data.executeUpdate("Update student set Name='"+name+"', Semester='"+semester+"',College='"+college+"', Branch='"+branch+"', Gender="+sgender+", Picture='"+picture+"' where Email_ID='"+emailID+"'");
        data.close();
    }

    public void findById() {
         dbConnection data = new dbConnection();
       data.open();
        ResultSet rs =data.executeQuery("Select * from student where Email_ID='"+emailID+"'");
        try{
            if(rs.next())
            {
            emailID=rs.getString(1);
            name=rs.getString(2);
            semester=rs.getShort(3);
            college=rs.getString(4);
            branch=rs.getString(5);
            gender=rs.getBoolean(6);
            picture=rs.getString(7);
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
        int sgender = (gender)?1:0;
        data.executeUpdate("Insert into student(Email_ID,Name,Semester,College,Branch,Gender,Picture) values('"+emailID+"','"+name+"','"+semester+"','"+college+"','"+branch+"',"+sgender+",'"+picture+"')");
        data.close();
    }
    
    public void remove() {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Delete from student where Email_ID='"+emailID+"'");
        data.close();
    }

    public ArrayList<Student> find(String cond) {
        ArrayList<Student>  al = new ArrayList<Student>();
        Student obj;
        dbConnection data = new dbConnection();
        data.open();
        ResultSet rs=data.executeQuery(cond);
        
        try{
        while(rs.next())
        {
            obj = new Student();
            obj.setEmailID(rs.getString(1));
            obj.setName(rs.getString(2));
            obj.setSemester(rs.getShort(3));
            obj.setCollege(rs.getString(4));
            obj.setBranch(rs.getString(5));
            obj.setGender(rs.getBoolean(6));
            obj.setPicture(rs.getString(7));
            
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
