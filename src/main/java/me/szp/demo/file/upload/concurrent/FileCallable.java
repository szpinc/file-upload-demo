package me.szp.demo.file.upload.concurrent;


import me.szp.demo.file.upload.dto.FileUploadDTO;
import me.szp.demo.file.upload.dto.FileUploadRequestDTO;
import me.szp.demo.file.upload.strategy.context.UploadContext;
import me.szp.demo.file.upload.strategy.enu.UploadModeEnum;

import java.util.concurrent.Callable;

public class FileCallable implements Callable<FileUploadDTO> {

    private UploadModeEnum mode;

    private FileUploadRequestDTO param;

    public FileCallable(UploadModeEnum mode,
                        FileUploadRequestDTO param) {

        this.mode = mode;
        this.param = param;
    }

    @Override
    public FileUploadDTO call() throws Exception {

        FileUploadDTO fileUploadDTO = UploadContext.INSTANCE.getInstance(mode).sliceUpload(param);
        return fileUploadDTO;
    }
}
