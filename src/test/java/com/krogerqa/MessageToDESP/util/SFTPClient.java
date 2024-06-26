package com.krogerqa.MessageToDESP.util;

import com.jcraft.jsch.*;

//connect to SFTP server
public class SFTPClient {

    private Session session = null;
    private ChannelSftp sftpChannel = null;

    //@Value("${private-key-path}")
    private String privateKeyPath;

    public void connect(String source, String destination) throws JSchException, SftpException {
        JSch jsch = new JSch();
        // Uncomment the two lines below if the FTP server requires password
        session = jsch.getSession("KON2208","u060b2biba402", 22);
        session.setPassword("Ranchi032024");
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        sftpChannel = (ChannelSftp) channel;
        sftpChannel.get(source, destination);
        sftpChannel.exit();
    }


    public void download(String source, String destination) throws JSchException, SftpException {
        try {
            System.out.println(source);
            System.out.println(destination);
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.get(source, destination);
            sftpChannel.exit();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void disconnect() {
        if (session != null) {
            session.disconnect();
        }
    }
}