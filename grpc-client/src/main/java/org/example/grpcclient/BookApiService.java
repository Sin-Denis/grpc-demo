package org.example.grpcclient;

import com.google.common.util.concurrent.Futures;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.grpc.BookServiceGrpc;
import org.example.grpc.FileRequest;
import org.example.grpc.FileServiceGrpc;
import org.example.grpc.GetBookRequest;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class BookApiService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);
    private final BookResponseToBookDtoMapper toBookDtoMapper;

    private BookServiceGrpc.BookServiceFutureStub bookGrpcService;
    private FileServiceGrpc.FileServiceFutureStub fileGrpcService;

    @GrpcClient("book")
    public void setBookGrpcService(BookServiceGrpc.BookServiceFutureStub bookGrpcService) {
        this.bookGrpcService = bookGrpcService;
    }

    @GrpcClient("file")
    public void setFileGrpcService(FileServiceGrpc.FileServiceFutureStub fileGrpcService) {
        this.fileGrpcService = fileGrpcService;
    }

    public BookDto getBook(Long id) {
        var bookDto = new BookDto();
        var bookRequest = GetBookRequest.newBuilder().setId(id).build();
        var fileRequest = FileRequest.newBuilder().setId(id).build();
        var bookFuture = bookGrpcService.getBook(bookRequest);
        var fileFuture = fileGrpcService.getFile(fileRequest);

        var futures = Futures.allAsList(
                Futures.transform(
                        bookFuture,
                        book -> toBookDtoMapper.map(bookDto, book),
                        executorService),
                Futures.transform(
                        fileFuture,
                        file -> bookDto.setBookContent(file.getResponse().toStringUtf8()),
                        executorService)
        );

        try {
            futures.get();
            return bookDto;
        } catch (Exception e) {
            throw new RuntimeException("Error while get features", e);
        }
    }
}
