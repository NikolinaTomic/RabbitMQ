# RabbitMQ
How to run project:
- From terminal run: docker run --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3.12.2-management
- Run both producer and consumer from IntelliJ
- Run curl: curl --location --request POST 'http://localhost:8083/transactions'
- Depending on testing needs, change file name in TransactionController (existing files are located in resources folder)
  
How to stop project:
- Stop both producer and consumer from Intellij
- From terminal run:
  
  docker ps
  
  docker stop containerId

Results:

![RabbitMq1000](https://github.com/NikolinaTomic/RabbitMQ/assets/44821513/4da5cac5-57f0-47f3-b01d-ded916f640ab)
![RabbitMqMillion](https://github.com/NikolinaTomic/RabbitMQ/assets/44821513/2a4b0e35-51fa-44c6-84a8-709914fdc1ea)
![RabbitMq10Million](https://github.com/NikolinaTomic/RabbitMQ/assets/44821513/489c7407-e431-45d9-90a3-840ca72fdb81)
