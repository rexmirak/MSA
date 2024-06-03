package com.example.demo.payment;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Long userId;
    private Long campaignId;
    private Double amount;
    private LocalDateTime paymentDate;

    public Payment() {
    }

    public Payment(Long paymentId,
                   Long userId,
                   Long campaignId,
                   Double amount,
                   LocalDateTime paymentDate) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.campaignId = campaignId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public Payment(Long userId,
                   Long campaignId,
                   Double amount) {
        this.userId = userId;
        this.campaignId = campaignId;
        this.amount = amount;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "payment{" +
                "paymentId=" + paymentId +
                ", userId=" + userId +
                ", campaignId=" + campaignId +
                ", amount=" + amount +
                '}';
    }
}
