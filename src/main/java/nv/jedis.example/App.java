package nv.jedis.example;

import redis.clients.jedis.Jedis;

/**
 *
 */
public class App {
    public static void main( String[] args ) {
        // Connecting to Redis on localhost
        Jedis jedis = new Jedis("localhost");
        // adding a new key
        jedis.set("key", "value");
        // getting the key value
        System.out.println(jedis.get("key"));


        // Counter Example
        System.out.println(jedis.get("counter"));
        jedis.incr("counter");
        System.out.println(jedis.get("counter"));
        //---------------------------------------


        // Caching Example
        // adding a new key
        String cacheKey = "cachekey";
        jedis.set(cacheKey, "cached value");
        // setting the TTL in seconds
        jedis.expire(cacheKey, 15);
        // Getting the remaining ttl
        System.out.println("TTL:" + jedis.ttl(cacheKey));
        sleep(1000);
        System.out.println("TTL:" + jedis.ttl(cacheKey));
        // Getting the cache value
        System.out.println("Cached Value:" + jedis.get(cacheKey));
        // Wait for the TTL finishs
        sleep(15000);
        // trying to get the expired key
        System.out.println("Expired Key:" + jedis.get(cacheKey));
        //---------------------------------------


        // Set, List Example
        //Adding a set as value
        jedis.sadd(cacheKey,"Java","C#","Python");//SADD
        //Getting all values in the set: SMEMBERS
        System.out.println("Languages: " + jedis.smembers(cacheKey));
        //Adding new values
        jedis.sadd(cacheKey,"Java","Ruby");
        //Getting the values... it doesn't allow duplicates
        System.out.println("Languages: " + jedis.smembers(cacheKey));
        //---------------------------------------
    }


    private static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
