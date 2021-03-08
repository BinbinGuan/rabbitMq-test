package bastion;

import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.common.IOUtils;
import net.schmizz.sshj.connection.channel.direct.DirectConnection;
import net.schmizz.sshj.connection.channel.direct.Session;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author: GuanBin
 * @date: Created in 下午7:31 2021/2/3
 */
public class Jump {

    public static void main(String[] args) throws IOException {
        SSHClient firstHop = new SSHClient();

        firstHop.loadKnownHosts();

        firstHop.connect("192.168.86.69");
        try {

            firstHop.authPublickey("root");

            DirectConnection tunnel = firstHop.newDirectConnection("192.168.86.69", 22);

            SSHClient ssh = new SSHClient();
            try {
                ssh.loadKnownHosts();
                ssh.connectVia(tunnel);
                ssh.authPublickey("root");

                final Session session = ssh.startSession();
                try {
                    final Session.Command cmd = session.exec("ping -c 1 google.com");
                    System.out.println(IOUtils.readFully(cmd.getInputStream()).toString());
                    cmd.join(5, TimeUnit.SECONDS);
                    System.out.println("\n** exit status: " + cmd.getExitStatus());
                } finally {
                    session.close();
                }
            } finally {
                ssh.disconnect();
            }
        } finally {
            firstHop.disconnect();
        }
    }
}
