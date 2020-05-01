package logics;

import java.io.File;
import java.io.FileInputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Sftp {

    private String host;
    private Integer port;
    private String user;
    private String password;

    private JSch jsch;
    private Session session;
    private Channel channel;
    private ChannelSftp sftpChannel;

    public Sftp(String host, Integer port, String user) {
        this.host = host;
        this.port = port;
        this.user = user;
    }

    public void connect() {

        System.out.println("connecting..." + host);
        try {
            jsch = new JSch();
            session = jsch.getSession(user, host, port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = session.openChannel("sftp");
            channel.connect();
            sftpChannel = (ChannelSftp) channel;

            System.out.println("Connected");

        } catch (JSchException e) {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        System.out.println("disconnecting...");
        sftpChannel.disconnect();
        channel.disconnect();
        session.disconnect();
    }

    public void upload(String fileName, String remoteDir) {
        System.out.println("Uploading...");

        FileInputStream fis = null;
        connect();
        try {
            // Change to output directory
            sftpChannel.cd(remoteDir);

            // Upload file
            File file = new File(fileName);
            fis = new FileInputStream(file);
            sftpChannel.put(fis, file.getName());

            fis.close();
            System.out.println("File uploaded successfully - " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
        disconnect();
    }

}