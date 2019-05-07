package com.example.paul.controllers;

import com.example.paul.models.Realm;
import com.example.paul.models.RestError;
import com.example.paul.services.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("service/user/")
public class RealmRestController {

    private static final RestError BAD_REQUEST = new RestError("InvalidRealmName");

    private static final RestError ALREADY_EXISTS = new RestError("DuplicateRealmName");

    private static final RestError INVALID_ARGUMENT = new RestError("InvalidArgument");

    private static final RestError REALM_NOT_FOUND = new RestError("RealmNotFound");

    private final RealmService realmService;

    @Autowired
    public RealmRestController(RealmService realmService) {
        this.realmService = realmService;
    }

    @PostMapping(
            value = "/realm",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> createRealm(
            @RequestBody Realm realm) {
        // Validate input
        if (realm.getName() == null || realm.getName().equals("")) {
            return new ResponseEntity<>(BAD_REQUEST, HttpStatus.OK);
        }

        // Attempt to save
        Realm savedRealm = realmService.createNewRealm(realm);
        if (savedRealm == null) {
            return new ResponseEntity<>(ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(savedRealm, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/realm/{id}",
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> getRealm(
            @PathVariable String id) {
        if (id == null || id.isEmpty()) {
            return new ResponseEntity<>(INVALID_ARGUMENT, HttpStatus.BAD_REQUEST);
        } else {
            try {
                Realm realm = realmService.getExistingRealm(Integer.parseInt(id));
                if (realm == null) {
                    return new ResponseEntity<>(REALM_NOT_FOUND, HttpStatus.NOT_FOUND);
                } else {
                    return new ResponseEntity<>(realm, HttpStatus.OK);
                }
            } catch (NumberFormatException e) {
                return new ResponseEntity<>(INVALID_ARGUMENT, HttpStatus.BAD_REQUEST);
            }
        }
    }
}
