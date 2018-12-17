import java.util.UUID;

public class DBReader implements Runnable {

    private DBEngine app;

    public DBReader(DBEngine app) {
        this.app = app;
    }

    public void run(){

        int count = 0;
        while (true) {
            //app.addRecord(count,UUID.randomUUID().toString());
            app.printDB();
            count++;
        }

    }

}
