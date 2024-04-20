Implementation notes:
* `org.victor.fibonacci.service.FibonacciService.calculateFibonacciNumberFromMaxIndex` does not implement the calculation
using recursion in order not to overflow the stack when making the recursive call
* The Fibonacci number is calculated as a `BigInteger` to be able to reach unlimited values
* I am using a "cache" for Fibonacci values in order to be able to fetch in `O(1)` complexity a previously calculated number
* When calculating a new number, we start from the last maximum calculated value, and not starting from the beginning, for efficiency
* In terms of logging, I create a correlationId to be able to identify separately the actions of each different request (thread)
* The validation is done taking into consideration the type of the argument and the length. The length is parametrized from `application.yml` and from the Kubernetes Config Map
* I also created a local git repo, maybe showcasing the manner I commit helps