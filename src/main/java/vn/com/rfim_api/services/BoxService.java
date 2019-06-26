package vn.com.rfim_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vn.com.rfim_api.persistences.repositories.BoxRepository;
import vn.com.rfim_api.services.jsonobjects.ResultResponse;

import java.sql.SQLSyntaxErrorException;

@Service
public class BoxService {

    @Autowired
    private BoxRepository context;

}
