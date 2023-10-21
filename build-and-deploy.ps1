cd ./OrderProducers/RetailOrderProducer
./gradlew dockerPushImage

cd ../../OrdersAggregator
./gradlew dockerPushImage

cd ../k8s
kubectl apply -f ./orders-aggregator.yaml
kubectl apply -f ./retail-orders.yaml

cd ..