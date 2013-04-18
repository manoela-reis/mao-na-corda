package example.maocorda;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Conectar extends Activity 
{
	View view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conectar);
		
	}
	
	public void new_game(View v)
	{
		view = new Game(this);
		setContentView(view);
	}

}
