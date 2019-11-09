package com.edgile.codingchallenge.service.impl;

import com.edgile.codingchallenge.service.EncryptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.util.StringUtils.isEmpty;

/**
 * Implements MD5 hash function
 */
@Component
@AllArgsConstructor
public class Md5EncryptService implements EncryptService {

    private final MessageDigest messageDigest;

    /**
     * Encodes value using MD5 hash function.
     * If value is either empty or null then null will be returned.
     *
     * @param value that need to be encoded in MD5
     * @return either encoded value or null
     */
    @Override
    public String encode(String value) {
        if (isEmpty(value)) {
            return null;
        }
        byte[] digest = messageDigest.digest(value.getBytes(UTF_8));
        BigInteger bigInteger = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInteger.toString(16));
        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");
        }
        return md5Hex.toString();
    }
}
