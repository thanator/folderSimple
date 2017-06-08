package com.tan_ds.sharedpreferencessample;

import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.util.List;

import static android.os.Environment.getExternalStorageDirectory;

public class MainActivity extends AppCompatActivity {

    private static final String PLAIN_TEXT = "plain_text";
    private static final String NUMBER_TEXT = "number_text";
    private static final String CHECKBOX_FLAG = "ckeckbox_flag";

    private EditText mPlainEditText, mNumberEditText;
    private CheckBox mCheckBox;

    private FoldersAdapter mFolderAdapter;
    private ListView mListView;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        setContentView(R.layout.activity_main);
        mPlainEditText = (EditText) findViewById(R.id.plain_edit_text);
        mNumberEditText = (EditText) findViewById(R.id.number_edit_text);
        mCheckBox = (CheckBox) findViewById(R.id.check_box);

        File[] listFiles = Environment.getRootDirectory().listFiles();

        mListView = (ListView) findViewById(R.id.list_view);
        mFolderAdapter = new FoldersAdapter(listFiles);
        mListView.setAdapter(mFolderAdapter);

    }

    private void readValuesFromRefs(){
        mPlainEditText.setText(mSharedPreferences.getString(PLAIN_TEXT, "MAMA MILA RAMU"));
        mNumberEditText.setText(String.valueOf(mSharedPreferences.getFloat(NUMBER_TEXT, 0)));
        mCheckBox.setChecked(mSharedPreferences.getBoolean(CHECKBOX_FLAG, false));
    }

    private void writeValuesToPref() {
        String plainText = mPlainEditText.getText().toString();
        Double number = 0.0;
        String numberText = mNumberEditText.getText().toString();
        if (!TextUtils.isEmpty(numberText)){
            number = Double.parseDouble(numberText);
        }
        String temp = number.toString();
        mSharedPreferences.edit().putString(PLAIN_TEXT, plainText)
                .putFloat(NUMBER_TEXT, Float.parseFloat(temp))
                .putBoolean(CHECKBOX_FLAG, mCheckBox.isChecked())
                .apply();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        readValuesFromRefs();
    }

    @Override
    protected void onPause() {
        super.onPause();
        writeValuesToPref();
    }
}
