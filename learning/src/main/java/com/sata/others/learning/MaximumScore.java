package com.sata.others.learning;

import java.util.HashMap;
import java.util.Map;

class MaximumScore {
    int scoreRes = 0;
    public int maxScoreWords(String[] words, char[] letters, int[] scores) {
        Map<String, Integer> mp = new HashMap<>();
        for(String word : words) {
            int score = 0;
            for(char c : word.toCharArray()) {
                score += scores[c - 'a'];
            }
            mp.put(word, score);
        }
        Map<Character, Integer> times = new HashMap<>();
        for(char c : letters)  {
            times.put(c, times.getOrDefault(c, 0) + 1);
        }
        backTracking(mp, times, words, 0,  0);
        return scoreRes;
    }
    private void backTracking(Map<String, Integer> mp, Map<Character, Integer> times, String[] words, int index, int cur) {
        if(index == words.length) {
            scoreRes = Math.max(scoreRes, cur);
            return;
        }

        if(validate(words[index], times)) {
            helper(times, words[index], true);
            backTracking(mp, times, words,  index + 1, cur + mp.get(words[index]));
            helper(times, words[index], false);
        }
        backTracking(mp, times, words,  index + 1, cur);
    }
    private void helper(Map<Character, Integer> times, String word, boolean tag) {
        for(char c : word.toCharArray()) {
            times.put(c, tag ? times.get(c) - 1 : times.get(c) + 1);
        }
    }
    private boolean validate(String word, Map<Character, Integer> times) {
        for(char c : word.toCharArray()) {
            if(! times.containsKey(c) || times.get(c) == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MaximumScore maximumScore = new MaximumScore();
        String[] words = {"dog","cat","dad","good"};
        char[] letters ={'a','a','c','d','d','d','g','o','o'};
        int[] scores = {1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0};
        int score = maximumScore.maxScoreWords(words, letters, scores);
        System.out.println(score);
    }
}