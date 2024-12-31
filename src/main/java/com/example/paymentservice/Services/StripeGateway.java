package com.example.paymentservice.Services;

import com.stripe.*;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service
public class StripeGateway implements PaymentGateway{

    @Override
    public String generatePaymentLink() {

        /* API Key needs to be entered */
        Stripe.apiKey = "sk_test_51QbzfzQVy7LAC4c3tNY0MDX8BcirhuM6DlLgp7BKIg99zsDSvtt2lEOTLMdjZUSn1L019Mi4IpRQfbh3O7EEk3o500T9nTIgoK";
        Price price = getPrice();

        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();
        try {
            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Price getPrice(){
        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(1000L)
                        .setRecurring(
                                PriceCreateParams.Recurring.builder()
                                        .setInterval(PriceCreateParams.Recurring.Interval.MONTH)
                                        .build()
                        )
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();
        try {
            Price price = Price.create(params);
            return price;
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
