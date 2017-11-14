package com.qile.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.qile.app.Latte;

/**
 * Created by qilee on 2017/11/7.
 */

public class DimeUtil {

    public static  int getScreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static  int getScreenHeight(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
