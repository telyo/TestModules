package uiautomator.telyo.com.myapplication.download;

/**
 * Author Administrator
 * date: 2018/1/27.
 * describe:
 */

public interface DownLoadListener {
    void onProgress(int progress);
    void onSuccess();
    void onFiled();
    void onPaused();
    void onCanceled();
}
