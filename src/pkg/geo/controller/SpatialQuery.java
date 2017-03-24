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
            con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.123:1521:orcl", "SYSTEM", "Testing123");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void runQuery()
    {
        try
        {
            Statement stmt = con.createStatement();

            /*ResultSet rs = stmt.executeQuery("SELECT t.SHAPE.sdo_point.x longitude, t.SHAPE.sdo_point.y latitude " +
                            "FROM hackathon t WHERE SDO_WITHIN_DISTANCE(t.SHAPE, " +
                    "MDSYS.SDO_GEOMETRY(2001, NULL, SDO_POINT_TYPE(5, 5, NULL),  NULL, NULL), " + "'distance=10') = 'TRUE'");*/

            ResultSet rs = stmt.executeQuery("select count(*) from HELP");

            while (rs.next())
            {
                setCount(rs.getInt(1));
                //results.add(new SpatialResult(rs.getDouble(2), rs.getDouble(3)));
            }

            System.out.println(getCount());
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
