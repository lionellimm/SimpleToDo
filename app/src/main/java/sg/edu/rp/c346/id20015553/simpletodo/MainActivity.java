package sg.edu.rp.c346.id20015553.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Random;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editAction;
    Button btnAdd, btnClear, btnDelete;
    ListView listView;
    Spinner barSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editAction = findViewById(R.id.editAction);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);
        ListView listView = findViewById(R.id.listView);
        barSpinner = findViewById(R.id.barSpinner);

        ArrayList<String> actionArray = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, actionArray);
        listView.setAdapter(adapter);

        barSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        editAction.setText("");
                        editAction.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        editAction.setText("");
                        editAction.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });







        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editAction.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "Input Required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    actionArray.add(editAction.getText().toString());
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actionArray.isEmpty() == true){
                    Toast.makeText(MainActivity.this, "Nothing to clear!", Toast.LENGTH_SHORT).show();
                }
                else{
                    actionArray.clear();
                    editAction.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTrue = false;
                if(actionArray.isEmpty()==true){
                    Toast.makeText(MainActivity.this, "Nothing to delete!", Toast.LENGTH_SHORT).show();
                }
                else if(editAction.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(MainActivity.this, "Index Required!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(Integer.parseInt(editAction.getText().toString()) > actionArray.size()-1){
                        Toast.makeText(MainActivity.this, "Too large of an index!", Toast.LENGTH_SHORT).show();
                    }else{
                        for(int i = 0; i < actionArray.size(); i++){
                            if(i == Integer.parseInt(editAction.getText().toString())){
                                isTrue = true;
                                break;
                            }
                            else{
                                isTrue = false;
                            }
                        }
                        if(isTrue == true){
                            actionArray.remove(Integer.parseInt(editAction.getText().toString()));
                            adapter.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Index not found!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });


//        btnRandom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Random rand = new Random();
//                int limit = actionArray.size(); //generate values from 0 - actionArray.size() - 1
//                int initial = Integer.parseInt(editAction.getText().toString()); //index
//                int rando = rand.nextInt(limit);
//                actionArray.add(rando, actionArray.get(initial));
//                actionArray.add(rando);
//                actionArray.remove(initial);
//            }
//        });


    }
}