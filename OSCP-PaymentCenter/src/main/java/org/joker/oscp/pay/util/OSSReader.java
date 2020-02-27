package org.joker.oscp.pay.util;

import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.reflections.Reflections.log;

/**
 * 读取MinIO-OSS工具类
 * @author JOKER
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class OSSReader {

    @Value("${minio.endpoint}")
    private String ENDPONT;
    @Value("${minio.bucketName}")
    private String BUCK_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    /**
     * 上传文件至OSS
     *
     * @param file 文件
     * @return 成功即返回true
     */
    public UtilResult updateFileInOSS(MultipartFile file) {
        try {
            MinioClient minioClient = new MinioClient(ENDPONT, ACCESS_KEY, SECRET_KEY);
            boolean isExist = minioClient.bucketExists(BUCK_NAME);
            if (isExist) {
                log.info("OSS上桶已经存在！！！");
            } else {
                // 创建桶并设置只读权限
                minioClient.makeBucket(BUCK_NAME);
                minioClient.setBucketPolicy(BUCK_NAME, "*.*", PolicyType.READ_ONLY);
            }
            String filename = file.getName();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyMMdd");
            String objectName = simpleDateFormat.format(new Date()) + "/" + filename;
            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(BUCK_NAME, objectName, file.getInputStream(), file.getContentType());
            log.info("文件上传成功！");
            UtilResult result = new UtilResult();
            result.setFlag(true);
            result.setPath(ENDPONT + "/" + BUCK_NAME + "/" + objectName);
            return result;
        } catch (Exception e) {
            log.info("上传发生错误: {}！", e);
            UtilResult result = new UtilResult();
            result.setPath(null);
            result.setFlag(false);
            return result;
        }
    }

    /**
     * 文件类型转换 M->F
     *
     * @param path
     * @param mfile
     * @return
     */
    public File transfromByMultipartFile(String path, MultipartFile mfile) {
        File file = null;
        try {
            file = File.createTempFile(path, null);
            mfile.transferTo(file);
            return file;
        } catch (IOException e) {
            log.info("转换出现错误： {}", e);
            return null;
        }
    }

    /**
     * 文件类型转换 F->M
     *
     * @param file
     * @return
     */
    public MultipartFile tranfromByFile(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);
            return multipartFile;
        } catch (Exception e) {
            log.info("转换出现错误： {}", e);
            return null;
        }
    }

}
