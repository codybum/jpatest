import java.util.UUID;

public class DBWriter implements Runnable {

    private DBEngine app;

    public DBWriter(DBEngine app) {
        this.app = app;
    }

    public void run(){

        int count = 0;
        while (true) {
            app.addRecord(count,UUID.randomUUID().toString());
            count++;
        }

    }

}
