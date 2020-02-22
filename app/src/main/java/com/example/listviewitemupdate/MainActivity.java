package com.example.listviewitemupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ImageButton addButton;
    ListView listView;
    ArrayAdapter<String>arrayAdapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listViewId);
        addButton=findViewById(R.id.addButtonId);

        String[] item = {"Dhaka","Faridpur","Rajshahi","Comilla","Khulna"};
        arrayList=new ArrayList(Arrays.asList(item));

        arrayAdapter=new ArrayAdapter(MainActivity.this,R.layout.sample,R.id.listItemId,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                UpdateItem(arrayList.get(position),position);

            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(MainActivity.this, "Add Clicked", Toast.LENGTH_SHORT).show();
                AddItem();

            }
        });

    }
    public void UpdateItem(String oldItem,final int index){
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.input_box);
        TextView textMessage=dialog.findViewById(R.id.textMessageId);
        textMessage.setText("Update Item");
        textMessage.setTextColor(Color.parseColor("#ff2222"));
        final EditText editText=dialog.findViewById(R.id.txtinputBoxId);
        editText.setText(oldItem);
        Button doneButton=dialog.findViewById(R.id.doneButtonId);
        Button cancelButton=dialog.findViewById(R.id.cancelButtonId);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.set(index,editText.getText().toString());
                arrayAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void AddItem(){
        final Dialog dialog=new Dialog(MainActivity.this);
        dialog.setTitle("Input Box");
        dialog.setContentView(R.layout.input_box);
        TextView textMessage=dialog.findViewById(R.id.textMessageId);
        textMessage.setText("Add Item");
        textMessage.setTextColor(Color.parseColor("#ff2222"));
        final EditText editText=dialog.findViewById(R.id.txtinputBoxId);
        editText.setText("");
        Button doneButton=dialog.findViewById(R.id.doneButtonId);
        Button cancelButton=dialog.findViewById(R.id.cancelButtonId);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equalsIgnoreCase("")) {
                    editText.setError("This field can not be blank");
                }
                else {
                    arrayList.add(editText.getText().toString());
                }

                arrayAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();

    }



}
