package example.maocorda;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class Game extends View implements Runnable

{	
	int q ;
	int r;
	Boolean possivel =false;
	static Rect atual=new Rect();
	static Rect corda=new Rect();
	Paint paint = new Paint();
	static float positionX=40;
	private static float positionY=30;
	private static float Width=30;
	private static float Height=30;
	ImageManager img;
	Bitmap imagem;
	
	public Game(Context context) {
		super(context);
		setFocusableInTouchMode(true);
		setClickable(true);
		setLongClickable(true);
		
		paint.setColor(Color.BLACK); 
		paint.setTextSize(20); 
		img = new ImageManager((int)positionY*2,(int)positionY*2);
		imagem=img.ImageManager("Game_Guerra.jpg", context);
		
		Thread processo = new Thread(this);
		processo.start();
		}
	
	
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		super.onSizeChanged(w, h, oldw, oldh);
		
		Width= getWidth()/2;
		Height=getHeight()/2;
		positionX=Width;
		positionY=Height;
	
	}
	
	public boolean onTouchEvent(MotionEvent event) 
	{	
		if (event.getAction() == MotionEvent.ACTION_DOWN) 
		{
			Log.i("foi", "down baby down !! ");
			q = (int)event.getRawX();
			r = (int)event.getRawY();
			if(corda.contains(q, r)){
			possivel=true;
			//aplicarForca(10);

			}
			else{
				possivel=false;
			}
		}
		

		
		if (event.getAction() == MotionEvent.ACTION_UP) 
		{
			Log.i("foi", "UP !!!");
			int a = (int)event.getRawX();
			int b = (int)event.getRawY();
			
			if(possivel){
				if(a-q >=4){
				
					aplicarForca((int)(a-q)/3);
				}
				else{
					possivel=false;
				}
			
			}
			
		}
		return super.onTouchEvent(event);
	}
		private void aplicarForca(int i) {
				
				positionX+=i;
				
				// TODO Auto-generated method stub
				
			}


	public void draw(Canvas canvas){
		super.draw(canvas);
		
		atual.set(0, 0, (int)Width*2, (int)Height*2);
		corda.set((int)positionX,(int) positionY,(int) positionX+20,(int) positionY+10);
		
		canvas.drawBitmap(imagem, null, atual, paint);
		canvas.drawRect(corda, paint);

			
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
