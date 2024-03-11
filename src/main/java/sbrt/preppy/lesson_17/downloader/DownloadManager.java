package sbrt.preppy.lesson_17.downloader;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.concurrent.CompletableFuture;


/**
 *  Управляет скачиванием нескольких файлов одновременно.
 *  Используется ExecutorService для выполнения задач в отдельных потоках
 *  с ограничением количества параллельных потоков.
 */
@Component
public class DownloadManager {
    public void downloadFiles(List<String> urls, String localFolderPath, int maxThreads, int speedLimit) {
        WebClient webClient = WebClient.create();
        Executor executor = Executors.newFixedThreadPool(maxThreads);
        LinkDownloader link = new LinkDownloader(speedLimit);

        urls.forEach(url -> CompletableFuture.runAsync(() -> link.downloadFileWithLimit(url, localFolderPath, webClient), executor));
    }
}
