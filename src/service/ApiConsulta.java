package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiConsulta {
    private static final String CAT_API_URL = "https://api.thecatapi.com/v1/images/search";
    private static final String DOG_API_URL = "https://api.thedogapi.com/v1/images/search";

    public static String getImagemAleatoriaGato() {
        return fetchImageUrlFromApi(CAT_API_URL);
    }

    public static String getImagemAleatoriaCachorro() {
        return fetchImageUrlFromApi(DOG_API_URL);
    }

    @SuppressWarnings("deprecation")
    private static String fetchImageUrlFromApi(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int status = connection.getResponseCode();
            if (status != 200) {
                throw new RuntimeException("Erro na requisição: " + status);
            }

            BufferedReader in =
                    new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                resposta.append(inputLine);
            }

            in.close();
            connection.disconnect();

            // Ex: [{"id":"abc123","url":"https://..."}]
            String json = resposta.toString();
            if (json == null) {
                System.out.println("ta nuulll");
            }
            int urlIndex = json.indexOf("\"url\":\"") + 7;
            int urlEndIndex = json.indexOf("\"", urlIndex);

            if (urlIndex > 6 && urlEndIndex > urlIndex) {
                return json.substring(urlIndex, urlEndIndex);
            } else {
                return "Imagem não encontrada.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao buscar imagem.";
        }
    }
}
