/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wppatend.wppatendclient.restapiclient;

/**
 *
 * @author pge
 */
public class ApiReturn {

    private boolean error;
    private String message;
    private Object returns;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getReturns() {
        return returns;
    }

    public void setReturns(Object returns) {
        this.returns = returns;
    }

}
