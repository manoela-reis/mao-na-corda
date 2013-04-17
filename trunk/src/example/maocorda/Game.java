package example.maocorda;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Game extends View implements Runnable{

	
	
	
	Paint paint = new Paint();
	static float positionX=0;
	private static float positionY=0;
	
	public Game(Context context) {
		super(context);

		paint.setColor(Color.BLACK); 
		paint.setTextSize(20); 

	
		Thread processo = new Thread(this);
		processo.start();
		}

	public void draw(Canvas canvas){
		super.draw(canvas);
		
		
		canvas.drawRect(positionX, positionY, positionX+40, positionY+40, paint);

		Puxando.atual.set((int)positionX, (int)positionY, (int)positionX+40, (int)positionY+40);
		
	}
	@Override
	public void run() {
		while(true)
		{
			
			try{
				
				Thread.sleep(1);
			}
			catch(Exception e){
				Log.e("Deu erro", "Quem sabe mete o pe");
			}
			
		
			postInvalidate();
		}
		
		// TODO Auto-generated method stub
		
	}
	
	
	



}
