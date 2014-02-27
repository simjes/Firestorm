package mainpkg.fireplace;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends Activity {
	Button hot;
	Button cold;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		hot = (Button)findViewById(R.id.Hot);
		cold = (Button)findViewById(R.id.Cold);
		hot.setOnTouchListener(new HotTouchListener());
		cold.setOnTouchListener(new ColdTouchListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.randomcolor:
			Random rng = new Random();
			int colorHot = Color.argb(255, rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
			GradientDrawable hotChangeColor = (GradientDrawable) hot.getBackground();
			hotChangeColor.setColor(colorHot);
			int colorCold = Color.argb(255, rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));
			GradientDrawable coldChangeColor = (GradientDrawable) cold.getBackground();
			coldChangeColor.setColor(colorCold);
			return true;
		case R.id.standardcolor:
			GradientDrawable hotStandardColor = (GradientDrawable) hot.getBackground();
			hotStandardColor.setColor(Color.argb(255, 159, 34, 0));
			GradientDrawable coldStandardColor = (GradientDrawable) cold.getBackground();
			coldStandardColor.setColor(Color.argb(255, 35, 95, 218));
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
