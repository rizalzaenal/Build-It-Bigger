package com.rizalzaenal.jokesprovider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokesProducer {
    private List<String> jokes;
    private Random random;

    private void populateJokes() {
        jokes.add("How does a rabbi make coffee? Hebrews it!");
        jokes.add("Rest in peace boiling water. You will be mist!");
        jokes.add("How do you throw a space party? You planet!");
        jokes.add(
          "Want to hear a construction joke? Oh never mind, I'm still working on that one.");
        jokes.add("Why don't scientists trust atoms? Because they make up everything!");
        jokes.add("I hate Russian dolls… they're so full of themselves!");
        jokes.add("Talk is cheap? Have you ever talked to a lawyer?");
        jokes.add("Why did the gym close down? It just didn't work out!");
        jokes.add("Two artists had an art contest. It ended in a draw!");
        jokes.add("What do you call a boomerang that doesn't come back? A stick!");
    }

    public JokesProducer() {
        jokes = new ArrayList<>();
        populateJokes();
        random = new Random();
    }

    public String gimmeJokes() {
        return jokes.get(random.nextInt(jokes.size()));
    }
}