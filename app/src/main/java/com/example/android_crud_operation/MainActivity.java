package com.example.android_crud_operation;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText edit1,edit2,edit3,edit4;
    Button AddData;
    Button viewData;
    Button btnUpdate;
    Button btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         myDb = new DatabaseHelper(this);


         edit1= findViewById(R.id.edit1);
        edit2= findViewById(R.id.edit2);
        edit3= findViewById(R.id.edit3);
        edit4= findViewById(R.id.edit4);
        AddData = findViewById(R.id.AddData);
        viewData=findViewById(R.id.viewData);
        btnUpdate=findViewById(R.id.btnUpdate);
        btn_delete=findViewById(R.id.btn_delete);
        addData();
        viewAllData();
        UpdateData();
        DeleteData();
    }

    public void DeleteData(){
        btn_delete.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Integer deleteRows = myDb.deleteData(edit4.getText().toString());
                        if (deleteRows>0)
                            Toast.makeText(MainActivity.this, "Data Deleated ", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Deleated ", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }



    public void UpdateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate= myDb.updateData(edit4.getText().toString(),edit1.getText().toString(),edit2.getText().toString(),edit3.getText().toString());
                      if(isUpdate == true){
                          Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                      }
                      else {
                          Toast.makeText(MainActivity.this, "Data is not updated", Toast.LENGTH_SHORT).show();
                      }
                    }
                }
        );
    }
    public void addData(){
        AddData.setOnClickListener(
             new View.OnClickListener(){

                 @Override
                 public void onClick(View view) {
                    boolean isInserted = myDb.insertData(edit1.getText().toString(), edit2.getText().toString(),edit3.getText().toString());
                 if (isInserted==true)
                     Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
                 else
                     Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                 }
             }


        );
    }
    public void viewAllData(){
        viewData.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                       Cursor res= myDb.getAllData();
                       if(res.getCount()==0){
                           showMessage("Error","Nothing found");
                           return;
                       }
                       StringBuffer buffer = new StringBuffer();
                       while(res.moveToNext()){
                           buffer.append("ID: " + res.getString(0)+ "\n");
                           buffer.append("Name: " + res.getString(1)+ "\n");
                           buffer.append("Surname: " + res.getString(2)+ "\n");
                           buffer.append("Marks: " + res.getString(3)+ "\n\n");
                       }
                       showMessage("Data",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String message){
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setCancelable(true);
       builder.setTitle(title);
       builder.setMessage(message);
       builder.show();
    }


}