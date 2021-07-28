package com.yoni.Light.Narration.Client;

import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class OpenLibClient {
    private Mono<String> googleBookApi(String bookName)
    {
        Dotenv dotenv = Dotenv.load();
        final String API_KEY = dotenv.get("API_KEY");

        WebClient webClient = WebClient.create();

        String URI = "https://www.googleapis.com/books/v1/volumes?q=\"" + bookName +
                "”&printType=books&key=" + API_KEY;

        return  webClient.get().uri(URI).retrieve().bodyToMono(String.class);
    }

    private JSONObject cleanBookApi(Mono<String> bookInfo) throws JSONException
    {
        JSONObject bookInfoJson = new JSONObject(bookInfo.block());
        return new JSONObject(bookInfoJson.getJSONArray("items").get(0).toString());
    }

    public String[] callApi(String bookName){

        String[] descriptionAuthorCover = new String[3];

        try
        {
            JSONObject aBook = cleanBookApi(googleBookApi(bookName));
            descriptionAuthorCover[0] = bookDescription(aBook);
            descriptionAuthorCover[1] = bookAuthors(aBook);
            descriptionAuthorCover[2] = bookCover(aBook);

        }catch(JSONException e)
        {
            descriptionAuthorCover[0] = "";
            descriptionAuthorCover[1] = "";
            descriptionAuthorCover[2] = "https://mrb.imgix.net/assets/default-book.png";
        }

        return descriptionAuthorCover;
    }
}
