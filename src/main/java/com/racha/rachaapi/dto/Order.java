package com.racha.rachaapi.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<SelectedItem> selectedItemList = new ArrayList<>();
    private BigDecimal subtotal;
    private BigDecimal taxAmount;
    private BigDecimal total;


    public List<SelectedItem> getSelectedItemList() {
        return selectedItemList;
    }

    public void setSelectedItemList(List<SelectedItem> selectedItemList) {
        this.selectedItemList = selectedItemList;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
