package sbrt.preppy.lesson_17;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import sbrt.preppy.lesson_17.downloader.DownloadManager;
import sbrt.preppy.lesson_17.lesson8_Spring.proxy.CacheProxy;
import sbrt.preppy.lesson_17.lesson8_Spring.service.Service;
import sbrt.preppy.lesson_17.lesson8_Spring.service.ServiceImpl;


import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableAsync
public class Lesson17Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Lesson17Application.class, args);

        Service service = context.getBean(ServiceImpl.class);
        Service service1 = context.getBean(CacheProxy.class).cache(service, "");

        System.out.println("Next test : Default parameters");

        double r1 = service1.doHardWork("work1", 5); //Запись
        double r2 = service1.doHardWork("work1", 10);  //Запись
        double r3 = service1.doHardWork("work1", 5);  //Кэш

        System.out.println();
        System.out.println("First test : choosing parameters");

        double r4 = service1.doIdentityByWork(new Date(12121211212L), 10); //Запись
        double r5 = service1.doIdentityByWork(new Date(12121211212L), 5);  //Кеш
        double r5_1 = service1.doIdentityByWork(new Date(121212112212L), 5); //Запись


        //Вторая часть задания
        List<String> urls = List.of("https://example.com/file1.txt", "https://example.com/file2.txt", "https://example.com/file3.txt");
        String outputDirectory = "/path/to/directory";
        int numThreads = 3;
        int speedLimitKBps = 500;

    }

}
