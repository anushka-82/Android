package com.anushka.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_notemenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() != R.id.add_note) {

            Intent intent = new Intent(getApplicationContext(), NOtesEditorActivityy.class);

            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listview);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.anushka.notes", Context.MODE_PRIVATE)   ;

        HashSet<String> set= (HashSet<String>) sharedPreferences.getStringSet("notes", null);

       if(set == null){
        notes.add("Example note");}

       else{
           notes = new ArrayList<>(set);
       }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);

        listView.setAdapter(arrayAdapter);




       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           }


           public void onItemClick(AdapterView<?> parent, View view, int position, long id, int i, long l) {
               Intent intent = new Intent(getApplicationContext(), NOtesEditorActivityy.class  );

               intent.putExtra("noteId", i);
               startActivity(intent);
           }
       });

       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               return false;
           }


           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id, int i) {

               final int itemToDelete = i;
               new AlertDialog.Builder(MainActivity.this)
                       .setIcon(android.R.drawable.ic_dialog_alert)
                       .setTitle("Are you sure?")
                       .setMessage("Do you want to delete this?")
                       .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {

                               notes.remove(i);
                               arrayAdapter.notifyDataSetChanged();
                           }
                       })
                       .setNegativeButton("No", null)
                       .show();

               return true;
           }
       });
    }
}