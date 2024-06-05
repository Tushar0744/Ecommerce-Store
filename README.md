# E-commerce Store 

This project is a simple e-commerce store backend built with Java and Spring Boot. It provides APIs for adding items to a cart, checking out, generating discount codes, and viewing order statistics. The store has functionality for applying discount codes to every Nth orders and includes admin endpoints for managing discounts and statistics.

## Features

- Add items to the cart for a user
- Checkout and place an order
- Apply discount codes to orders (user specific)
- User can Generate discount codes for every nth order
- View statistics on orders and discounts

## Technologies Used

- Java
- Spring Boot
- JUnit 5
- Mockito

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven or Gradle
- IDE of your choice (e.g., IntelliJ IDEA)


###  Design Pattern Used

- `Service Pattern:` The project follows the service pattern, where business logic is encapsulated in service classes. This helps in keeping controllers thin and focused on handling HTTP requests and responses.

- `Repository Pattern:` The repository pattern is used to abstract the data access layer. This helps in decoupling the business logic from the data access logic.
- `Singleton Pattern:` Services and repositories are managed as singletons by Spring's dependency injection container, ensuring a single instance throughout the application's lifecycle

### Annoation Used:
`@RestController`, `@RequestMapping`, `@PostMapping`, `@GetMapping`, `@RequestParam`, `@RequestBody`, `@Service`, `@Autowired`, `@Mock`, `@InjectMocks`, `@BeforeEach`, `@Test`

### Code Features:

- Design Patterns: Using the service and repository patterns promotes code organization, maintainability, and testability.
- Annotations: The listed annotations are commonly used for building RESTful APIs with Spring and improve code readability.
- Error Handling: Implementing proper error handling with try-catch blocks ensures the application gracefully handles exceptions.
- Unit Tests: Having unit tests shows a commitment to code quality and helps prevent regressions.
- Response DTOs: Returning well-defined Response DTOs improves API clarity and reduces coupling between layers.
   
## API Endpoints

1. Add User

- URL: `users/create`
- Method: `POST`
- Request Parameter: `username`

2. Add Item to Cart

- URL: `cart/add`
- Method: `POST`
- Request Parameter: `userId`
- Request Body: 
  ```bash
  {
  "name": "Item Name",
  "price": 100.0
   }
  ```
  
3. Checkout

- URL: `cart/checkout`
- Method: `POST`
- Request Parameter: `discountCode(optional)`
- Request Parameter: `userId`

4. Get Cart

- URL: `cart/get-cart`
- Method: `GET`
- Request Paramter: `userId`

5. Generate Discount Code

- URL: `/admin/generateDiscountCode`
- Method: `POST`
- Request Parameter: `orderCount: current order count`
- Request Parameter: `userId`

6. Calculate Stats

- URL: `/admin/stats`
- Method: `GET`




### Conclusion

This `README.md` provides a clear overview of the project, including its features, setup instructions, detailed API endpoints, testing, and project structure. It should help new developers quickly understand and get started with the project.
