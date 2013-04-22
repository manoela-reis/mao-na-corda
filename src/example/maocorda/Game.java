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
	static Rect Impulso=new Rect();

	private int counter;
	private int period=100;
	private int current;
	Paint paint = new Paint();
	static float positionX=40;
	private static float positionY=30;
	private static float Width=30;
	private static float Height=30;
	ImageManager img;
	Bitmap imagem;
	Bitmap impulso;
	int Forca;
	int Num_impulso;
	Boolean impp = false;
	
	public Game(Context context) {
		super(context);
		setFocusableInTouchMode(true);
		setClickable(true);
		setLongClickable(true);
		
		paint.setColor(Color.BLACK); 
		paint.setTextSize(20); 
		img = new ImageManager((int)positionY*2,(int)positionY*2);
		imagem=img.ImageManager("Game_Guerra.jpg", context);
		impulso=img.ImageManager("amarelo.png", context);
		
		Thread processo = new Thread(this);
		processo.start();
		}
	
	
	protected void onSizeChanged(int w, int h, int oldw, int oldh) 
	{
		super.onSizeChanged(w, h, oldw, oldh);
		Impulso.set((int)getWidth()/10,(int)6*getHeight()/10 , (int)3*getWidth()/10, (int)8*getHeight()/10);
		
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
			
			if(Impulso.contains(q, r- Impulso.height())){
				impp=true;
				Num_impulso++;
				current= period;
			}
			if(corda.contains(q, r-corda.height()/2)){
			possivel=true;
			

			}
			else{
				possivel=false;
			}
		}
		
		if (event.getAction() == MotionEvent.ACTION_MOVE) 
		{
		
			if(possivel && q<event.getRawX()){
				positionX = event.getRawX();
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
					
					aplicarForca((int)(a-q)/3*Num_impulso);
					possivel=false;
					
				}
				else{
					possivel=false;
					impp=false;

				}
			
			}
			if(impp){
				Num_impulso= (current -period)*10;
				impp=false;
			}
			positionX=Width;
			
		}
		return super.onTouchEvent(event);
	}
		private void aplicarForca(int i) {
				
				//positionX+=i;
				
			Forca =i;
				// TODO Auto-generated method stub
				
			}


	public void draw(Canvas canvas){
		super.draw(canvas);
		
		atual.set(0, 0, (int)Width*2, (int)Height*2);
		corda.set((int)positionX,(int) positionY,(int) positionX+200,(int) positionY+100);
		canvas.drawBitmap(imagem, null, atual, paint);
		canvas.drawRect(corda, paint);
		canvas.drawText("forcaa"+Forca+"impulsooo"+Num_impulso, 50, 30, paint);
		canvas.drawRect(Impulso, paint);

		
			
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
			
		
			update();
			postInvalidate();
		}
		
		// TODO Auto-generated method stub
		

	}
	
	
	public void update(){
		if (period !=0)
		{
			counter ++;
		}
		
		if (counter == 1000)
		{
			period-=1;
			counter = 0;
		}
	}



}
