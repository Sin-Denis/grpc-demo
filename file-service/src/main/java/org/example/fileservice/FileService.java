package org.example.fileservice;

import lombok.RequiredArgsConstructor;
import org.example.fileservice.FileRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository repository;

    public byte[] getFile(Long id) {
        return repository.getFile(id);
    }
}
