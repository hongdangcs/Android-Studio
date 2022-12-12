package com.ddq.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnViewAll;
    EditText nameEt, ageEt;
    Switch isActive;
    ListView customerListView;
    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnViewAll = findViewById(R.id.btnViewAll);
        nameEt = findViewById(R.id.editTextName);
        ageEt = findViewById(R.id.editTextAge);
        customerListView = findViewById(R.id.customerView);
        isActive = findViewById(R.id.switchActiveCustomer);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomerModel customerModel;
                try{
                     customerModel = new CustomerModel(5, nameEt.getText().toString(), Integer.parseInt(ageEt.getText().toString()), isActive.isChecked());
                    Toast.makeText(MainActivity.this, customerModel.toString(), Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Null input", Toast.LENGTH_LONG).show();
                    customerModel = new CustomerModel(-1, "error", -1, false);
                }
                MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
                boolean success = myDatabaseHelper.addCustomer(customerModel);
                Toast.makeText(MainActivity.this, "Success" + success, Toast.LENGTH_LONG).show();
            }
        });
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
                List<CustomerModel> everyone = myDatabaseHelper.getAllCustomer();

                ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
                customerListView.setAdapter(customerArrayAdapter);
            }
        });
        customerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomerModel clickCustomer = (CustomerModel) parent.getItemAtPosition(position);
                myDatabaseHelper.deleteCustomer(clickCustomer);

            }
        });
    }
}