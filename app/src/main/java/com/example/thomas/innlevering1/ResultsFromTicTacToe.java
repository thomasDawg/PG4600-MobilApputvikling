package com.example.thomas.innlevering1;

import java.util.ArrayList;

/**
 * Created by thomas on 17.02.2016.
 */
public class ResultsFromTicTacToe {
    private static ArrayList<String> historyList = new ArrayList<>();

    public void updateList(String history) {
        historyList.add(history);
    }

    public ArrayList<String> getHistoryList() {
        return historyList;
    }

}