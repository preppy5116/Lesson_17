package sbrt.preppy.lesson_17.lesson8_Spring.service;

import cachePackage.Cache;
import cachePackage.CacheType;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Component
public interface Service {
    @Cache()
    double doHardWork(String str, int number);
    @Cache(identityBy = Date.class)
    double doIdentityByWork(Date str, int number);

    @Cache(identityBy = String.class, keyName = "list", listSize = 2)
    List<Integer> doListWork(String str, int number);

    @Cache(identityBy = String.class, keyName = "file", cacheType = CacheType.FILE)
    double doFileWork(String str, int number);

    @Cache(identityBy = String.class, keyName = "zip", cacheType = CacheType.FILE, zip = true)
    double doZipWork(String str, int number);

    @Cache(identityBy = {String.class, Integer.class}, keyName = "My name", cacheType = CacheType.FILE, zip = true)
    double doExtraWork(String str, int number);
}

