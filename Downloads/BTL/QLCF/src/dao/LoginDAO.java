package dao;

import java.sql.ResultSet;

import connectBD.Connect_DB;

public class LoginDAO {
    
    public ResultSet executeQuery(String sql) {
        return Connect_DB.getInstance().executeQuery(sql);
    }
    
}