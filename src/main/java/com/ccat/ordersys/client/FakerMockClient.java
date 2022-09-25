package com.ccat.ordersys.client;

import com.ccat.ordersys.model.entity.Address;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class FakerMockClient implements FakerClient {
    @Override
    public Address getAddress() {
        return new Address(
                "Mockstreet 4",
                "Mockcity",
                "1337",
                "Mockcountry"
        );
    }
}
