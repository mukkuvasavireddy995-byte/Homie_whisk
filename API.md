# HomeWhisk REST API

Base URL: `http://localhost:8080`

## Products

### GET `/api/products`
Returns all products.

### GET `/api/products/{id}`
Returns one product.

## Orders

### GET `/api/orders`
Returns all orders for baker management.

### POST `/api/orders`
Creates an order.

Example:

```json
{
  "customer": {
    "name": "Customer Name",
    "phone": "9876543210",
    "email": "customer@example.com",
    "address": "Kanigiri, Andhra Pradesh"
  },
  "deliveryDate": "2026-10-10",
  "items": [
    {
      "productId": 1,
      "quantity": 1,
      "customization": "Chocolate, 6 inch, Happy Birthday, Eggless"
    }
  ]
}
```

### PUT `/api/orders/{id}/status?status=PREPARING`

Valid statuses:

`PENDING`, `CONFIRMED`, `PREPARING`, `OUT_FOR_DELIVERY`, `DELIVERED`, `CANCELLED`

## HTTP behavior

- `200 OK` successful GET/update
- `201 Created` successful order creation
- `400 Bad Request` validation/business input problem
- `404 Not Found` missing product/order
