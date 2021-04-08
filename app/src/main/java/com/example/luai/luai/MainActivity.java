package com.example.luai.luai;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    private Button btn;
    private EditText input;
    private ListView list;
    public ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    Spinner spi;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        input = (EditText) findViewById(R.id.input);
        list = (ListView) ((Activity)this).findViewById(R.id.list);
        spi=(Spinner) findViewById(R.id.spain);


        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_list_item_lolo, arrayList);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String names2 =spi.getSelectedItem().toString();
                String names = input.getText().toString();
                arrayList.add(names);
                list.setAdapter(adapter);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, "name  " + arrayList.get(position), Toast.LENGTH_SHORT).show();
                    }


                });

                list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, arrayList.remove(position), Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();

                        return false;
                    }
                });

saveArray();


            }


        });

loadArray();
    }




    public boolean saveArray() {
        SharedPreferences sp  = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor mEdit1 = sp.edit();
        /* sKey is an array */
        mEdit1.putInt("Status_size", arrayList.size());

        for (int i = 0; i < arrayList.size(); i++) {
            mEdit1.putString("Status_" + i, arrayList.get(i));
        }

        return mEdit1.commit();
    }

        public void loadArray()
        {
            SharedPreferences mSharedPreference1 = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            arrayList.clear();
            int size = mSharedPreference1.getInt("Status_size", 0);
            for (int i = 0; i < size; i++) {
                arrayList.add(mSharedPreference1.getString("Status_" + i, null));
            }
        }
    }