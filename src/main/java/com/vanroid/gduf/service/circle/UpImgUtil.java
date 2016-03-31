package com.vanroid.gduf.service.circle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vanroid.gduf.entity.Circle;
import com.vanroid.gduf.entity.ImagePath;

public class UpImgUtil {
public static  List<ImagePath> imghanlder(Circle circle,List<File> files,String path) throws IOException{

    List<ImagePath> list=new ArrayList<ImagePath>();
    File dir=new File(path);
    if(!dir.exists())
    	dir.mkdir();
	if (files != null && files.size() > 0) {
        for (int i = 0; i < files.size(); i++) {
        	String fileName =new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
        	ImagePath ip=new ImagePath();
            FileOutputStream fos = new FileOutputStream(path+"\\"+fileName+".jpg");
            FileInputStream fis = new FileInputStream(files.get(i));
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fis.close();
            fos.close();
            ip.setPath(fileName+".jpg");
            ip.setTid(circle);
            list.add(ip);
        }
    }
	return list;
}
}
