package com.wust.spring.boot.multi.tenant.demo.constant.page;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Data
@Accessors(chain = true)
public class PageRequire {

//    @Min(1)
    private int page;

//    @Range(min = 1, max = 200)
    private int pageSize;

    private OrderItems orders;

    @Data
    @Accessors(chain = true)
    public static class OrderItems {

        private List<String> items;
        private List<Boolean> ascs;

        public void fillingFromString(String str) {
            items = new ArrayList<>();
            ascs = new ArrayList<>();

            if (str == null || str.trim().equals("")) {
                return;
            }

            String[] orderSegments = str.trim().split(",");
            for (int i = 0; i < orderSegments.length; i++) {
                String orderSegment = orderSegments[i];
                if (orderSegment == null || orderSegment.trim().equals("") ||
                        orderSegment.startsWith("null.") || orderSegment.startsWith(".")) {
                    continue;
                }
                String[] array = orderSegment.trim().split("\\.");
                if (array.length != 1 && array.length != 2) {
                    throw new IllegalArgumentException("orderSegment pattern must be {property}.{direction}, input is: " + orderSegment);
                }
                items.add(array[0]);
                ascs.add(array.length != 2 || array[1].equals("asc"));
            }
        }
    }
}
