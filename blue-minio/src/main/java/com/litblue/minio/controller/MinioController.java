package com.litblue.minio.controller;


import com.litblue.minio.config.MinioTemplate;
import com.litblue.minio.res.MinioReturn;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/minio")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MinioController {

    private final MinioTemplate minioTemplate;

    /**
     * 单文件上传接口
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public MinioReturn upload(MultipartFile file) {
        MinioReturn minioReturn = null;
        try {
            minioReturn = minioTemplate.putFile(file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return minioReturn;
    }


    /**
     * 文件删除接口
     *
     * @param map
     * @return
     */
    @PostMapping("/remove")
    public String remove(@RequestBody Map map) {
        try {
            minioTemplate.removeFile(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

    /**
     * 多文件上传
     *
     * @param files
     * @return
     */
    @PostMapping("/uploadMultiple")
    public List<MinioReturn> uploadMultiple(@RequestParam("files") MultipartFile[] files) {
        //创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<CompletableFuture<MinioReturn>> futures = Arrays.asList(files).stream().map(file -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return minioTemplate.putFile(file);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }, executorService))
                .collect(Collectors.toList());

        //拿到他的返回结果
        List<MinioReturn> results = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());
        //关闭线程池
        executorService.shutdown();
        return results;
    }
}
