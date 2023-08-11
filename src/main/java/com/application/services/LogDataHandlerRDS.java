package com.application.services;

import com.application.domain.DimensionsModel;

import java.sql.CallableStatement;
import java.sql.Connection;

public class LogDataHandlerRDS {


    public static boolean InsertIntoDatabase(DimensionsModel dimension){

        try{

            Connection conn = DataSource.getConnection();
            CallableStatement cstmt = conn.prepareCall("CALL insert_ALL(?,?,?,?,?,?,?,?)");
            cstmt.setString(1,dimension.getTimestamp());
            cstmt.setString(2,dimension.getLevel());
            cstmt.setInt(3,dimension.getCpu());
            cstmt.setInt(4,dimension.getRam());
            cstmt.setString(5,dimension.getApiEndpoint());
            cstmt.setString(6,dimension.getStatus());
            cstmt.setString(7,dimension.getServiceName());
            cstmt.setString(8,dimension.getIp());

            cstmt.executeUpdate();

            return true;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
