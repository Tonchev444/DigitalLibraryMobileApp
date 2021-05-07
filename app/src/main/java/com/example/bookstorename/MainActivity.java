package com.example.bookstorename;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, author, price;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        author = findViewById(R.id.author);
        price = findViewById(R.id.price);

        insert = findViewById(R.id.buttonInsert);
        update = findViewById(R.id.buttonUpdate);
        delete = findViewById(R.id.buttonDelete);
        view = findViewById(R.id.buttonView);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String authorTXT = author.getText().toString();
                String priceTXT = price.getText().toString();

                Boolean checkinsertdata = DB.insertbookdata(nameTXT, authorTXT, priceTXT);
                if(checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "New entry added to collection", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Problem with inserting entry", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String authorTXT = author.getText().toString();
                String priceTXT = price.getText().toString();

                Boolean checkupdatedata = DB.updatebookdata(nameTXT, authorTXT, priceTXT);
                if(checkupdatedata==true){
                    Toast.makeText(MainActivity.this, "Book Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Problem with updating book", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                Boolean checkdeletedata = DB.deletebookdata(nameTXT);
                if(checkdeletedata==true){
                    Toast.makeText(MainActivity.this, "Book Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Problem with deleting book", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Cursor res = DB.getbooks();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "The library is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name :"+ res.getString(0) +"\n");
                    buffer.append("Author :"+ res.getString(1) +"\n");
                    buffer.append("Price :"+ res.getString(2) +"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Book Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


    }
}