package com.example.paul.services;

import com.example.paul.models.Realm;
import com.example.paul.repositories.RealmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class RealmService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RealmService.class);

    private static final String DEFAULT_HASH = "aGJ1aW9hZndmb2l3YXVhb3NqZHdkb3Vx";

    private final RealmRepository realmRepository;

    @Autowired
    public RealmService(RealmRepository realmRepository) {
        this.realmRepository = realmRepository;
    }

    public Realm getExistingRealm(int id) {
        return realmRepository.findById(id).orElse(null);
    }

    public Realm createNewRealm(Realm realm) {
        // Verify if realm does not already exist
        if (realmRepository.existsByName(realm.getName())) {
            return null;
        }

        // Compute secure hash
        String key;
        try {
            key = buildSecureKey(realm.getName());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.warn("Unable to use SHA-256 hash. Falling back to default hash value");
            e.printStackTrace();
            key = DEFAULT_HASH;
        }
        realm.setKey(key);

        // Save and return entity
        return realmRepository.save(realm);
    }

    private String buildSecureKey(String originalString) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] encodedHashBytes = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
        String encodedHash = bytesToHex(encodedHashBytes);
        return encodedHash.substring(0, 31);
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
