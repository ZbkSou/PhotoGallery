package com.example.bkzhou.photogallery;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Created by bkzhou on 15-10-22.
 */
public class FlickrFetchr {
    public static final String TAG = "PhotoFetcher";

    private static final String ENDPOINT = "http://image.baidu.com/channel/listjson?pn=0&rn=30&tag1=%E7%BE%8E%E5%A5%B3&tag2=%E5%85%A8%E9%83%A8&ftags=%E5%B0%8F%E6%B8%85%E6%96%B0&ie=utf8";

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public GalleryItem fetchItems()  {
        GalleryItem items = new GalleryItem();
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(ENDPOINT);
            HttpResponse response = client.execute(get);
            int code = response.getStatusLine().getStatusCode();//返回响应码
            InputStream in = response.getEntity().getContent();//服务器返回的数据
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            StringBuffer result = new StringBuffer();

            String line = "";

            while((line = reader.readLine()) != null){

                result.append(line);

            }

            reader.close();
           Log.d(TAG,result.toString());
            Gson gson = new Gson();
            items = gson.fromJson(result.toString(), GalleryItem.class);


        }catch (Exception e){
            Log.d(TAG, e.toString());
        }

        return items;
    }




}
