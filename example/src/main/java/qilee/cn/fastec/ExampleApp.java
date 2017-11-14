package qilee.cn.fastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;

import com.qile.app.Latte;
import ec.icon.IconEcModule;

/**
 * Created by qilee on 2017/11/6.
 */

public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withIcon(new IconEcModule())
                .configure();
    }
}
