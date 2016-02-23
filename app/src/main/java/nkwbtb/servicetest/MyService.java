package nkwbtb.servicetest;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import java.util.List;

public class MyService extends Service {
    public MyService() {
    }

    public String getForeground() {
        ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        //获取到当前所有进程
        List<ActivityManager.RunningAppProcessInfo> processInfoList = activityManager.getRunningAppProcesses();
        if (processInfoList ==null || processInfoList.isEmpty()) {
            return "";
        }
        //遍历进程列表，找到第一个前台进程
        for (ActivityManager.RunningAppProcessInfo processInfo : processInfoList) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return processInfo.processName;
            }
        }
        return "";
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }
    private Intent intent2;
    @Override
    public void onCreate()
    {
        super.onCreate();
        intent2 = new Intent(Intent.ACTION_MAIN);
        intent2.addCategory(Intent.CATEGORY_LAUNCHER);
        intent2.setClass(this, MainActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        System.out.println("Service Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        String s1 = getPackageName();
        String s2 = getForeground();

        if (!s1.equals(s2))
            startActivity(intent2);
        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        System.out.println("Destroyed");
    }

}
