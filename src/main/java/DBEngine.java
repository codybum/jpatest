import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBEngine {

    //private BasicDataSource ds;
    private DataSource ds;

    public DBEngine() {

        try {

            File dbsource = new File("demo");
            if(dbsource.exists()) {
                delete(dbsource);
            }

            ds = setupDataSource("jdbc:derby:demo;create=true");

            //Class.forName("com.mysql.cj.jdbc.Driver");
            //ds = setupDataSource("jdbc:mysql://localhost/cresco?characterEncoding=UTF-8","root", "codeman01");


            /*
            ds = new BasicDataSource();
            ds.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
            ds.setUrl("jdbc:derby:demo;create=true");
            */

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void addNode(String region, String agent, String plugin, String status_code, String status_desc, String configparams) {

        try {
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            String stmtString = null;
            if((region != null) && (agent != null) && (plugin != null)) {
                stmtString = "insert into pnode (region_id,agent_id,plugin_id,status_code,status_desc,configparams) " +
                        "values ('" + region + "','" + agent + "','" + plugin + "','" + status_code + "','" + status_desc + "','" + configparams + "')";
            } else if((region != null) && (agent != null) && (plugin == null)) {
                stmtString = "insert into anode (region_id,agent_id,status_code,status_desc,configparams) " +
                        "values ('" + region + "','" + agent + "','" + status_code + "','" + status_desc + "','" + configparams + "')";
            } else if((region != null) && (agent == null) && (plugin == null)) {
                stmtString= "insert into rnode (region_id,status_code,status_desc,configparams) " +
                        "values ('" + region + "','" + status_code + "','" + status_desc + "','" + configparams + "')";
            }
            stmt.executeUpdate(stmtString);
            stmt.close();
            conn.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

    public void initDB() {

        String createRNode = "CREATE TABLE rnode" +
                "(" +
                "   region_id varchar(43) primary key NOT NULL," +
                "   status_code varchar(255)," +
                "   status_desc varchar(255)," +
                "   configparams varchar(255)" +
                ")";

        String createANode = "CREATE TABLE anode" +
                "(" +
                "   region_id varchar(43) NOT NULL," +
                "   agent_id varchar(42) primary key NOT NULL," +
                "   platform varchar(255)," +
                "   environment varchar(255)," +
                "   location varchar(255)," +
                "   status_code varchar(255)," +
                "   status_desc varchar(255)," +
                "   configparams varchar(255)," +
                "   FOREIGN KEY (region_id) REFERENCES rnode(region_id) " +
                ")";

        String createPNode = "CREATE TABLE pnode" +
                "(" +
                "   region_id varchar(43) NOT NULL," +
                "   agent_id varchar(42) NOT NULL," +
                "   plugin_id varchar(43) NOT NULL," +
                "   status_code varchar(255)," +
                "   status_desc varchar(255)," +
                "   configparams varchar(255)," +
                "   FOREIGN KEY (region_id) REFERENCES rnode(region_id), " +
                "   FOREIGN KEY (agent_id) REFERENCES anode(agent_id), " +
                "   CONSTRAINT pNodeID PRIMARY KEY (region_id, agent_id, plugin_id)" +
                ")";

        try {
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createRNode);
            stmt.executeUpdate(createANode);
            stmt.executeUpdate(createPNode);

            stmt.close();
            conn.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean nodeExist(String regionId, String agentId, String pluginId) {
        boolean exist = false;
        try {

            String queryString = null;

            if((regionId != null) && (agentId != null) && (pluginId != null)) {
                //plugin
                queryString = "SELECT COUNT(1) " + "FROM pnode " +
                        "WHERE region_id = '" + regionId + "' and agent_id = '" + agentId + "' and plugin_id = '" + pluginId + "'";
            } else if((regionId != null) && (agentId != null) && (pluginId == null)) {
                //agent
                queryString = "SELECT COUNT(1) " + "FROM anode " +
                        "WHERE region_id = '" + regionId + "' and agent_id = '" + agentId + "'";

            } else if((regionId != null) && (agentId == null) && (pluginId == null)) {
                //region
                queryString = "SELECT COUNT(1) " + "FROM rnode " +
                        "WHERE region_id = '" + regionId + "'";
            }

            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();


            ResultSet rs = stmt.executeQuery(queryString);
            rs.next();
            exist = rs.getBoolean(1);

            rs.close();
            stmt.close();
            conn.close();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return exist;
    }

    public void printnodes() {
        try {

            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM pnode");


            // print out query result
            while (rs.next()) {
                System.out.printf("%s\t%s\t%s\n", rs.getString("region_id"), rs.getString("agent_id"), rs.getString("plugin_id"));
            }
            rs.close();

            rs = stmt.executeQuery("SELECT * FROM anode");

            // print out query result
            while (rs.next()) {
                System.out.printf("%s\t%s\n", rs.getString("region_id"), rs.getString("agent_id"));
            }
            rs.close();

            rs = stmt.executeQuery("SELECT * FROM rnode");

            // print out query result
            while (rs.next()) {
                System.out.printf("%s\n", rs.getString("region_id"));
            }
            rs.close();


            stmt.close();
            conn.close();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

        public static DataSource setupDataSource(String connectURI) {
            return setupDataSource(connectURI,null,null);
        }

        public static DataSource setupDataSource(String connectURI, String login, String password) {
        //
        // First, we'll create a ConnectionFactory that the
        // pool will use to create Connections.
        // We'll use the DriverManagerConnectionFactory,
        // using the connect string passed in the command line
        // arguments.
        //
            ConnectionFactory connectionFactory = null;
            if((login == null) && (password == null)) {
                connectionFactory = new DriverManagerConnectionFactory(connectURI, null);
            } else {
                connectionFactory = new DriverManagerConnectionFactory(connectURI,
                                login, password);
            }

        //
        // Next we'll create the PoolableConnectionFactory, which wraps
        // the "real" Connections created by the ConnectionFactory with
        // the classes that implement the pooling functionality.
        //
        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(connectionFactory, null);

        //
        // Now we'll need a ObjectPool that serves as the
        // actual pool of connections.
        //
        // We'll use a GenericObjectPool instance, although
        // any ObjectPool implementation will suffice.
        //
        ObjectPool<PoolableConnection> connectionPool =
                new GenericObjectPool<>(poolableConnectionFactory);

        // Set the factory's pool property to the owning pool
        poolableConnectionFactory.setPool(connectionPool);

        //
        // Finally, we create the PoolingDriver itself,
        // passing in the object pool we created.
        //
        PoolingDataSource<PoolableConnection> dataSource =
                new PoolingDataSource<>(connectionPool);

        return dataSource;
    }


    public void connectionToDerby() throws SQLException {
        // -------------------------------------------
        // URL format is
        // jdbc:derby:<local directory to save data>
        // -------------------------------------------
        String dbUrl = "jdbc:derby:demo;create=true";
        //conn = DriverManager.getConnection(dbUrl);
    }

    public void printDB() {
        try {

            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();

            //System.out.println("NumActive: " + ds.getNumActive());
            //System.out.println("NumIdle: " + ds.getNumIdle());

            //Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");

            // print out query result
            while (rs.next()) {
                //System.out.printf("%d\t%s\n", rs.getInt("id"), rs.getString("name"));
                //System.out.print(".");
                String s = rs.getString("name");
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void normalDbUsage() throws SQLException {
        Connection conn = ds.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("Create table users (id int primary key, name varchar(36))");
        stmt.close();
        conn.close();

    }

    public void addRecord(int number, String name) {
        try {
            //System.out.print("+");
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into users values (" + number + ",'" + name + "')");
            stmt.close();
            conn.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void delete(File f) throws IOException {
        if (f.isDirectory()) {
            for (File c : f.listFiles())
                delete(c);
        }
        if (!f.delete())
            throw new FileNotFoundException("Failed to delete file: " + f);
    }

}
