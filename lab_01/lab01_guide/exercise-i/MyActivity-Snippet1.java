import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {

	...

	public final static String EXTRA_MESSAGE = "pt.ulisboa.tecnico.cmov.helloworld.MESSAGE";

	...

	/** Called when the user clicks the Send button */
	public void sendMessage(View view) {
	    Intent intent = new Intent(this, DisplayMessageActivity.class);
	    EditText editText = (EditText) findViewById(R.id.edit_message);
	    String message = editText.getText().toString();
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}

}
