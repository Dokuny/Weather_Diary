package site.notion.dokuny.weather_diary.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.notion.dokuny.weather_diary.exception.ApiConnectorException;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiConnector {

	public static String getResponseString(String apiUrl) {
		try {
			log.info("APIConnector started to get response from API");
			URL url = new URL(apiUrl);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();

			BufferedReader br;
			if (responseCode == 200) {
				log.info("API Connector received normal response");
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				log.info("API Connector received error response");
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuilder response = new StringBuilder();

			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}

			br.close();

			log.info("APIConnector completed to get response from API");
			return response.toString();

		} catch (Exception e) {
			log.error("ApiConnector failed to get response from API");
			throw new ApiConnectorException("failed to get response",e);
		}
	}


}
