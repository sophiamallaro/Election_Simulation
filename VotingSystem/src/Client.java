/**
 * Created by kanderson8 on 12/1/16.
 */
public class Client extends NetworkConnection {
    @Override
    protected boolean isServer() {
        return false;
    }

    @Override
    protected String getIP() {
        return null;
    }

    @Override
    protected int getPort() {
        return 0;
    }
}
