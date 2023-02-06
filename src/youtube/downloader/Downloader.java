package youtube.downloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
public class Downloader {
        public static void main(String[] args) {
            try {
                String parseLine; /* variable definition */
                /* create objects */
                Scanner myObj = new Scanner(System.in);
                
                URL URL = new URL(myObj.nextLine());
                BufferedReader br = new BufferedReader(new InputStreamReader(URL.openStream()));

                while ((parseLine = br.readLine()) != null) {
                    /* read each line */
                    System.out.println(parseLine);
                }
                br.close();

            } catch (MalformedURLException me) {
                System.out.println(me);

            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        }//class end
}
