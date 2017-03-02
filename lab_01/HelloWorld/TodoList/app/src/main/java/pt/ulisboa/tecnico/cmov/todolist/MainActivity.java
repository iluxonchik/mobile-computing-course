package pt.ulisboa.tecnico.cmov.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items = new ArrayList();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);

        final EditText editText = (EditText) this.findViewById(R.id.newItem);
        editText.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Empty
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && s.charAt(s.length()-1) == '\n') {
                    Log.d("Main", "Enter pressed.");
                    if (s.length() > 1) {
                        // there is some text in the box, let's add it to the list
                        adapter.insert(s.toString(), 0);
                        editText.setText("");
                    } else {
                        // clear the text, to prevent "\n" from being added to the list
                        editText.setText("");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Empty
            }
        });

        ListView listView = (ListView) this.findViewById(R.id.todo_list);
        listView.setAdapter(adapter);
    }

    public void onButtonPress(View v) {
        EditText editText = (EditText)findViewById(R.id.newItem);
        String text = editText.getText().toString();
        if (text.length() > 1) {
                adapter.insert(text, 0);
                editText.setText("");
        }
    }
}
