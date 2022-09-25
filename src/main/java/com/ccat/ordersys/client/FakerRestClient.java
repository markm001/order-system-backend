package com.ccat.ordersys.client;

import com.ccat.ordersys.model.AddressResponse;
import com.ccat.ordersys.model.entity.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class FakerRestClient implements FakerClient {

    @Value("${fakerApi.baseUrl}")
    private String baseUrl;

    //get User Address data from API:
    @Override
    public Address getAddress() {
        RestTemplate restTemplate = new RestTemplate();

        String requestUrl = baseUrl+"/addresses?_quantity=1";
        AddressResponse addressResponse = restTemplate.getForObject(requestUrl, AddressResponse.class);

        HashMap<String, String> addressData = addressResponse.getData().get(0);

        Address userAddress = new Address(
                addressData.get("streetName") + " " + addressData.get("street"),
                addressData.get("city"),
                addressData.get("zipcode"),
                addressData.get("country"));

        try {
            return userAddress;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
