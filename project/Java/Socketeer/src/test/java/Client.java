import com.denesgarda.Socketeer.data.Connection;
import com.denesgarda.Socketeer.data.End;

import java.io.IOException;

public class Client extends End {
    public Client() throws IOException {
        Connection connection = this.connect("localhost", 11000);
        connection.send("Hello");
        connection.send("Hello again");
    }
}