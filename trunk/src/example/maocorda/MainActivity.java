package example.maocorda;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.menu);
	}
	
	public void new_game(View v)
	{
		Intent i = new Intent();
		i.setClass(this, Conectar.class);
		startActivity(i);
	}
}
