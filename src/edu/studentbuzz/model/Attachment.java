package edu.studentbuzz.model;

import edu.studentbuzz.helper.dbConnection;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Attachment {
    private Long attachmentID;
    private String filename;
    private BigInteger postID;
    private String url;
    private Integer size;
    private String fileType;

    public Attachment() {
    }

    public Attachment(Long attachmentID) {
        this.attachmentID = attachmentID;
    }

    public Attachment(Long attachmentID, String filename) {
        this.attachmentID = attachmentID;
        this.filename = filename;
    }

    public Long getAttachmentID() {
        return attachmentID;
    }

    public void setAttachmentID(Long attachmentID) {
        this.attachmentID = attachmentID;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public BigInteger getPostID() {
        return postID;
    }

    public void setPostID(BigInteger postID) {
        this.postID = postID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void add()
    {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Insert into attachment values('"+attachmentID+"','"+filename+"','"+postID+"','"+url+"','"+size+"','"+fileType+"')");
        data.close();
    }
    
    public void remove()
    {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Delete from attachment where Attachment_ID='"+attachmentID+"'");
        data.close();
    }
    
    
    public void findById()
    {
        dbConnection data = new dbConnection();
       data.open();
        ResultSet rs =data.executeQuery("Select * from attachment where Attachment_ID='"+attachmentID+"'");
        try{
            if(rs.next())
            {
            attachmentID=rs.getLong(1);
            filename=rs.getString(2);
            postID=new BigInteger(rs.getString(3));
            url=rs.getString(4);
            size=rs.getInt(5);
            fileType=rs.getString(6);
            }

            else
            {
             attachmentID=null;
            }
        }
        catch(SQLException e)
                {System.out.println(e.toString());}
        data.close();
    }
    
    public ArrayList<Attachment> find(String cond)
    {
        ArrayList<Attachment> al = new ArrayList<Attachment>();
        Attachment obj;
        dbConnection data = new dbConnection();
        data.open();
        ResultSet rs=data.executeQuery(cond);
        
        try{
        while(rs.next())
        {
            obj = new Attachment();
            obj.setAttachmentID(rs.getLong(1));
            obj.setFilename(rs.getString(2));
            obj.setPostID(new BigInteger(rs.getString(3)));
            obj.setUrl(rs.getString(4));
            obj.setSize(rs.getInt(5));
            obj.setFileType(rs.getString(6));
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
