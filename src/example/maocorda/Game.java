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
	int q;
	int r;
	int p;
	int t;
	Boolean possivel = false;
	static Rect atual = new Rect();
	static Rect corda = new Rect();
	static Rect Impulso = new Rect();

	private int counter;
	private int period = 100;
	private int current;
	Paint paint = new Paint();
	static float positionX = 40;
	private static float positionY = 30;
	private static float Width = 30;
	private static float Height = 30;
	ImageManager img;
	Bitmap imagem;
	Bitmap impulso;
	int Forca;
	int Num_impulso;
	Boolean impp = false;
	private int segTouchX;
	private int segTouchY;
	String SegTouch;
	String PriTouch;

	public Game(Context context) {
		super(context);
		setFocusableInTouchMode(true);
		setClickable(true);
		setLongClickable(true);

		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		img = new ImageManager((int) positionY * 2, (int) positionY * 2);
		imagem = img.ImageManager("Game_Guerra.jpg", context);
		impulso = img.ImageManager("amarelo.png", context);

		Thread processo = new Thread(this);
		processo.start();
	}

	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Impulso.set((int) getWidth() / 10, (int) 6 * getHeight() / 10, (int) 3
				* getWidth() / 10, (int) 8 * getHeight() / 10);

		Width = getWidth() / 2;
		Height = getHeight() / 2;
		positionX = Width;
		positionY = Height;

	}

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Log.i("foi", "down baby down !! ");
			q = (int) event.getX(0);
			r = (int) event.getY(0);
			Log.d("vamos", "" + q);
			if (Impulso.contains(q, r)) {
				impp = true;
				Num_impulso++;
				current = period;
				PriTouch = "Impulso";
			}
			if (corda.contains(q, r )) {
				possivel = true;
				impp = false;
				PriTouch = "corda";

			}

		}
		if (event.getAction() == MotionEvent.ACTION_POINTER_2_DOWN) {
			Log.i("foi", "dsegundo !! ");
			segTouchX = (int) event.getX(1);
			segTouchY = (int) event.getY(1);

			Log.d("vamos", "" + segTouchX);
			if (impp == false) {
				if (Impulso.contains(segTouchX, segTouchY)) {
					impp = true;
					Num_impulso++;
					current = period;
					SegTouch = "Impulso";
				}
			}
			if (possivel == false) {
				if (corda.contains(segTouchX, segTouchY)) {
					SegTouch = "corda";
					possivel = true;

				}
			}
		}

		if (event.getAction() == MotionEvent.ACTION_POINTER_2_UP) {
			Log.i("foi", "dsegundo UP!! ");
			p = (int) event.getX(1);
			t =(int) event.getY(1);
			if (corda.contains(p, t )) {

				if (p - segTouchX >= 4) {

					aplicarForca((int) (p - segTouchX) / 3 * Num_impulso);
					possivel = false;

				} else {
					possivel = false;

				}

			}
		
			if (Impulso.contains(p, t )) {
				Num_impulso = (current - period) * 10;
				impp = false;
			}

		}

		if (event.getAction() == MotionEvent.ACTION_MOVE) {

			if (possivel == true && q < event.getX(0) && PriTouch == "corda") {
				positionX = event.getX(0);
			}

			if (possivel == true && segTouchX < event.getX(1) && SegTouch == "corda") {
				positionX = event.getX(1);
			}
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			Log.i("foi", "UP !!!");
			int a = (int) event.getX(0);
			int b = (int) event.getY(0);

			if (corda.contains(a, b )) {

				if (a - q >= 4) {

					aplicarForca((int) (a - q) / 3 * Num_impulso);
					possivel = false;

				} else {
					possivel = false;

				}

			}
			if (Impulso.contains(a, b )) {
				Num_impulso = (current - period) * 10;
				impp = false;
			}

		}
		return super.onTouchEvent(event);
	}

	private void aplicarForca(int i) {

		// positionX+=i;

		Forca = i;
		// TODO Auto-generated method stub
		positionX = Width;
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);

		atual.set(0, 0, (int) Width * 2, (int) Height * 2);
		corda.set((int) positionX, (int) positionY, (int) positionX + 200,
				(int) positionY + 100);
		canvas.drawBitmap(imagem, null, atual, paint);
		canvas.drawRect(corda, paint);
		canvas.drawText("forcaa" + Forca + "impulsooo" + Num_impulso, 50, 30,
				paint);
		canvas.drawRect(Impulso, paint);

	}

	@Override
	public void run() {
		while (true) {

			try {

				Thread.sleep(1);
			} catch (Exception e) {
				Log.e("Deu erro", "Quem sabe mete o pe");
			}

			update();
			postInvalidate();
		}

		// TODO Auto-generated method stub

	}

	public void update() {
		if (period != 0) {
			counter++;
		}

		if (counter == 1000) {
			period -= 1;
			counter = 0;
		}
	}

}
