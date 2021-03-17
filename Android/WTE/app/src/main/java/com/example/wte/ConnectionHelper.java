package com.example.wte;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionHelper {
    //Member Variables
    Connection con;
    String uName, pWord, endpoint, port, database, driver, connectionString;

    @SuppressLint("NewApi")
    public Connection createConnection() {
        endpoint = "adm-aws-sql-server-01.cstdy1oacghn.us-east-2.rds.amazonaws.com";
        database = "Where_To_Eat";
        uName = "admin";
        pWord = "VIZOaUzaS57lRWGBqekM";
        port = "1433";
        driver = "new com.microsoft.sqlserver.jdbc.SQLServerDriver()";
        connectionString = "jdbc:jtds:sqlserver://" + endpoint + ":" + port + ";database=" + database + ";user=" + uName + ";password=" + pWord +
                ";encrypt=true;trustServerCertificate=true;";
        /*

       connectionString = "jdbc:sqlserver://" + endpoint + ":" + port + ";database=" + database + ";user=" + uName + ";password=" + pWord +
                            ";encrypt=true;trustServerCertificate=true;";
        connectionString = "jdbc:jtds:sqlserver://" + endpoint + ":" + port + ";database=" + database + ";user=" + uName + ";password=" + pWord +
                            ";encrypt=true;trustServerCertificate=true;";
         */

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Connection connection = null;
        String connectionURL = null;

        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            //Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();

            connection = DriverManager.getConnection(connectionString);
        } catch (Exception problem)   {
            Log.e("Error: ", problem.getMessage());
            String errorMessage = "Error: " + problem.getMessage();
        }//End of the try / catch blocks
        return connection;
    }//End of the method createConnection

}//End of class ConnectionHelper
