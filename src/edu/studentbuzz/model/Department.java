package edu.studentbuzz.model;

import edu.studentbuzz.helper.dbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Department {
    private Integer deptID;
    private String name;

    public Department() {
    }

    public Department(Integer deptID) {
        this.deptID = deptID;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void add() {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Insert into department(Dept_ID,Name) values('"+deptID+"','"+name+"')");
        data.close();
    }

    public void update() {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Update department set Name='"+name+"' where Dept_ID='"+deptID+"'");
        data.close();
    }
    
    public void remove() {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Delete from department where Dept_ID='"+deptID+"'");
        data.close();
    }

    public void findById() {
       
       dbConnection data = new dbConnection();
       data.open();
        ResultSet rs =data.executeQuery("Select * from department where Dept_ID='"+deptID+"'");
        try{
            if(rs.next())
            {
            deptID=rs.getInt(1);
            name=rs.getString(2);
            }
            else
            {
             deptID=null;
             
            }
        }
        catch(SQLException e)
                {System.out.println(e.toString());}
        data.close();
    }
    
        public ArrayList<Department> find(String cond) {
        ArrayList<Department>  al = new ArrayList<Department> ();
        Department obj;
        dbConnection data = new dbConnection();
        data.open();
        ResultSet rs=data.executeQuery(cond);
        
        try{
        while(rs.next())
        {
            obj = new Department();
            obj.setDeptID(rs.getInt(1));
            obj.setName(rs.getString(2));
            
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