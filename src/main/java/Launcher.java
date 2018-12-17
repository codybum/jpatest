import java.util.UUID;

public class Launcher {
    //Connection conn;

    public static void main(String[] args)  {


        try {

            /*
            app.normalDbUsage();
            app.initDB();


            String region = "region-" + UUID.randomUUID().toString();
            String agent = "agent-" + UUID.randomUUID().toString();
            String plugin = "plugin-" + UUID.randomUUID().toString();

            String statusCode = "10";
            String statusDesc = "Ok";
            String configParams = null;


            app.addNode(region,null,null,statusCode,statusDesc,configParams);
            app.addNode(region,agent,null,statusCode,statusDesc,configParams);
            app.addNode(region,agent,plugin,statusCode,statusDesc,configParams);
            */


            while (true) {




                //new Thread(new DBWriter(app)).run();
                //new Thread(new DBReader(app)).run();
                //app.insertkey("region-" + UUID.randomUUID().toString(),null,null);
                //app.insertkey("region-" + UUID.randomUUID().toString(),"agent-" + UUID.randomUUID().toString(), null);
                //app.insertkey("region-" + UUID.randomUUID().toString(),"agent-" + UUID.randomUUID().toString(),"plugin-" + UUID.randomUUID().toString());


                //app.printnodes();


                Thread.sleep(1000);
            }


        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

}