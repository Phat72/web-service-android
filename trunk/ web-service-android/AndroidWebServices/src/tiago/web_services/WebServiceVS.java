package tiago.web_services;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WebServiceVS extends Activity implements OnClickListener
{

	// Objects and variables to use in KSOAP 2

	// Name of the SOAP ACTION. (Can be extracted from
	// http://www.w3schools.com/webservices/tempconvert.asmx?op=CelsiusToFahrenheit)
	private String SOAP_ACTION = "http://tempuri.org/Calculadora";
	// Name of the method used by application
	private String METHOD_NAME = "Calculadora";
	// Name of namespace
	private String NAMESPACE = "http://tempuri.org/";
	// URL of the site of Web Service
	private String URL = "http://10.0.2.2:18344/WebServiceTutorial/Service.asmx";

	// Objects and variables to use in GUI

	EditText numberA, numberB, numberOfOperation, restul;
	Button callWS;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_vs);

		numberA = (EditText) findViewById(R.id.editTextNumberA);

		numberB = (EditText) findViewById(R.id.editTextNumberB);

		numberOfOperation = (EditText) findViewById(R.id.editTextNumberOfOperation);

		callWS = (Button) findViewById(R.id.buttonExecuteWebServiceFromVS);

		callWS.setOnClickListener(this);
	}

	/**
	 * Function that do all operations with Web Service
	 */
	private void callWebService()
	{
		//Request object
		SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);

		//Add the name of parameter("Celsius")
		Request.addProperty("ValorA", "10");
		Request.addProperty("ValorB", "1");
		Request.addProperty("TipoOperacao", "Subtracao");

		//Here is possible choose the verstion of Web Services (1.0, 1.1 or 1.2)
		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		
		//If the server is written in .net this flag MUST BE true. Otherwise the application
		//can´t be the right behaviour. For other servers(PHP,java,etc) this value MUST BE false.
		soapEnvelope.dotNet = true;
		
		soapEnvelope.setOutputSoapObject(Request);

		AndroidHttpTransport aht = new AndroidHttpTransport(URL);

		try
		{
			//Excecute the call for the server.
			aht.call(SOAP_ACTION, soapEnvelope);
			
			//Get the result of the call.
			SoapPrimitive resultString = (SoapPrimitive) soapEnvelope
					.getResponse();

			//Put the result on the EditText
			//editTextResultOfConversion.setText("" + resultString.toString());
			
			Toast.makeText(this, "Result is " +resultString.toString(), 10000).show();

		} catch (Exception e)
		{
			//Put the message of exception in EditText
			//editTextResultOfConversion.setText("Exception: " + e.getMessage());
			
			Toast.makeText(this, "Result is " + "Exception: " + e.getMessage() , 1000).show();
			
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v)
	{
		if(v == callWS)
		{
			callWebService();
		}
			
	}

}
