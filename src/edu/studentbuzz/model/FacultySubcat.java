package edu.studentbuzz.model;


import edu.studentbuzz.helper.dbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class FacultySubcat {
 
    String email;
    ArrayList<String> subcatlist;

    public FacultySubcat(String email) {
        this.email = email;
        subcatlist = new ArrayList<String>();
    }

    public FacultySubcat() {
        subcatlist = new ArrayList<String>();
    }
    

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getSubcatlist() {
        return subcatlist;
    }

    public void setSubcatlist(ArrayList<String> subcatlist) {
        this.subcatlist = subcatlist;
    }

    public String getEmail() {
        return email;
    }
    
    public void add_subcat(String item)
    {

       subcatlist.add(item);

    }
    
    public void add()
    {
        dbConnection data = new dbConnection();
        Iterator<String> itr;
        String subcat;
        if(subcatlist.size()>0)
        {
        data.open();
        itr=subcatlist.iterator();
        while(itr.hasNext())
        {
        subcat = (String) itr.next();
        data.executeUpdate("Insert into faculty_category values('"+email+"','"+subcat+"')");
        }
        data.close();
        }
    }
    
    public void remove()
    {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Delete from faculty_category where Email_ID='"+email+"'");
        data.close();
    }
    
    public void update()
    {
        remove();
        add();
    }
    
    public void findById()
    {
        dbConnection data = new dbConnection();
       data.open();
       ArrayList<String> al = new ArrayList<String>();
        ResultSet rs =data.executeQuery("Select * from faculty_category where Email_ID='"+email+"'");
        try{
            while(rs.next())
            {
            al.add(rs.getString(2));
            }
            if(al.isEmpty()) email=null;
        }
        catch(SQLException e)
                {System.out.println(e.toString());}
        data.close();
        subcatlist=al;
    }
    
    public boolean search(String item)
    {
        boolean result  =false;
        if(subcatlist.indexOf(item)!=-1) result= true;
        return result;
    }
    
    
}