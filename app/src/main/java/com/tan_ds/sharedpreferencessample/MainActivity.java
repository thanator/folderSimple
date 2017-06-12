package com.tan_ds.sharedpreferencessample;

import android.content.Intent;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity  {
    private final static String FOLDER_NAME = "name";
    private String mName;

    private FoldersAdapter mFolderAdapter;
    private ListView mListView;
    private File mFile;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!mFile.toString().equals("/system")){
            Intent intent = getIntent();
            intent.putExtra(FOLDER_NAME, mFile.getParent());
            finish();
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folders);

        Intent intent = getIntent();
        mName = intent.getStringExtra(FOLDER_NAME);
        if (mName != null)
            mFile = new File(mName);
        else
            mFile = Environment.getRootDirectory();


        mListView = (ListView) findViewById(R.id.list_view_folder);

        File[] files = mFile.listFiles();
        mFolderAdapter = new FoldersAdapter(files);
        mListView.setAdapter(mFolderAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                File itemFile = (File) parent.getItemAtPosition(position);
                if (itemFile.isFile())
                    Toast.makeText(getApplicationContext(), "This is file", Toast.LENGTH_SHORT).show();
                else if (itemFile.isDirectory()){
                    Intent intent = getIntent();
                    intent.putExtra(FOLDER_NAME, parent.getItemAtPosition(position).toString());
                    finish();
                    startActivity(intent);
                }else
                    Toast.makeText(getApplicationContext(), "Dunno wtf is this", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
