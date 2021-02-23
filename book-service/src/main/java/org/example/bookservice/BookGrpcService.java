package org.example.bookservice;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.BookFilter;
import org.example.grpc.BookResponse;
import org.example.grpc.BookServiceGrpc;
import org.example.grpc.GetBookRequest;

import java.math.BigDecimal;

@GrpcService
@RequiredArgsConstructor
public class BookGrpcService extends BookServiceGrpc.BookServiceImplBase{

    private final BookService bookService;
    private final BookToBookResponseMapper toBookResponseMapper;

    @Override
    public void getBook(GetBookRequest request, StreamObserver<BookResponse> responseObserver) {
        var book = bookService.getById(request.getId());
        var response = toBookResponseMapper.map(book);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void searchBook(BookFilter request,
                           StreamObserver<BookResponse> responseObserver) {
        var costFrom = BigDecimal.valueOf(request.getCostFrom());
        var costTo = BigDecimal.valueOf(request.getCostTo());
        var bookResponses = toBookResponseMapper.map(bookService.getByCostRange(costFrom, costTo));
        bookResponses.forEach(book -> {
            try {
                Thread.sleep(1000);
                responseObserver.onNext(book);
            } catch (InterruptedException e) {
                throw new RuntimeException("Error when sleep on thread", e);
            }
        });
        responseObserver.onCompleted();
    }
}
