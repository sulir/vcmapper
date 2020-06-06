package com.github.sulir.vcmapper.impl;

import com.github.sulir.vcmapper.base.Synonym;
import com.github.sulir.vcmapper.dialog.OnException;
import com.github.sulir.vcmapper.dialog.ResponseConversion;
import com.github.sulir.vcmapper.dialog.ResponseConverter;
import com.github.sulir.vcmapper.dialog.VoiceResponse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;

public class Command {
    private final Object object;
    private final Method method;
    private final List<String> methodWords;
    private List<String> sentenceWords;
    private final List<Object> parameterValues = new ArrayList<>();
    private Consumer<String> voiceOutput;

    public Command(Object object, Method method, List<String> methodWords) {
        this.object = object;
        this.method = method;
        this.methodWords = methodWords;
    }

    public void setSentenceWords(List<String> sentenceWords) {
        this.sentenceWords = sentenceWords;
    }

    public void addParameterValue(Object value) {
        parameterValues.add(value);
    }

    public void setVoiceOutput(Consumer<String> voiceOutput) {
        this.voiceOutput = voiceOutput;
    }

    public double calculateScore() {
        Set<String> methodSet = new HashSet<>(methodWords);
        Set<String> sentenceSet = new HashSet<>(sentenceWords);

        double maxScore = SetUtils.jaccardIndex(methodSet, sentenceSet);
        Lexer lexer = Lexer.getInstance();

        for (Synonym synonym: findSynonyms()) {
            Set<String> methodWithSynonyms = new HashSet<>(methodSet);
            for (String word : methodWithSynonyms) {
                if (word.equals(lexer.tokenizeAndJoin(synonym.of()))) {
                    methodWithSynonyms.remove(word);
                    methodWithSynonyms.addAll(lexer.tokenize(synonym.is()));
                    break;
                } else if (word.equals(lexer.tokenizeAndJoin(synonym.is()))) {
                    methodWithSynonyms.remove(word);
                    methodWithSynonyms.addAll(lexer.tokenize(synonym.of()));
                    break;
                }
            }

            double score = SetUtils.jaccardIndex(methodWithSynonyms, sentenceSet);
            if (score > maxScore) {
                maxScore = score;
                methodSet = methodWithSynonyms;
            }
        }

        return maxScore;
    }

    public void execute() {
        try {
            Object result = method.invoke(object, parameterValues.toArray());

            if (voiceOutput != null && result != null) {
                if (result instanceof String) {
                    voiceOutput.accept((String) result);
                } else if (method.isAnnotationPresent(ResponseConversion.class)) {
                    @SuppressWarnings("rawtypes") Class<? extends ResponseConverter> converter =
                            method.getAnnotation(ResponseConversion.class).value();
                    //noinspection unchecked
                    voiceOutput.accept(converter.getDeclaredConstructor().newInstance().toResponse(result));
                } else if (result instanceof VoiceResponse) {
                    voiceOutput.accept(((VoiceResponse) result).toResponse());
                } else {
                    voiceOutput.accept(result.toString());
                }
            }
        } catch (InvocationTargetException e) {
            if (voiceOutput != null) {
                OnException onException = method.getAnnotation(OnException.class);

                if (onException != null && onException.of() == e.getCause().getClass())
                    voiceOutput.accept(onException.say());
                else
                    voiceOutput.accept(e.getCause().getMessage());
            }
        } catch (IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return method.toString();
    }

    private List<Synonym> findSynonyms() {
        List<Synonym> result = new ArrayList<>();

        result.addAll(Arrays.asList(method.getAnnotationsByType(Synonym.class)));
        result.addAll(Arrays.asList(method.getDeclaringClass().getAnnotationsByType(Synonym.class)));

        return result;
    }
}
