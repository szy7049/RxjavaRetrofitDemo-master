package com.example.retrofit.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.retrofit.R;
import com.example.retrofit.activity.adapter.DownAdapter;
import com.example.retrofit.downlaod.DownInfo;
import com.example.retrofit.downlaod.HttpDownManager;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 多任務下載
 */
public class DownLaodActivity extends AppCompatActivity {
    List<DownInfo> listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_laod);
        initResource();
        initWidget();
    }
   // http://www.izaodao.com/app/izaodao_app.apk
    //http://musicdata.baidu.com/data2/lrc/b1f98ad984e18662e13c93253f41089b/565031735/565031735.lrc
    /*数据*/
    //请求数据
    private void initResource(){
        listData=new ArrayList<>();
        String[] downUrl=new String[]{"http://mirror.aarnet.edu.au/pub/TED-talks/911Mothers_2010W-480p.mp4",
                "http://musicdata.baidu.com/data2/lrc/4ca11ed349ed0c1e6488ad1bd664812b/565001257/565001257.lrc"};
        for (int i = 0; i < downUrl.length; i++) {
            File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "test"+i + "mp4");
            DownInfo apkApi=new DownInfo(downUrl[i]);
            //存储位置
            String absolutePath = outputFile.getAbsolutePath();
            apkApi.setSavePath(outputFile.getAbsolutePath());
            listData.add(apkApi);
        }
    }

    /*加载控件*/
    private void initWidget(){
        EasyRecyclerView recyclerView=(EasyRecyclerView)findViewById(R.id.rv);
        DownAdapter adapter=new DownAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.addAll(listData);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*停止全部下载*/
        HttpDownManager.getInstance().stopAllDown();
    }
}
