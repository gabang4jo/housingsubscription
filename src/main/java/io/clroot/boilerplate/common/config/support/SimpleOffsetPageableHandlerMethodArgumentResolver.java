package io.clroot.boilerplate.common.config.support;

import static org.apache.commons.lang3.math.NumberUtils.toInt;
import static org.apache.commons.lang3.math.NumberUtils.toLong;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SimpleOffsetPageableHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  private static final int DEFAULT_PAGE = 0;

  private static final int DEFAULT_SIZE = 10;

  private final String offsetParam;

  private final String limitParam;

  public SimpleOffsetPageableHandlerMethodArgumentResolver() {
    this("offset", "limit");
  }

  public SimpleOffsetPageableHandlerMethodArgumentResolver(String offsetParam, String limitParam) {
    this.offsetParam = offsetParam;
    this.limitParam = limitParam;
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return Pageable.class.isAssignableFrom(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
    String pageString = webRequest.getParameter(offsetParam);
    String sizeString = webRequest.getParameter(limitParam);

    int page = toInt(pageString, DEFAULT_PAGE);
    int size = toInt(sizeString, DEFAULT_SIZE);

    if (page < 0) {
      page = DEFAULT_PAGE;
    }
    if (size < 1 || size > 5) {
      size = DEFAULT_SIZE;
    }

    return new SimplePageRequest(page, size);
  }

}