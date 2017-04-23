package com.example.administrator.open_source_in_china_liwenxi.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Process;

import java.io.InputStream;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class CircleUtils {
    private int i;

    public static final void exit(){
        //获取pid
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    public static Bitmap getCircleImg(Bitmap bit){
        //创建一个空的Bitmap
        Bitmap newBit = Bitmap.createBitmap(bit.getWidth(),bit.getHeight(), Bitmap.Config.ARGB_4444);
        //创建画布
        Canvas can = new Canvas(newBit);
        //创建画笔
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        int i =  bit.getWidth() / 2 > bit.getHeight() / 2 ? bit.getHeight() / 2 : bit.getWidth() / 2;
        can.drawCircle(bit.getWidth()/2,bit.getHeight()/2,i,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Rect rec = new Rect(0,0,bit.getWidth(),bit.getHeight());
        can.drawBitmap(bit,rec,rec,paint);



        return newBit;
    }
    public static Bitmap getCircleImg(InputStream is){

        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return getCircleImg(bitmap);
    }

}
