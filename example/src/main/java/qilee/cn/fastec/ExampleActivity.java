package qilee.cn.fastec;

import com.qile.activities.ProxyActivity;
import com.qile.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootLatteDelegate() {
        return new ExampleDelegate();
    }
}
