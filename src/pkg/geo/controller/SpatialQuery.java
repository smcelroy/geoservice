package pkg.geo.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import oracle.jdbc.*;

/**
 * Created by ewert on 3/21/2017.
 */
public class SpatialQuery extends GeoLocation {
    private ArrayList<SpatialResult> results;
    private Connection con;

    public SpatialQuery()
    {
        results = new ArrayList<>();
    }

    public void run()
    {
        connectToDb();
        runQuery();
    }

    public void connectToDb()
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:hackthon:@192.168.1.123:1521:hackthon", "SYSTEM", "testing123");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void runQuery()
    {
        //count = SELECT count(*) FROM <spatial_table> WHERE SDO_WITHIN_DISTANCE(<spatial_table.shape>, SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(<json.lat>, <json.lon>, NULL),NULL,NULL)), 'distance='+distance+'');
        try
        {
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT count(*), t.shape.sdo_point.x longitude, t.shape.sdo_point.y latitude " +
                    "FROM <table> t WHERE SDO_WITHIN_DISTANCE(<table_shape>, " +
                    "SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(" + getLongitude() + ", " + getLatitude() + ", NULL), NULL, NULL), " +
                    "'distance=" + getDistance() + "') = 'TRUE'");

            while (rs.next())
            {
                setCount(rs.getInt(1));
                results.add(new SpatialResult(rs.getDouble(2), rs.getDouble(3)));
            }

            con.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<SpatialResult> getResults()
    {
        return results;
    }
}
