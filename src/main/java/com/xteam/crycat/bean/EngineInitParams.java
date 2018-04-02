package com.xteam.crycat.bean;

import java.util.List;
import java.util.Map;

public class EngineInitParams {

  private String serviceClass;

  private List<String> symbols;

  private Map<String, Object> params;

  public String getServiceClass() {
    return serviceClass;
  }

  public void setServiceClass(String serviceClass) {
    this.serviceClass = serviceClass;
  }

  public List<String> getSymbols() {
    return symbols;
  }

  public void setSymbols(List<String> symbols) {
    this.symbols = symbols;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  public void setParams(Map<String, Object> params) {
    this.params = params;
  }

  @Override
  public String toString() {
    return "ExchangeParams{" +
            "serviceClass='" + serviceClass + '\'' +
            ", symbols=" + symbols +
            ", params=" + params +
            '}';
  }
}
