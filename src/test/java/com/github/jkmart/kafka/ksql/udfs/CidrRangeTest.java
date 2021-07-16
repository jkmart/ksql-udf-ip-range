package com.github.jkmart.kafka.ksql.udfs;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CidrRangeTest {

    @ParameterizedTest(name = "ipincidr({0}, {1})")
    @CsvSource({
            "192.168.1.1, 192.168.1.0/24, true",
            "192.168.2.1, 192.168.1.0/24, false",
            "10.0.0.1, 10.0.0.0/8, true",
            "11.0.0.1, 10.0.0.0/8, false",
            "192.168.0.1, 192.168.0.0/23, true",
            "192.168.1.1, 192.168.0.0/23, true",
            "192.168.2.1, 192.168.0.0/23, false",

    })
    void ipincidr(final String ip, final String cidr, final boolean expectedResult) {
        final CidrRange range = new CidrRange();
        final boolean actualResult = range.ipincidr(ip, cidr);
        assertEquals(expectedResult, actualResult, ip + " in CIDR " + cidr + " should be " + expectedResult);
    }
}