package sg.edu.rp.c346.id20006949.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText editTodo;
Button buttonAdd;
Button buttonDelete;
Button buttonClear;
ArrayList<String> alTodo;
ArrayAdapter<String> aaTodo;
Spinner todoitems;
ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTodo = findViewById(R.id.eTodo);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonClear = findViewById(R.id.buttonClear);
        lv = findViewById(R.id.lv);
        todoitems = findViewById(R.id.todoitems);
        alTodo = new ArrayList<>();
        aaTodo = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,alTodo);
        lv.setAdapter(aaTodo);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nTodo = editTodo.getText().toString();
                alTodo.add(nTodo);
                aaTodo.notifyDataSetChanged();
                editTodo.setText(null);
                Toast.makeText(MainActivity.this, "Task has been added", Toast.LENGTH_LONG).show();

            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alTodo.clear();
                aaTodo.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Task has been cleared", Toast.LENGTH_LONG).show();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nTodo = editTodo.getText().toString();
                int pos = Integer.parseInt(editTodo.getText().toString());
                alTodo.remove(pos);
                aaTodo.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Task has been deleted", Toast.LENGTH_LONG).show();
            }
        });
        aaTodo.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                if (lv.getAdapter().isEmpty())
                    buttonDelete.setEnabled(false);
                else
                    buttonDelete.setEnabled(true);
            }
        });

    }
}