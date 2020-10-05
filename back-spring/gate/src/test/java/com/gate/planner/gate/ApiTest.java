package com.gate.planner.gate;

import com.gate.planner.gate.model.dto.place.PlaceDto;
import com.gate.planner.gate.service.api.ApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest
@Transactional
public class ApiTest {
    @Autowired
    ApiService apiService;

    @Test
    void requestApiTest() throws IOException {
        //중간에 에러가 발생하면 안됨
        ArrayList<PlaceDto> placeList =
                Assertions.assertDoesNotThrow(() -> apiService.RequestAPI(1, "숭실대학교"));

        //정상적인 요청일 경우 size가 0이면 안됨.
        Assertions.assertNotEquals(placeList.size(), 0);

    }
}
