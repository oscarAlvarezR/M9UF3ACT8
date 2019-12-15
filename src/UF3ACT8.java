import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class UF3ACT8 {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		UF3ACT8 http = new UF3ACT8();

		Scanner teclado = new Scanner(System.in);

		// Demanem al usuari que introdueixi els parametres
		System.out.println("Introduce 1 para iniciar el metodo sendGet:");
		System.out.println("Introduce 2 para iniciar el metodo sendPost:");
		int seleccio = teclado.nextInt();

		while ( !(seleccio == 1 || seleccio == 2)) {

			System.out.println("Introduce 1 para iniciar el metodo sendGet:");
			System.out.println("Introduce 2 para iniciar el metodo sendPost:");
			seleccio = teclado.nextInt();
		}

		teclado.nextLine();

		if (seleccio == 1) {

			http.sendGet();

		} else {

			http.sendPost();
		}



	}

	// HTTP GET request
	private void sendGet() throws Exception {

		Scanner teclado = new Scanner(System.in);

		// Demanem la url
		System.out.println("Introdueix la url (ex: http://www.insbaixcamp.org/):");
		String url = teclado.nextLine();

		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	private void sendPost() throws Exception {

		Scanner teclado = new Scanner(System.in);

		// Demanem la url
		System.out.println("Introdueix la url (ex: http://www.insbaixcamp.org/):");
		String url = teclado.nextLine();
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "ca-es");

		//Query string
		String urlParameters = "categoryid=7";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

}
