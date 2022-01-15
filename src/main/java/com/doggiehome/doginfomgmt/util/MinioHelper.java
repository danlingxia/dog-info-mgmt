package com.doggiehome.doginfomgmt.util;

import io.minio.*;
import io.minio.errors.MinioException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@ConfigurationProperties(prefix = "minio")
public class MinioHelper {
    private String bucket;

    private String host;

    private String url;

    private String accessKey;

    private String secretKey;

    public String upload(MultipartFile multipartFile, String picName)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(host)
                            .credentials(accessKey, secretKey)
                            .build();

            // Make bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build());
            if (!found) {
                // Make a new bucket .
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            } else {
                System.out.println("Bucket " + bucket +" already exists.");
            }

            InputStream inputStream = multipartFile.getInputStream();
            long size = multipartFile.getSize();
            String contentType = multipartFile.getContentType();
            minioClient.putObject(
                    PutObjectArgs.builder().bucket(bucket).object(picName).stream(
                            inputStream, size, -1)
                            .contentType(contentType)
                            .build());

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
        return this.url + UriUtils.encode(picName, StandardCharsets.UTF_8);

    }

}
