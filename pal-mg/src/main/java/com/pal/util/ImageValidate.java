package com.pal.util;

import com.google.common.collect.ImmutableList;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

/**
 * User: Alan
 * Email: houlong.yang@hialan.com
 * Date: 10:21 1/7/16
 */
public class ImageValidate {
    public static boolean checkExtension(String filename, List<String> extCol) {
        String name = filename;
        name = name.toLowerCase();
        return FilenameUtils.isExtension(name, extCol);
    }

    /**
     * 检查图片文件是否大于指定的大小
     * @param bytes 文件数据
     * @param size 指定的大小,单位MB
     * @return 如果大于指定的大小返回true,否则false
     */
    public static boolean checkImgSize(byte[] bytes, long size) {
        if (ArrayUtils.isEmpty(bytes))
            throw new IllegalArgumentException("bytes could not be null");

        long len = bytes.length;
        if(len > 1024 * 1024 * size) {
            return true;
        }
        return false;
    }
    public static void validImage(byte[] bytes,String fileName) throws Exception {
        //验证图片格式
        if (!ImageValidate.checkExtension(fileName, ImmutableList.of("png", "jpg", "jpeg")))
            throw new Exception("图片格式不支持,仅支持png、jpg、jpeg格式图片");

        //验证图片大小
        if (ImageValidate.checkImgSize(bytes, 1)) {
            String msg = String.format("您上传的图片大小为%.1fMB,超过1MB限制",
                    (double)bytes.length/1024/1024);
            throw new Exception(msg);
        }
    }
}
