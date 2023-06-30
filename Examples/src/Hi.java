import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Hi {
	public static void main(String []args) throws URISyntaxException, IOException, InterruptedException {
		String url="http://192.168.60.60:8520/get/1";
		HttpRequest request = HttpRequest.newBuilder().GET()
				  .uri(URI.create(url))
				  .build();
		HttpClient client=HttpClient.newBuilder().build();
		HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());
		System.out.println(response.statusCode());
		System.out.println(response.body());
}
}
