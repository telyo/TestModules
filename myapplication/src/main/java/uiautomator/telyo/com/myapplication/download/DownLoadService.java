package uiautomator.telyo.com.myapplication.download;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import java.io.File;

import uiautomator.telyo.com.myapplication.MainActivity;
import uiautomator.telyo.com.myapplication.R;

/**
 * Author Administrator
 * date: 2018/1/27.
 * describe:
 */

public class DownLoadService extends Service {
    private DownLoadTask downLoadTask;

    private String downLoadUrl;
    private DownLoadListener loadListener = new DownLoadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("下载中..." ,progress));
        }

        @Override
        public void onSuccess() {
            downLoadTask = null;
            //关闭通知
            stopForeground(true);
            //创建一个下载成功的通知
            getNotificationManager().notify(1,getNotification("下载成功!",-1));
        }

        @Override
        public void onFiled() {
            downLoadTask = null;
            //关闭通知
            stopForeground(true);
            //创建一个下载成功的通知
            getNotificationManager().notify(1,getNotification("下载失败!",-1));
        }

        @Override
        public void onPaused() {
            downLoadTask = null;
        }

        @Override
        public void onCanceled() {
            downLoadTask = null;
            stopForeground(true);
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        return new DownLoadBinder();
    }


    class DownLoadBinder extends Binder {
        public void startDownLoad(String url){
            if (downLoadTask == null){
                downLoadUrl = url;
                downLoadTask = new DownLoadTask(loadListener);
                downLoadTask.execute(downLoadUrl);
                startForeground(1,getNotification("下载中....",0));
            }
        }
        public void pausedDownload(){
            if (downLoadTask != null){
                downLoadTask.pausedDownLoad();
            }
        }

        public void canceledDownload(){
            if (downLoadTask != null){
                downLoadTask.canceledDownLoad();
            }else {
                if (downLoadUrl != null){
                    //删除文件 并通知关闭
                    String fileName = downLoadUrl.substring(downLoadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file != null){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                }
            }
        }

    }


    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
        builder.setContentTitle(title);
        builder.setContentIntent(pi);
        if (progress > 0){
            builder.setContentText( progress + "%");
            builder.setProgress(100,progress,false);
        }

        return builder.build();

    }

    public NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
}
