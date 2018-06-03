package com.spring.cloud.servicegateway.filters.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.Debug;
import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;

public class FilterExample extends ZuulFilter {

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 10000;
  }

  @Override
  public boolean shouldFilter() {
    return Debug.debugRequest();
  }

  @Override
  public Object run() {
    HttpServletRequest req = RequestContext.getCurrentContext().getRequest();
    Debug.addRequestDebug("Requete:" + req.getScheme() + " Adresse:" + req.getRemoteAddr() + " Port:" + req.getRemotePort());
    return null;
  }
}
