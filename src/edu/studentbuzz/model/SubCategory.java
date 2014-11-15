package edu.studentbuzz.model;

import edu.studentbuzz.helper.dbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubCategory {
    
    private String subID;
    private String name;
    private int deptid;

    public SubCategory() {
    }

    public SubCategory(String subID) {
        this.subID = subID;
    }

    public SubCategory(String subID, String name, int deptid) {
        this.subID = subID;
        this.name = name;
        this.deptid = deptid;
    }

    public String getSubID() {
        return subID;
    }

    public void setSubID(String subID) {
        this.subID = subID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeptid() {
        return deptid;
    }

    public void setDeptid(int deptid) {
        this.deptid = deptid;
    }

    
    
    public void add() {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Insert into sub_category(Sub_ID,Name,Dept_id) values('"+subID+"','"+name+"','"+deptid+"')");
        data.close();
    }

    public void update() {
       dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Update sub_category set Name='"+name+"', Dept_ID='"+deptid+"' where Sub_ID='"+subID+"'");
        data.close();
    }

    public void remove() {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Delete from sub_category where Sub_ID='"+subID+"'");
        data.close();
    }

    public void findById() {
         dbConnection data = new dbConnection();
       data.open();
        ResultSet rs =data.executeQuery("Select * from sub_category where Sub_ID='"+subID+"'");
        try{
            if(rs.next())
            {
            subID=rs.getString(1);
            name=rs.getString(2);
            deptid=rs.getShort(3);
            }
            else
            {
             subID=null;
            }
        }
        catch(SQLException e)
                {System.out.println(e.toString());}
        data.close();
    }

    public ArrayList<SubCategory> find(String cond) {
        ArrayList<SubCategory>  al = new ArrayList<SubCategory> ();
        SubCategory obj;
        dbConnection data = new dbConnection();
        data.open();
        ResultSet rs=data.executeQuery(cond);
        
        try{
        while(rs.next())
        {
            obj = new SubCategory();
            obj.setSubID(rs.getString(1));
            obj.setName(rs.getString(2));
            obj.setDeptid(rs.getInt(3));
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
