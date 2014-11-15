package edu.studentbuzz.model;

import edu.studentbuzz.helper.dbConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Comment {
    private Long commentID;
    private String postID;
    private String writeID;
    private String textContent;
    private Date timestamp;

    public Comment() {
    }

    public Comment(Long commentID) {
        this.commentID = commentID;
    }

    public Long getCommentID() {
        return commentID;
    }

    public void setCommentID(Long commentID) {
        this.commentID = commentID;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getWriteID() {
        return writeID;
    }

    public void setWriteID(String writeID) {
        this.writeID = writeID;
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
        data.executeUpdate("Insert into comment(Comment_ID,Post_ID,Writer_ID,TextContent) values('"+commentID+"','"+postID+"','"+writeID+"','"+textContent+"')");
        data.close();
    }

    public void update() {
         dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Update comment set TextContent='"+textContent+"' where Comment_ID='"+commentID+"'");
        data.close();
    }

    public void remove() {
          dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Delete from comment where Comment_ID='"+commentID+"'");
        data.close();
    }

    public void findById() {
         dbConnection data = new dbConnection();
       data.open();
        ResultSet rs =data.executeQuery("Select * from comment where Comment_ID='"+commentID+"'");
        try{
            if(rs.next())
            {
            commentID=rs.getLong(1);
            postID=rs.getString(2);
            writeID=rs.getString(3);
            textContent=rs.getString(4);
            timestamp=rs.getTimestamp(5);
            }
            else
            {
             commentID=null;
           }
        }
        catch(SQLException e)
                {System.out.println(e.toString());}
        data.close();
    }

    public ArrayList<Comment> find(String cond) {
        ArrayList<Comment> al = new ArrayList<Comment>();
        Comment obj;
        dbConnection data = new dbConnection();
        data.open();
        ResultSet rs=data.executeQuery(cond);
        
        try{
        while(rs.next())
        {
            obj = new Comment();
            obj.setCommentID(rs.getLong(1));
            obj.setPostID(rs.getString(2));
            obj.setWriteID(rs.getString(3));
            obj.setTextContent(rs.getString(4));
            obj.setTimestamp(rs.getTimestamp(5));
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
