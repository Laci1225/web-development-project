package com.example.webdevelopmentproject.mapper;

import com.example.webdevelopmentproject.model.OrderDto;
import com.example.webdevelopmentproject.persistence.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto fromOrderToOrderDto(Order order);

    Order fromOrderDtoToOrder(OrderDto orderDto);

}
