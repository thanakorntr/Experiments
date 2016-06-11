package LeetCode;

import java.util.*;

/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

 postTweet(userId, tweetId): Compose a new tweet.
 getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 follow(followerId, followeeId): Follower follows a followee.
 unfollow(followerId, followeeId): Follower unfollows a followee.
 *
 * Example:

 Twitter twitter = new Twitter();

 // User 1 posts a new tweet (id = 5).
 twitter.postTweet(1, 5);

 // User 1's news feed should return a list with 1 tweet id -> [5].
 twitter.getNewsFeed(1);

 // User 1 follows user 2.
 twitter.follow(1, 2);

 // User 2 posts a new tweet (id = 6).
 twitter.postTweet(2, 6);

 // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 twitter.getNewsFeed(1);

 // User 1 unfollows user 2.
 twitter.unfollow(1, 2);

 // User 1's news feed should return a list with 1 tweet id -> [5],
 // since user 1 is no longer following user 2.
 twitter.getNewsFeed(1);
 *
 * Created by Thanakorn on 6/11/16.
 */
class Tweet {

    public int tweetId;
    public long timeStamp;

    public Tweet(int tweetId, long timeStamp) {
        this.tweetId = tweetId;
        this.timeStamp = timeStamp;
    }

}

class TweetsWrapper implements Comparable<TweetsWrapper> {

    public List<Tweet> tweets;
    public int index;

    public TweetsWrapper(List<Tweet> tweets, int index) {
        this.tweets = tweets;
        this.index = index;
    }

    public Tweet getIndexedTweet() {
        if (index >= 0) {
            return tweets.get(index);
        }
        return null;
    }

    @Override
    public int compareTo(TweetsWrapper o) {
        return Long.compare(o.tweets.get(o.index).timeStamp, this.tweets.get(index).timeStamp);
    }

}

public class Twitter {

    private long timeStamp;
    private int MAX_NEWS_FEED;
    private Map<Integer, Set<Integer>> userFollowMap;
    private Map<Integer, List<Tweet>> userTweetMap;

    /** Initialize your data structure here. */
    public Twitter() {
        userFollowMap = new HashMap<>();
        userTweetMap = new HashMap<>();
        timeStamp = 0;
        MAX_NEWS_FEED = 10;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(tweetId, timeStamp++);
        if (!userFollowMap.containsKey(userId)) {
            userFollowMap.put(userId, new HashSet<>());
        }
        if (!userTweetMap.containsKey(userId)) {
            userTweetMap.put(userId, new ArrayList<>());
        }
        userTweetMap.get(userId).add(tweet);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted
     * by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new ArrayList<>();

        if ((!userTweetMap.containsKey(userId) || userTweetMap.get(userId).isEmpty())
              && (!userFollowMap.containsKey(userId) || userFollowMap.get(userId).isEmpty())) {
            return newsFeed;
        }

        Queue<TweetsWrapper> tweetsWrapperQueue = new PriorityQueue<>();
        if (userTweetMap.containsKey(userId) && !userTweetMap.get(userId).isEmpty()) {
            TweetsWrapper curUserTweetWrapper = new TweetsWrapper(userTweetMap.get(userId), userTweetMap.get(userId).size() - 1);
            tweetsWrapperQueue.add(curUserTweetWrapper);
        }
        if (userFollowMap.containsKey(userId)) {
            for (int followerId : userFollowMap.get(userId)) {
                if (userTweetMap.containsKey(followerId) && !userTweetMap.get(followerId).isEmpty()) {
                    List<Tweet> followerTweets = userTweetMap.get(followerId);
                    TweetsWrapper followerTweetWrapper = new TweetsWrapper(followerTweets, followerTweets.size() - 1);
                    tweetsWrapperQueue.add(followerTweetWrapper);
                }
            }
        }

        while (newsFeed.size() < MAX_NEWS_FEED && !tweetsWrapperQueue.isEmpty()) {
            TweetsWrapper tweetsWrapper = tweetsWrapperQueue.poll();
            int tweetId = tweetsWrapper.getIndexedTweet().tweetId;
            newsFeed.add(tweetId);
            if (tweetsWrapper.index - 1 >= 0) {
                tweetsWrapperQueue.add(new TweetsWrapper(tweetsWrapper.tweets, tweetsWrapper.index - 1));
            }
        }

        return newsFeed;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        if (!userFollowMap.containsKey(followerId)) {
            userFollowMap.put(followerId, new HashSet<>());
        }
        if (!userTweetMap.containsKey(followerId)) {
            userTweetMap.put(followerId, new ArrayList<>());
        }
        if (!userFollowMap.containsKey(followeeId)) {
            userFollowMap.put(followeeId, new HashSet<>());
        }
        if (!userTweetMap.containsKey(followeeId)) {
            userTweetMap.put(followeeId, new ArrayList<>());
        }
        userFollowMap.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        if (!userFollowMap.containsKey(followerId)) {
            userFollowMap.put(followerId, new HashSet<>());
        }
        if (!userTweetMap.containsKey(followerId)) {
            userTweetMap.put(followerId, new ArrayList<>());
        }
        if (!userFollowMap.containsKey(followeeId)) {
            userFollowMap.put(followeeId, new HashSet<>());
        }
        if (!userTweetMap.containsKey(followeeId)) {
            userTweetMap.put(followeeId, new ArrayList<>());
        }
        userFollowMap.get(followerId).remove(followeeId);
    }

}
