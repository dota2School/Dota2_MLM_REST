package org.dota2school.mlm.controller;

import org.apache.commons.codec.digest.DigestUtils;
import org.dota2school.mlm.frame.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author xujq
 */
@RestController
@RequestMapping("/file")
public class UploadController {
    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);


    @Autowired
    private AppConfig config;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String testUploadFile(HttpServletRequest req,
                               MultipartHttpServletRequest multiReq) {

        String session = req.getParameter("session");
        if(session == null) session = "0";
        System.out.println(session);
        // 获取上传文件的路径
        String uploadFilePath = multiReq.getFile("filePath").getOriginalFilename();
        System.out.println("uploadFlePath:" + uploadFilePath);
        // 截取上传文件的文件名
        String uploadFileName = uploadFilePath.substring(
                uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
        System.out.println("multiReq.getFile()" + uploadFileName);
        // 截取上传文件的后缀
        String uploadFileSuffix = uploadFilePath.substring(
                uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
        System.out.println("uploadFileSuffix:" + uploadFileSuffix);
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fis = (FileInputStream) multiReq.getFile("filePath").getInputStream();
            String fileName = DigestUtils.md5Hex(uploadFileName + "."+ uploadFileSuffix+System.currentTimeMillis());
            File file  = new File(config.getPath()+"/"+session + "/"+fileName+"."+uploadFileSuffix);
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            byte[] temp = new byte[1024];
            int i = fis.read(temp);
            while (i != -1){
                fos.write(temp,0,temp.length);
                fos.flush();
                i = fis.read(temp);
            }
            return "/"+session + "/"+fileName+"."+uploadFileSuffix;
        } catch (IOException e) {
            LOG.info("Failed write file",e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

}
