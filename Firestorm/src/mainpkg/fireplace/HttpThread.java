package mainpkg.fireplace;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpThread implements Runnable {
	String type = "";
	HttpResponse httpRes;

	public HttpThread(String type) {
		this.type = type;
	}

	@Override
	public void run() {
		HttpClient client = new DefaultHttpClient();
		try {
			httpRes = client.execute(new HttpGet("http://10.0.1.18:8000/" + type));
			StatusLine sl = httpRes.getStatusLine();
			if (sl.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				httpRes.getEntity().writeTo(outStream);
				outStream.close();
			} else {
				httpRes.getEntity().getContent().close();
				throw new IOException(sl.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
