package com.github.jkmart.kafka.ksql.udfs;

import io.confluent.ksql.function.udf.Udf;
import io.confluent.ksql.function.udf.UdfDescription;
import io.confluent.ksql.function.udf.UdfParameter;
import org.apache.commons.net.util.SubnetUtils;

@UdfDescription(name = "ipincidr", description = "Function to check if IP is in CIDR range")
public class CidrRange {

    @Udf(description = "checks if IP is in given CIDR range")
    public boolean ipincidr(
            @UdfParameter(value = "ip", description = "the IP address") final String ip,
            @UdfParameter(value = "cidr", description = "the CIDR range to check against") final String cidr
    ) {
        SubnetUtils utils = new SubnetUtils(cidr);
        return utils.getInfo().isInRange(ip);
    }
}