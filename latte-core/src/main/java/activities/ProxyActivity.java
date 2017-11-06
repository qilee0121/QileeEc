package activities;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import delegates.LatteDelegate;
import me.yokeyword.fragmentation.SupportActivity;
import qilee.cn.latte.R;

/**
 * Created by qilee on 2017/11/6.
 */

public abstract class ProxyActivity extends SupportActivity{

    public abstract LatteDelegate setRootLatteDelegate();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootLatteDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
