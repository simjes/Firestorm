package mainpkg.fireplace;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class HotTouchListener implements OnTouchListener {

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			HttpThread hot = new HttpThread("hot");
			Thread hotThread = new Thread(hot);
			hotThread.start();
			hotThread.interrupt();
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			HttpThread stopHot = new HttpThread("hot_stop");
			Thread stopHotThread = new Thread(stopHot);
			stopHotThread.start();
			stopHotThread.interrupt();
		}
		return true;
	}

}
