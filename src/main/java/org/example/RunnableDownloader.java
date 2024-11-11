package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class RunnableDownloader implements Runnable{
    String link;
    int numberFile;

    public RunnableDownloader(String url, int num){
    this.link=url;
    this.numberFile=num;
    }

    @Override
    public void run() {
       /* BufferedImage img = null;
        try {
            URI uri = new URI(url);
            img = ImageIO.read(uri.toURL());

            String fileName = "animal"+numberFile+".jpg";

            File file = new File("src/main/resources/results/"+fileName);
                ImageIO.write(img, "jpg", file);

        } catch (IOException e) {
            System.out.println(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            URI uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        */

        URL url = null;
        try {
            url = new URL(link);
            InputStream in = url.openStream();
            File outputFile = new File("animal" + numberFile + ".jpg");

            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
