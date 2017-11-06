package qilee.cn.fastec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import activities.ProxyActivity;
import delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootLatteDelegate() {
        return new ExampleDelegate();
    }
}
