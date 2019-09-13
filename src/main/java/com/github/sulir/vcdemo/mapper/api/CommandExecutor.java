package com.github.sulir.vcdemo.mapper.api;

import com.github.sulir.vcdemo.mapper.exceptions.AmbiguityException;
import com.github.sulir.vcdemo.mapper.exceptions.NoMatchException;
import com.github.sulir.vcdemo.mapper.impl.Command;
import com.github.sulir.vcdemo.mapper.impl.ControlledMethod;
import com.github.sulir.vcdemo.mapper.impl.Lexer;
import com.github.sulir.vcdemo.mapper.impl.MethodIndex;

import java.util.ArrayList;
import java.util.List;

public class CommandExecutor {
    private static final double SCORE_THRESHOLD = 0.1;
    private MethodIndex index = new MethodIndex();

    public CommandExecutor(Object... objects) {
        for (Object object : objects)
            index.addObject(object);
    }

    public void addObject(Object object) {
        index.addObject(object);
    }

    public void execute(String sentence) throws NoMatchException, AmbiguityException {
        List<String> words = Lexer.getInstance().tokenize(sentence);
        double bestScore = 0;
        List<Command> bestMatches = new ArrayList<>();

        for (ControlledMethod method : index.getMethods()) {
            Command command = method.matchParameters(words);

            if (command != null) {
                double score = command.calculateScore();

                if (score > bestScore) {
                    bestScore = score;
                    bestMatches.clear();
                }

                if (score > SCORE_THRESHOLD && score >= bestScore) {
                    bestMatches.add(command);
                }
            }
        }

        if (bestMatches.isEmpty())
            throw new NoMatchException();
        else if (bestMatches.size() > 1)
            throw new AmbiguityException(bestMatches);
        else
            bestMatches.get(0).execute();
    }
}