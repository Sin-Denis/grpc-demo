package org.example.fileservice;

import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.grpc.FileRequest;
import org.example.grpc.FileResponse;
import org.example.grpc.FileServiceGrpc;

@GrpcService
@RequiredArgsConstructor
public class FileGrpcService extends FileServiceGrpc.FileServiceImplBase {

    private final FileService fileService;

    @Override
    public void getFile(FileRequest request,
                        StreamObserver<FileResponse> responseObserver) {
        var file = fileService.getFile(request.getId());
        var response = FileResponse.newBuilder()
                .setResponse(ByteString.copyFrom(file))
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
