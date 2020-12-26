package dev.punchcafe.implementations.broker;

public interface Broker {
    Broker subscribe(final Subscriber subscriber, final String topic);
    Broker publish(final String message, final String topic);

    static Builder newBuilder(){
        return new Builder();
    }

    class Builder {
        private String hostname;
        private Integer portNumber;

        public Builder setHostName(final String hostname) {
            this.hostname = hostname;
            return this;
        }

        public Builder setPortNumber(final int portNumber) {
            this.portNumber = portNumber;
            return this;
        }

        public Broker build() {
            return new ClientSideBroker(this.hostname, this.portNumber);
        }
    }
}
