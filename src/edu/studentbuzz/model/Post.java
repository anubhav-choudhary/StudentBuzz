package edu.studentbuzz.model;

import edu.studentbuzz.helper.dbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Post {
    private Long postID;
    private String writerID;
    private String textContent;
    private Date timestamp;
    private String subcatID;

    public String getSubcatID() {
        return subcatID;
    }

    public void setSubcatID(String subcatID) {
        this.subcatID = subcatID;
    }

    public Post() {
    }

    public Post(Long postID) {
        this.postID = postID;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public String getWriterID() {
        return writerID;
    }

    public void setWriterID(String writerID) {
        this.writerID = writerID;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


   

   
    public void add() {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Insert into post(Post_ID,Writer_ID,TextContent,subcat_ID) values('"+postID+"','"+writerID+"','"+textContent+"','"+subcatID+"')");
        data.close();
    }

   
    public void update() {
         dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Update post set TextContent='"+textContent+"', SubCat_ID='"+subcatID+"' where Post_ID='"+postID+"'");
        data.close();
    }

   
    public void remove() {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Delete from  post where Post_ID='"+postID+"'");
        data.close();
    }

   
    public void findById() {
         dbConnection data = new dbConnection();
       data.open();
        ResultSet rs =data.executeQuery("Select * from post where post_ID='"+postID+"'");
        try{
            if(rs.next())
            {
            postID=rs.getLong(1);
            writerID=rs.getString(2);
            textContent=rs.getString(3);
            timestamp=rs.getTimestamp(4);
            subcatID=rs.getString(5);
            }
            else
            {
             postID=null;
            }
        }
        catch(SQLException e)
                {System.out.println(e.toString());}
        data.close();
    }

   
     public ArrayList<Post> find(String cond) {
        ArrayList<Post> al = new ArrayList<Post>();
        Post obj;
        dbConnection data = new dbConnection();
        data.open();
        ResultSet rs=data.executeQuery(cond);
        
        try{
        while(rs.next())
        {
            obj = new Post();
            obj.setPostID(rs.getLong(1));
            obj.setWriterID(rs.getString(2));
            obj.setTextContent(rs.getString(3));
            obj.setTimestamp(rs.getTimestamp(4));
            obj.setSubcatID(rs.getString(5));
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
    
    public String getDeptName()
    {
       SubCategory obj= new SubCategory(subcatID);
       obj.findById();
       Department dept = new Department(obj.getDeptid());
       dept.findById();
       return dept.getName();
    }
    
    public String getSubCatName()
    {
       SubCategory obj= new SubCategory(subcatID);
       obj.findById();
       return obj.getName();
    }
   
}
