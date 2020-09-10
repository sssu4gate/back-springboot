package com.gate.planner.gate.service.post;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.gate.planner.gate.exception.post.InvalidFileTypeException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class S3Function {

    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public List<String> StoreImgToS3(MultipartFile[] files) throws IOException {
        List<String> imgUrlList = new ArrayList<String>();

        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getOriginalFilename();
            String ext = FilenameUtils.getExtension(fileName);
            byte[] bytes = IOUtils.toByteArray(files[i].getInputStream());
            ObjectMetadata metadata = new ObjectMetadata();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

            metadata.setContentLength(bytes.length);
            metadata.setContentType("image/jpeg");

            fileName = UUID.randomUUID().toString() + fileName;

            System.out.println(ext);

            if (!"jpeg".equals(ext) && !"jpg".equals(ext) && !"png".equals(ext))
                throw new InvalidFileTypeException();

            s3Client.putObject(new PutObjectRequest(bucketName, fileName, byteArrayInputStream, metadata));
            imgUrlList.add(s3Client.getUrl(bucketName, fileName).toString());
        }
        return imgUrlList;
    }
}
