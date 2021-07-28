package com.yoni.Light.Narration.Client;

import com.yoni.Light.Narration.Models.Book;
import io.github.cdimascio.dotenv.Dotenv;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OpenLibClient {
    // SENDS REQUEST TO GOOGLE BOOKS API TO RETRIEVE ALL BOOK THAT MATCH THE PARAMETERS
    // IN THIS CASE THE PARAMETERS ARE HAVING "LIGHT NOVEL" IN THE TITLE LOL
    private Mono<String> googleBookApi()
    {
        Dotenv dotenv = Dotenv.load();
        final String API_KEY = dotenv.get("API_KEY");

        WebClient webClient = WebClient.create();

        //TODO: Fix API call so that we get the proper search results
        String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=light%20novel&printType=books&orderBy=newest&maxResults=40&key=";

        String URI = baseUrl + API_KEY;
        System.out.println(URI);

        return  webClient.get().uri(URI).retrieve().bodyToMono(String.class);
    }

    private JSONArray cleanBookApi(Mono<String> bookInfo) throws JSONException
    {
        JSONObject bookInfoJson = new JSONObject(bookInfo.block());
        return bookInfoJson.getJSONArray("items");
    }

    private String getBookDescription(JSONObject bookInfoItemJson)
    {
        //TODO: Either handle all JSONExceptions in the other get methods or make them all into one method
        try{
            Object description = bookInfoItemJson.getJSONObject("volumeInfo").get("description");
            return (String) description;
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String getBookCover(JSONObject bookInfoItemJson) throws JSONException
    {
        Object bookCover = bookInfoItemJson.getJSONObject("volumeInfo").getJSONObject("imageLinks").get("thumbnail");
        return (String) bookCover;
    }

    private String getBookAuthor(JSONObject bookInfoItemJson) throws JSONException
    {
        JSONArray authors = bookInfoItemJson.getJSONObject("volumeInfo").getJSONArray("authors");

        List<String> authorList = new ArrayList<>();

        for(int i = 0; i< authors.length(); i++)
        {
            authorList.add(authors.getString(i));
        }

        StringBuilder authorsStringBuilder = new StringBuilder();

        for(int i = 0; i < authorList.size(); i++)
        {
            if(i != authorList.size() - 1)
            {
                authorsStringBuilder.append(authorList.get(i)).append(", ");
            }else
            {
                authorsStringBuilder.append(authorList.get(i)).append(" ");
            }
        }

        getBookCover(bookInfoItemJson);

        return authorsStringBuilder.toString();
    }

    private String getBookTitle(JSONObject bookInfoItemJson) throws JSONException
    {
        Object title = bookInfoItemJson.getJSONObject("volumeInfo").get("title");
        return (String) title;
    }

    public List<Book> callApi(){

        List<Book> books = new ArrayList<>();
        Book book; // This might cause an issue because I think the id value might be generated when we call new
        System.out.println("IN CALLAPI()");
        JSONArray jsonArray = null;

        try
        {
            System.out.println("IN TRY");
            jsonArray = cleanBookApi(googleBookApi());
            //System.out.println("Just Created json array maybe");
            System.out.println("Size of jsonArray " + jsonArray.length());
            System.out.println("JSONarray" + jsonArray);

//            for (int i = 0; i < 100; i++) {
//                System.out.println(i);
//            }

            for (int i = 0; i < jsonArray.length(); i++) {
                book = new Book();

                System.out.println("In for loop");
                // Iterate through the JSONObjs in the list and store them inside a list of Book objs
                JSONObject aBook = new JSONObject(jsonArray.get(i).toString());
                //System.out.println(aBook);


                book.setTitle(getBookTitle(aBook));
                book.setDescription(getBookDescription(aBook));
                book.setAuthors(getBookAuthor(aBook));
                book.setCoverImageLink(getBookCover(aBook));
                //System.out.println(book);

                books.add(book);
            }

        }catch(JSONException e)
        {
            book = new Book();
            System.out.println("CATCH");
            book.setTitle("");
            book.setDescription("");
            book.setAuthors("");
            book.setCoverImageLink("");

            books.add(book);
        }

        for (Book b : books) {
            System.out.println(b);
        }

        return books;
    }

}
