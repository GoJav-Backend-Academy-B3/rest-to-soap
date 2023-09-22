package com.phincon.wls.model.dto.request;

public class CreditCardRequestBuilder {
    private CreditCardRqHeader header;
    private CreditCardRqBody body;

    public class RqHeaderBuilder {

        CreditCardRequestBuilder instance;

        private String _service;
        private String _traceId;
        private String _channel;
        private String _timestamp;

        private RqHeaderBuilder(CreditCardRequestBuilder instance) {
            this.instance = instance;
        };

        public RqHeaderBuilder service(String service) {
            this._service = service;
            return this;
        }

        public RqHeaderBuilder traceId(String traceId) {
            this._traceId = traceId;
            return this;
        }

        public RqHeaderBuilder channel(String channel) {
            this._channel = channel;
            return this;
        }

        public RqHeaderBuilder timestamp(String timestamp) {
            this._timestamp = timestamp;
            return this;
        }

        public CreditCardRequestBuilder ok() {
            instance.header.setService(this._service);
            instance.header.setChannel(this._channel);
            instance.header.setTraceId(this._traceId);
            instance.header.setTimestamp(this._timestamp);
            return instance;
        }
    }

    public RqHeaderBuilder header() {
        return new RqHeaderBuilder(this);
    }

    public CreditCardRequestBuilder cif(String cif) {
        this.body.cust = cif;
        return this;
    }

    public CreditCardRequestBuilder cardNumber(String cardNumber) {
        this.body.cust = cardNumber;
        return this;
    }

    public CreditCardRequest build() {
        return new CreditCardRequest(this.header, this.body);
    }
}
