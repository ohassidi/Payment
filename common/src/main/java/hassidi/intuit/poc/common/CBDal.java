/*
 * poc
 * 11/26/18
 */
package hassidi.intuit.poc.common;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

/**
 * @author odedh
 */
public class CBDal {

    public static Bucket getInstance() {
        return BucketHolder.INSTANCE;
    }

    private static class BucketHolder {
        static final Bucket INSTANCE = init();

        private static Bucket init () {
            // Initialize the Connection
            Cluster cluster = CouchbaseCluster.create("localhost");
            cluster.authenticate("Administrator", "shushu");
            return cluster.openBucket("intuit");
        }
    }

    private CBDal () {}

}
