package ch8.asynchronous_stream;

public class ConsumerData {

    private Consumer consumer;
    private MySubscription mySubscription;

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public MySubscription getMySubscription() {
        return mySubscription;
    }

    public void setMySubscription(MySubscription mySubscription) {
        this.mySubscription = mySubscription;
    }
}
