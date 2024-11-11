package org.example;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final int NUM_THREADS = 10;
    public static final String ADRESS = "src/main/resources/animal-urls.txt";

    public static void main(String[] args) {
            List<String> links = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(ADRESS))) {
                String line = null;
                while ((line = br.readLine()) != null) {
                    links.add(line.trim());
                }

                int numberFile = 1;
                ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
                for (String s: links){
                    Runnable r = new RunnableDownloader(s, numberFile);
                    pool.execute(r);
                    numberFile++;
                }

                pool.shutdown();

            } catch (ProtocolException e) {
                throw new RuntimeException(e);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}