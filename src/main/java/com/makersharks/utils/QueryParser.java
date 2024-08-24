package com.makersharks.utils;

import java.util.HashMap;

public class QueryParser {
  public String query;
  public HashMap <String, String> keywords;
  public QueryParser(String query) {
    this.query = query;
    this.keywords = new HashMap <> ();
    initMap();
  }

  void initMap() {
    String locations[] = {"India", "UK", "USA", "Germany", "Canada", "Australia"};
    String business[] = {"SMALL_SCALE", "LARGE_SCALE", "MEDIUM_SCALE"};
    String processes[] = {"THREE_D_PRINTING", "CASTING", "MOULDING", "COATING", "MOULDING"};
    for (var x : locations) keywords.put(x, "location");
    for (var x : processes) keywords.put(x, "process");
    for (var x : business) keywords.put(x, "natureOfBusiness");
  }

  public HashMap <String, String> getKeyWords() {
    HashMap <String, String> list = new HashMap<>();
    for (var en : keywords.entrySet()) {
      if (query.toLowerCase().contains(en.getKey().toLowerCase())) {
        System.out.println(en.getKey());
        list.put(en.getValue(), en.getKey());
      }
    }
    return list;
  }
}