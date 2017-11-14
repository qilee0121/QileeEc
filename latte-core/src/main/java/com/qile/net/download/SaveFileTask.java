package com.qile.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.qile.app.Latte;
import com.qile.net.callback.IRequest;
import com.qile.net.callback.ISuccess;
import com.qile.util.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by qilee on 2017/11/14.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {
        String dir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody responseBody = (ResponseBody) params[2];
        String name = (String) params[3];

        final InputStream is = responseBody.byteStream();
        if (dir == null || dir.equals("")) {
            dir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }
        if (name == null) {
            return FileUtil.writeToDisk(is, dir, extension.toUpperCase(), extension);
        } else {
            return FileUtil.writeToDisk(is, dir, name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null){
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null){
            REQUEST.onRequestEnd();
        }
        atuoInstallAPK(file);
    }

    private void atuoInstallAPK(File file){
        if (FileUtil.getExtension(file.getPath()).equals("apk")){
            Intent install = new Intent();
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplication().startActivity(install);
        }
    }
}
