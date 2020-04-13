package me.szp.demo.file.upload.service;

import me.szp.demo.file.upload.dto.FileUploadDTO;
import me.szp.demo.file.upload.dto.FileUploadRequestDTO;

import java.io.IOException;

public interface FileService {

    FileUploadDTO upload(FileUploadRequestDTO fileUploadRequestDTO) throws IOException;

    FileUploadDTO sliceUpload(FileUploadRequestDTO fileUploadRequestDTO);

    FileUploadDTO checkFileMd5(FileUploadRequestDTO fileUploadRequestDTO) throws IOException;

}
