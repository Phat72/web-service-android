package tiago.web_services;

/**
 * 
 * Activity to use Web Services in Android.
 * This API uses the KSOAP 2 version 2.3.
 * 
 * This project call a function that convert one temperature typed by the user
 * from Celsius to Fahrenheit. The link of the Web Serivce is:
 * http://www.w3schools.com/webservices/tempconvert.asmx
 * 
 * Next step is update KSOAP 2 to latest version(2.6).
 * 
 * 
 * @author Tiago Melo
 *
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{

	Button btnGoToWsInternet, btnGoToWsVS;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_activity_menu);

		btnGoToWsInternet = (Button) findViewById(R.id.buttonWebServiceFromInternet);
		btnGoToWsVS = (Button) findViewById(R.id.buttonWebServiceFromVS);
		
		btnGoToWsInternet.setOnClickListener(this);
		btnGoToWsVS.setOnClickListener(this);
	}

	
	public void onClick(View v)
	{
		if (v == btnGoToWsInternet)
		{
			Intent it = new Intent(this, WebServiceInternet.class);

			startActivity(it);

		}

		if (v == btnGoToWsVS)
		{
			Intent it = new Intent(this, WebServiceVS.class);

			startActivity(it);
		}

	}

}