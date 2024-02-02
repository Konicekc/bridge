package bridge;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.net.ssl.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;

/**
 * the bridge between the AI player, and human moderator and the game itself.
 */
public class Bridge extends Application {
    // this should only be used to store the most recent response.
    private String response;
    // this will store all the responses
    private StringBuilder log = new StringBuilder();
    public static void main(String[] args) {
        // Disable SSL certificate check
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, TrustAllManager.getTrustManagers(), new java.security.SecureRandom());
            String encoding = Base64.getEncoder().encodeToString(("yoder" + ":" + System.getenv("ROSIE_PASSWORD")).getBytes());

            // Create a custom HttpClient with the disabled certificate check
            HttpClient client = HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .build();

            // Make the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://dh-ood.hpc.msoe.edu/node/dh-node4.hpc.msoe.edu/5000/greet?username=Blah"))
                    .setHeader("Authorization", "Basic " + encoding).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //   launch(args);
    }





    /**
     * prompt generation logic.
     * @param role the player's role, or VOTING if the player needs to vote
     * @param options all valid options
     * @return the prompt
     */
    public String generatePrompt(String role, ArrayList<Integer> options){
        return null;
    }

    /**
     * parses the response from the AI, looking for the entered keyword(s).
     * this method will have to remove punctuation/ uppercase chars as needed.
     * @param response the response from the AI.
     * @param keywords anything the human moderator is looking for.
     * @return the first keyword that appears in the response,
     * or null if no keywords are in the response.
     */
    public String parseResponse(String response, ArrayList<String> keywords){
        return null;
    }
    /**
     * generates a pre-written prompt containing the passed in options and sends it to the player.
     * to be used for both roleActions AND voting.
     * this method should call the other ones.
     * @param options the options to be included in the prompt
     * @param actionType the type of action the request was made for
     * @return the selection made by the player
     */
    public int request(ArrayList<Integer> options, String actionType){
        return 0;
    }

    /**
     * logs the responses of the AI. can also be used to log the prompts.
     * @param response the response from the AI.
     */
    public void logResponse(String response){
        log.append(response);
        log.append("\n");
    }
    public String getLog(){
        return log.toString();
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}