package APIConnection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class APIConnection {

    public Answer getAnswerFromApi() throws IOException {
        URL url = new URL("https://craggy-island-8-ball-api.herokuapp.com/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        conn.connect();

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }


        Gson gson = new Gson();

        String answersFromAPI = "";
        Scanner sc = new Scanner(url.openStream());

        while (sc.hasNext()) {
            String answer = sc.nextLine();
            answersFromAPI += answer;
        }
        sc.close();

        TypeToken<AnswerList> answerList = new TypeToken<AnswerList>() {};
        AnswerList answerList1 = gson.fromJson(answersFromAPI, answerList.getType());

//        for (APIConnection.APIConnection.Answer answer : answerList1.answers)
//        {
//            System.out.println(answer.getId() + " : " + answer.getText());
//        }

        Answer randomAnswer = answerList1.getRandomAnswer();
        //System.out.println(randomAnswer.getText());
        return randomAnswer;
    }


}
