package sbrt.preppy.lesson_17.downloader;


import java.io.*;
import java.net.URL;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Скачивание файлов по ссылкам.
 * Метод downloadFile принимает URL ссылки и путь для сохранения файла.
 */
public class LinkDownloader {
    private final int downloadSpeedLimit;

    public LinkDownloader(int speedLimit) {
        this.downloadSpeedLimit = speedLimit;
    }

    public void downloadFileWithLimit(String url, String localFolderPath, WebClient webClient) {
        try {
            URL fileUrl = new URL(url);
            Mono<byte[]> result = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(byte[].class);

            byte[] fileData = result.block();
            if (fileData != null) {
                int fileSize = fileData.length;
                long startTime = System.currentTimeMillis();
                try (OutputStream outputStream = new FileOutputStream(localFolderPath + "/" + getFileName(url))) {
                    System.out.println(localFolderPath + "/" + getFileName(url));
                    int bytesRead;
                    int totalRead = 0;
                    long elapsedTime;
                    do {
                        bytesRead = Math.min(downloadSpeedLimit * 1024, fileSize - totalRead);
                        outputStream.write(fileData, totalRead, bytesRead);
                        totalRead += bytesRead;

                        elapsedTime = System.currentTimeMillis() - startTime;
                        if (elapsedTime < 1000) {
                            Thread.sleep(1000 - elapsedTime);
                        }
                        startTime = System.currentTimeMillis();
                    } while (totalRead < fileSize);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getFileName(String url) {
        String[] parts = url.split("/");
        return parts[parts.length - 1];
    }
}
