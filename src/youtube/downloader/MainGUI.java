package youtube.downloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
//import java.awt.event.ActionListener;

public class MainGUI {

    private  JFrame jFrame;
    private JLabel textBoxLabel;

    private JTextField downloadUrlField;

    private JPanel jPanelOne;

    private JProgressBar downloadProgress;



    MainGUI(){
        loadGUIComponents();
    }
    public void loadGUIComponents(){
        jFrame = new JFrame("Youtube downloader");
        jFrame.setSize(500,600);

        textBoxLabel = new JLabel("PLease paste your youtube url below:");
        downloadUrlField = new JTextField();
        JButton downloadBtm = new JButton("Download");
        downloadBtm.addActionListener((ActionEvent e) ->{
            String userText = downloadUrlField.getText();
            startDownload(userText);
            JOptionPane.showMessageDialog(jFrame,"User input : "+userText);
        });

        downloadProgress = new JProgressBar();
        downloadProgress.setMaximum(100);
        downloadProgress.setMinimum(1);
        downloadProgress.setValue(10);
        jPanelOne = new JPanel();
        jPanelOne.add(textBoxLabel);
        jPanelOne.add(downloadUrlField);
        jPanelOne.setLayout(new GridLayout(1,1));

        jFrame.add(jPanelOne);
        jFrame.add(downloadBtm);
        jFrame.add(downloadProgress);

        jFrame.setLayout(new GridLayout(4,1));

        jFrame.setVisible(true);

    }

    public static void startDownload(String downloadUrl) {
        // String a = "abcd";
        // String b = "abcd"; b = "cde"
        // url : https://somewebsite.com/downlaods/idm.exe .substring(0,4)
        StringBuffer userHome = new StringBuffer(System.getProperty("user.home"));
        StringBuffer fileSeparator = new StringBuffer(System.getProperty("file.separator"));
        StringBuffer downloadPath = userHome.append(fileSeparator).append("Downloads").append(fileSeparator);
        downloadPath.append(downloadUrl.substring(downloadUrl.lastIndexOf("/")+1));

        URL url;
        try {
            url = new URL(downloadUrl);
            getFileTotalSize(url);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(downloadPath.toString());
            byte[] b = new byte[1024];
            int count;

            while((count= bufferedInputStream.read(b,0,1024))!=-1){
                fileOutputStream.write(b,0,count);
            }
        }catch(MalformedURLException malformedURLException){
            System.out.println("The url provided : "+downloadUrl+ " is invalid");
        }catch (IOException ioException){
            System.out.println("The resource is not found");
        }
    }

    private static void getFileTotalSize(URL url){
        HttpURLConnection httpURLConnection;

        try{
            httpURLConnection = (HttpURLConnection) url.openConnection();
            long length = httpURLConnection.getContentLengthLong();
            return getSize(length,expectedOutput);
            System.out.println("The size of file is "+ length/(1024*1024) + "mb.");
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private float getSize(long length, String expectedOutput){
        switch (expectedOutput){

            case "mb":
                System.out.println("The size of file is "+ length/(1024*1024) + "mb.");
                break;
            case "gb":
                break;
            default:
                break;
        }
    }

    //link ="https://az764295.vo.msecnd.net/stable/e2816fe719a4026ffa1ee0189dc89bdfdbafb164/VSCodeUserSetup-x64-1.75.0.exe";
    public static void main(String[] args) {
        String downloadUrl =  "https://az764295.vo.msecnd.net/stable/e2816fe719a4026ffa1ee0189dc89bdfdbafb164/VSCodeUserSetup-x64-1.75.0.exe";
        //PrintAllProperties();
               MainGUI mainGUI = new MainGUI();
               getFileTotalSize(new URL(downloadUrl()));
        //startDownload(downloadUrl);

    }

    private static String downloadUrl() {
    }

    private static void PrintAllProperties() {
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("file.separator"));
        System.out.println(System.getenv("OS"));
        System.out.println(System.getenv("NUMBER_OF_PROCESSORS"));
        Properties properties = System.getProperties();
        Enumeration<?> enumeration = properties.propertyNames();
        while(enumeration.hasMoreElements()){
            String property = enumeration.nextElement().toString();
            System.out.println(property +" "+System.getProperty(property));
        }
    }
}