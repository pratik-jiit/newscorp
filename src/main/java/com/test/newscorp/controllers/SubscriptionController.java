package com.test.newscorp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class SubscriptionController {

    private static final String TRUNCATE_STRING = " ... (truncated) ... ";
    @RequestMapping("/subscribe")
    public String subscribe(HttpServletRequest request, Model model) {
        String orderDetails = getOrderDetails(request);
        String stringSize = getStringSize(request);
        String truncatedOrderDetails = truncate(orderDetails, Integer.parseInt(stringSize));
        model.addAttribute("truncatedOrderDetails", truncatedOrderDetails);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Truncated String >> " + truncatedOrderDetails);
        return "subscribe";
    }

    private String getOrderDetails(HttpServletRequest request) {
        //Perform actual extraction of Order details here.
        //Assuming user enters the actual order details as the query string
        return request.getParameter("orderDetails");
    }

    private String getStringSize(HttpServletRequest request) {
        return request.getParameter("size");
    }

    private String truncate(String orderDetails, int size) {
        String truncatedString = "";
        if (orderDetails.length() <= size) {
            //Order Details string is less than the specified size
            truncatedString = orderDetails;
        } else if (orderDetails.length() <= TRUNCATE_STRING.length()) {
            // The size of the order details string after truncating would be more than the actual size
            truncatedString = orderDetails;
        } else if (size <= TRUNCATE_STRING.length()) {
            truncatedString = orderDetails;
        } else {
            int sizeBeforeTruncateString = (size - TRUNCATE_STRING.length())/2 + (size - TRUNCATE_STRING.length())%2;
            int sizeAfterTruncateString = orderDetails.length() - (size - (sizeBeforeTruncateString + TRUNCATE_STRING.length()));
            truncatedString = orderDetails.substring(0, sizeBeforeTruncateString).concat(TRUNCATE_STRING).concat(orderDetails.substring(sizeAfterTruncateString));
        }
        return truncatedString;
    }
}
