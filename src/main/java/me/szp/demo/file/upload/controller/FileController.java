package me.szp.demo.file.upload.controller;

import lombok.extern.slf4j.Slf4j;
import me.szp.demo.file.common.exception.BizException;
import me.szp.demo.file.common.model.Result;
import me.szp.demo.file.upload.dto.FileDownloadRequestDTO;
import me.szp.demo.file.upload.dto.FileUploadDTO;
import me.szp.demo.file.upload.dto.FileUploadRequestDTO;
import me.szp.demo.file.upload.service.FileService;
import me.szp.demo.file.upload.util.FileUtil;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping(value = "/")
@Slf4j
public class FileController {


    @Autowired
    private FileService fileService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


    @GetMapping(value = "/")
    public String gotoPage() {
        return "index";
    }

    @GetMapping(value = "/uploadFile")
    public String gotoFilePage() {
        return "upload";
    }

    @GetMapping(value = "/oss/upload")
    public String gotoOssPage() {
        return "ossUpload";
    }


    @PostMapping(value = "/upload")
    @ResponseBody
    public Result<FileUploadDTO> upload(FileUploadRequestDTO fileUploadRequestDTO) throws IOException {

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        FileUploadDTO fileUploadDTO = null;
        if (isMultipart) {

            StopWatch stopWatch = new StopWatch();
            stopWatch.start("upload");
            if (fileUploadRequestDTO.getChunk() != null && fileUploadRequestDTO.getChunks() > 0) {
                fileUploadDTO = fileService.sliceUpload(fileUploadRequestDTO);
            } else {
                fileUploadDTO = fileService.upload(fileUploadRequestDTO);
            }
            stopWatch.stop();
            log.info("{}", stopWatch.prettyPrint());

            return new Result<FileUploadDTO>().setData(fileUploadDTO);
        }

        throw new BizException("上传失败", 406);

    }

    @RequestMapping(value = "checkFileMd5", method = RequestMethod.POST)
    @ResponseBody
    public Result<FileUploadDTO> checkFileMd5(String md5, String path) throws IOException {

        FileUploadRequestDTO param = new FileUploadRequestDTO().setPath(path).setMd5(md5);
        FileUploadDTO fileUploadDTO = fileService.checkFileMd5(param);

        return new Result<FileUploadDTO>().setData(fileUploadDTO);
    }

    @PostMapping("/download")
    public void download(FileDownloadRequestDTO requestDTO) {

        try {
            FileUtil.downloadFile(requestDTO.getName(), requestDTO.getPath(), request, response);
        } catch (FileNotFoundException e) {
            log.error("download error:" + e.getMessage(), e);
            throw new BizException("文件下载失败", 406);
        }
    }


}
