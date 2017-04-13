package cn.meituan.jp;

import cn.meituan.jp.utils.PhoneManager;

/**
 * Created by 11608 on 2017/4/13.
 */

public class Constant {

    //主机地址
    public static final String REQUEST_HOST = "";
    public static final String APP_DIR = PhoneManager.getSdCardRootPath() + "/FlyLeopard/";//app文件目录
    public static final String IMAGE_CACHE_DIR_PATH = APP_DIR + "cache/";// 图片缓存地址
    public static final String UPLOAD_FILES_DIR_PATH = APP_DIR + "upload/";//上传文件、零时文件存放地址
    public static final String DOWNLOAD_DIR_PATH = APP_DIR + "downloads/";// 下载文件存放地址
}
