package com.rients.org.sourceviewer.domain;


/**
 * Object that holds an error message
 * 
 * 
 * @author x074246
 *
 */
public class ErrorDetail {
 
    /**
     * The error code
     */
    private String code;

    /**
     * The field or object that the error 'belongs' to.
     */
    private String field;

    /**
     * Descriptive text about the error
     */
    private String description;

    public ErrorDetail(final String code, final String field, final String description) {
        this.code = code;
        this.field = field;
        this.description = description;
    }

    public ErrorDetail(final String code, final String description) {
        this.code = code;
        this.description = description;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	StringBuffer buf = new StringBuffer();
    	if (code != null) {
    		buf.append(" code: ");
    		buf.append(code);
       	}
    	if (field != null) {
    		buf.append(" field: ");
    		buf.append(field);
       	}
    	if (description != null) {
    		buf.append(" description: ");
    		buf.append(description);
       	}
    	return buf.toString();
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

}