package com.github.sulir.vcmapper.base;

import com.github.sulir.vcmapper.exceptions.AmbiguityException;
import com.github.sulir.vcmapper.exceptions.NoMatchException;
import com.github.sulir.vcmapper.impl.Command;
import com.github.sulir.vcmapper.impl.ControlledMethod;
import com.github.sulir.vcmapper.impl.Lexer;
import com.github.sulir.vcmapper.impl.MethodIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CommandExecutor {
    private static final double SCORE_THRESHOLD = 0.2;
    private final MethodIndex index = new MethodIndex();
    private Consumer<String> voiceOutput;

    public CommandExecutor(Object... objects) {
        for (Object object : objects)
            index.addObject(object);
    }

    public void addObject(Object object) {
        index.addObject(object);
    }

    public void setVoiceOutput(Consumer<String> voiceOutput) {
        this.voiceOutput = voiceOutput;
    }

    public void execute(String sentence) throws NoMatchException, AmbiguityException {
        List<String> words = Lexer.getInstance().tokenize(sentence);
        double bestScore = 0;
        List<Command> bestMatches = new ArrayList<>();

        for (ControlledMethod method : index.getMethods()) {
            Command command = method.tryRegex(sentence, this);

            if (command != null) {
                bestMatches = List.of(command);
                break;
            }

            command = method.matchParameters(words);

            if (command != null) {
                double score = command.calculateScore();

                if (score > bestScore) {
                    bestScore = score;
                    bestMatches.clear();
                }

                if (score >= SCORE_THRESHOLD && score >= bestScore) {
                    bestMatches.add(command);
                }
            }
        }

        bestMatches.forEach(command -> command.setVoiceOutput(voiceOutput));

        if (bestMatches.isEmpty())
            throw new NoMatchException();
        else if (bestMatches.size() > 1)
            throw new AmbiguityException(bestMatches);
        else
            bestMatches.get(0).execute();
    }
}
