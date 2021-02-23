package org.example.fileservice;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class FileRepository {

    private final ConcurrentMap<Long, String> fileStorage = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        fileStorage.put(1L, "books/Effective Java");
        fileStorage.put(2L, "books/Жизнь без правил");
        fileStorage.put(3L, "books/ЛеБрон, INC");
        fileStorage.put(4L, "books/Беспощадная истина");
        fileStorage.put(5L, "books/Исчезнувшая");
    }

    public byte[] getFile(Long id) {
        try {
            return getFile(fileStorage.get(id))
                    .readAllBytes();
        } catch (Exception e) {
            throw new RuntimeException("Error while get file", e);
        }
    }

    private InputStream getFile(String path) {
        return Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(path);
    }
}
