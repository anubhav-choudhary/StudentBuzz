package edu.studentbuzz.model;

import edu.studentbuzz.helper.dbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Report {
    private Long reportid;
    private int postid;
    private String textcontent;
    private Date timestamp;
    private String reporterid;

    public String getReporterid() {
        return reporterid;
    }

    public void setReporterid(String reporterid) {
        this.reporterid = reporterid;
    }
    
    public Report() {
    }

    public Report(Long reportid) {
        this.reportid = reportid;
    }

    public Report(Long reportid, int postid, String textcontent, Date timestamp) {
        this.reportid = reportid;
        this.postid = postid;
        this.textcontent = textcontent;
        this.timestamp = timestamp;
    }

    public Long getReportid() {
        return reportid;
    }

    public void setReportid(Long reportid) {
        this.reportid = reportid;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getTextcontent() {
        return textcontent;
    }

    public void setTextcontent(String textcontent) {
        this.textcontent = textcontent;
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
        data.executeUpdate("Insert into report(reportid,ReporterID,postid,Textcontent) values('"+reportid+"','"+reporterid+"','"+postid+"','"+textcontent+"')");
        data.close();
    }

    public void update() {
       dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Update report set Textcontent='"+textcontent+"' where reportid='"+reportid+"'");
        data.close();
    }

    public void remove() {
        dbConnection data = new dbConnection();
        data.open();
        data.executeUpdate("Delete from report where reportid='"+reportid+"'");
        data.close();
    }

        public ArrayList<Report> find(String cond) {
        ArrayList<Report> al = new ArrayList<Report>();
        Report obj;
        dbConnection data = new dbConnection();
        data.open();
        ResultSet rs=data.executeQuery(cond);
        
        try{
        while(rs.next())
        {
            obj = new Report();
            obj.setReportid(rs.getLong(1));
            obj.setReporterid(rs.getString(2));
            obj.setPostid(rs.getInt(3));
            obj.setTextcontent(rs.getString(4));
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

    public void findById() {
        dbConnection data = new dbConnection();
       data.open();
        ResultSet rs =data.executeQuery("Select * from report where reportid='"+reportid+"'");
        try{
            if(rs.next())
            {
            reportid=rs.getLong(1);
            reporterid=rs.getString(2);
            postid=rs.getInt(3);
           textcontent=rs.getString(4);
           timestamp=rs.getTimestamp(5);
            }
            else
            {
             reportid=null;
            }
        }
        catch(SQLException e)
                {System.out.println(e.toString());}
        data.close();
    }  
}