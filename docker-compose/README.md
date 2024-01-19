docker container exec -it kafka /bin/bash

kafka-console-producer --broker-list localhost:9092 --topic order.created

{ "orderId": "ce94e156-ef5d-4afe-a53c-9398bdfc19b6", "item": "test"}


kafka-console-consumer --topic order.dispatched --bootstrap-server localhost:9092
kafka-console-consumer --topic dispatch.tracking --bootstrap-server localhost:9092

