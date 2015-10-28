package com.rients.org.sourceviewer.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Object that wraps a list of ErrorDetail objects 
 * 
 * @author x074246
 *
 */
public class Errors {

    private List<ErrorDetail> errorDetails = new ArrayList<>();

    /**
     * @return the errors
     */
    public List<ErrorDetail> getErrorDetails() {
        return errorDetails;
    }

    /**
     * @param errorDetails the errors to set
     */
    public void setErrorDetails(List<ErrorDetail> errorDetails) {
        this.errorDetails = errorDetails;
    }
}
