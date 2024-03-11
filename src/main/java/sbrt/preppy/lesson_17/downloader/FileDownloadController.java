package sbrt.preppy.lesson_17.downloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sbrt.preppy.lesson_17.downloader.DownloadManager;

import java.util.List;

/**
 * Для Spring Boot приложения создать контроллер,
 * который будет обрабатывать запросы и вызывать методы класса
 * DownloadManager для скачивания файлов.
 */

@RestController
public class FileDownloadController {
    private final DownloadManager downloadManager;

    @Autowired
    public FileDownloadController(DownloadManager downloadManager) {
        this.downloadManager = downloadManager;
    }

    @PostMapping("/download")
    public String downloadFiles(@RequestBody List<String> urls, @RequestParam String folderPath,
                              @RequestParam int maxThreads, @RequestParam int speedLimit) {
        downloadManager.downloadFiles(urls,folderPath, maxThreads, speedLimit);
        return "Files downloaded successfully";
    }
}
