package com.zhh.base.toolkit.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by zhanghehe on 2018/8/23.
 * desc:
 */
public class GotoActUtils {

    /**
     * goto system browser
     */
    public static void gotoSystemBrowser(Context context, String webUrl) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(webUrl);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    public static void gotoActivity(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

}
