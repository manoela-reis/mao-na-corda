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

	int q ;
	int r;
	Rect atual=new Rect();
	Boolean possivel =true;
	Paint paint = new Paint();
	private static float positionX=0;
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

		atual.set((int)positionX, (int)positionY, (int)positionX+40, (int)positionY+40);
		
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
	
	public boolean onTouchEvent(MotionEvent event) 
	{	
		if (event.getAction() == MotionEvent.ACTION_DOWN) 
		{
			Log.i("foi", "down baby down !! ");
			q = (int)event.getRawX();
			r = (int)event.getRawY();
			if(atual.contains(q, r)){
			
			}
			else{
				q=0;
				r=0;
			}

		}
		

		
		if (event.getAction() == MotionEvent.ACTION_UP) 
		{
			Log.i("foooooooooi", "UP !!!");
			int a = (int)event.getRawX();
			int b = (int)event.getRawY();
			
			if(q!=0 & r!=0){
				if(a-q >=20){
				
					aplicarForca(10);
				}
				else{
					aplicarForca(q);
				}
			
			}
			
		}
		return super.onTouchEvent(event);
	}

	private void aplicarForca(int i) {
		
		positionX+=i;
		
		// TODO Auto-generated method stub
		
	}



}
