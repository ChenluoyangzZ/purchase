package com.dingding.purchase.uitls;

import com.dingding.purchase.pojo.ItemsImg;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

public class UploadUtils {
    @SneakyThrows
    public static String uploadFile(String id,String FILEPATH, MultipartFile multipartFile,String file){
    String uploadPathPrefix = File.separator + id;
        if (multipartFile != null) {
        String fileName = multipartFile.getOriginalFilename();
        if (StringUtils.isNotBlank(fileName)) {
            String[] splits = fileName.split("\\.");
            if (!splits[splits.length - 1].equalsIgnoreCase("png")
                    && !splits[splits.length - 1].equalsIgnoreCase("jpg")
                    && !splits[splits.length - 1].equalsIgnoreCase("jpeg")) {
                return RespondResultUtils.errorMsg("传入图片格式不对");
            }
            //文件重组名字
            String newFileName = file+"Img-" + id + "." + splits[splits.length - 1];
            //头像保存地址
            String finalFacePath = FILEPATH + uploadPathPrefix + File.separator + newFileName;
            File outfile = new File(finalFacePath);
            outfile.getParentFile().mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(outfile);
            InputStream inputStream = multipartFile.getInputStream();
            IOUtils.copy(inputStream, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            String itemImg = "http://localhost:8088/"+file+"s/" + id + "/" + newFileName;
            return itemImg;
        }
        }
        return null;
    }
}
