package com.example.solution;

/*
* Find the top 10 trending hashtags in twitter.
* You can write a standalone java class(es) / interfaces
* as deemed to be fit.
*/


import java.util.*;
import java.util.stream.Collectors;

public class Question1Solution {
    // Method to sort Map by values
    public static HashMap<String, Integer> sortByValue(Map<String, Integer> hm){

        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2){
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    // Method returns list of top 10 Hashtags
    public static List<String> getTop10TrendingHashTagsFromString(String joinedTweets){
        String splitArray[] = joinedTweets.split(" ");
        // Filtering out all the HashTags from the List
        List<String> strList =
                Arrays.stream(splitArray).filter(s -> s.startsWith("#")).collect(Collectors.toList());
        // Map to Store number of occurrences of Hashtag
        Map<String, Integer> hm = new HashMap<>();
        strList.forEach(s -> {
            Integer j = hm.get(s);
            hm.put(s, (j == null)? 1: j + 1);
        });
        // getting a sorted hashmap based on the occurrences
        HashMap<String, Integer> hm1 = sortByValue(hm);
        // returning top 10 hashtags
        return hm1.keySet().stream().limit(10).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // Initializing Scanner to get input at runtime
        Scanner scan = new Scanner(System.in);
        // To get number of tweets
        int numberOfTweets = scan.nextInt();
        // Joining Tweets to make a single String
        String joinedTweets = "";
        for(int i =0;i <= numberOfTweets; i++){
            String tweet = scan.nextLine();
            joinedTweets = joinedTweets + " " + tweet;
        }
        // printing out Top 10 HashTags from the Tweets
        getTop10TrendingHashTagsFromString(joinedTweets).forEach(System.out::println);
    }
}
