/*
 * poc
 * 11/26/18
 */
package hassidi.intuit.poc.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author odedh
 */
public class MyObjectMapper {
    public static final ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
}
