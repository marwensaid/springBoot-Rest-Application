package com.tprest.app.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by marwen on 23/03/16.
 */

@XmlRootElement
public class RestErrorInfo {

    public final String detail;
    public final String message;

    public RestErrorInfo(Exception ex, String detail) {
        this.message = ex.getLocalizedMessage();
        this.detail = detail;
    }
}
