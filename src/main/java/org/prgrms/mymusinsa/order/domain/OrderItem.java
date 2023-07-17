package org.prgrms.mymusinsa.order.domain;

import java.util.UUID;

public record OrderItem(UUID productId, int quantity) {
}
