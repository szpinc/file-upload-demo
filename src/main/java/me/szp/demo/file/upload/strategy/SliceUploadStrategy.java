package me.szp.demo.file.upload.strategy;


import me.szp.demo.file.upload.dto.FileUploadDTO;
import me.szp.demo.file.upload.dto.FileUploadRequestDTO;

public interface SliceUploadStrategy {

  FileUploadDTO sliceUpload(FileUploadRequestDTO param);
}
